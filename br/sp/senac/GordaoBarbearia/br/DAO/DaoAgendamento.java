package DAO;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JCalendar;
import modelo.Agendamento;
import modelo.Funcionario;
import modelo.Funcoes;

public class DaoAgendamento {
	private Connection con;
	private Statement statement;

	// Metodo utilizado para abrir conexao
	private void conectar() throws Exception {
		try {
			con = Conexao.abreConexao();
			statement = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Chamado para salvar o agendamento
	public boolean salvarAgendamento(Agendamento agendamento) throws SQLException {
		try {
			conectar();
			String sql = "INSERT INTO TB_AGENDAMENTO(ID_FUNC, ID_CLIENTE, ID_STATUS, ID_SERVICO, DATA_AGENDAMENTO, HORA_INICIO_AGEND, HORA_FIM_AGEND)"
					+ "VALUES ('" + agendamento.getIdFuncionario() + "','" + agendamento.getIdCliente() + "','"
					+ agendamento.getIdStatus() + "'" + ",'" + agendamento.getIdServico() + "','"
					+ agendamento.getDataAgendamento() + "','" + agendamento.getHoraInicioAgendmento() + "','"
					+ agendamento.getHoraFimAgendamento() + "')";
			statement.executeUpdate(sql);
			con.close();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con.close();
		return false;
	}

	// Chamado para inserir os valores disponiveis no combobox de status
	public ArrayList<String> atualiazaComboStatus(JComboBox<String> status) throws Exception {
		status.removeAllItems();
		ArrayList<String> arrayStatus = new ArrayList<>();
		ResultSet rs;
		conectar();
		String sql = "SELECT ID_STATUS, STATUS_AGEND FROM TB_STATUS";
		rs = statement.executeQuery(sql);
		while (rs.next()) {
			arrayStatus.add(rs.getString("ID_STATUS"));
			status.addItem(rs.getString("STATUS_AGEND"));
		}
		con.close();
		return arrayStatus;
	}

	// Chamado para preencher os valores no combobox das unidades
	public ArrayList<String> atualiazaComboUnidade(JComboBox<String> unidade) throws Exception {
		unidade.removeAllItems();
		ArrayList<String> arrayStatus = new ArrayList<>();
		ResultSet rs;
		conectar();
		String sql = "SELECT ID_UNIDADE, NOME_UNIDADE FROM TB_UNIDADE";
		rs = statement.executeQuery(sql);
		arrayStatus.add("");
		unidade.addItem("");
		while (rs.next()) {
			arrayStatus.add(rs.getString("ID_UNIDADE"));
			unidade.addItem(rs.getString("NOME_UNIDADE"));
		}
		con.close();
		return arrayStatus;
	}

	// Chamado para preencher os valores no combobox de funcionarios de acordo
	// com a unidade selecionada
	public Vector<Funcionario> atualizarComboFuncionario(JComboBox<String> funcionario1, String idUnidade)
			throws Exception {
		ResultSet rs;
		conectar();
		String sql = "SELECT NOME_FUNC, ID_FUNC, HORA_INICIO_FUNC, HORA_FIM_FUNC FROM TB_FUNCIONARIO WHERE ID_UNIDADE = '"
				+ idUnidade + "' ";
		rs = statement.executeQuery(sql);
		Vector<Funcionario> vectorFunc = new Vector<Funcionario>();
		while (rs.next()) {
			Funcionario funcionario = new Funcionario();
			funcionario.setNomeFunc(rs.getString("NOME_FUNC"));
			funcionario1.addItem(rs.getString("NOME_FUNC"));
			funcionario.setIdFunc(rs.getString("ID_FUNC"));
			funcionario.setHoraInicio(rs.getString("HORA_INICIO_FUNC"));
			funcionario.setHoraFim(rs.getString("HORA_FIM_FUNC"));
			vectorFunc.addElement(funcionario);
		}
		con.close();
		return vectorFunc;
	}

	// Chamado para preencher os valores no combobox de tipos de servicos
	public ArrayList<String> atualizarComboServicos(JComboBox<String> servicos) throws Exception {
		servicos.removeAllItems();
		ArrayList<String> arrayServicos = new ArrayList<>();
		ResultSet rs;
		conectar();
		String sql = "SELECT ID_SERVICO, TIPO_SERVICO FROM TB_SERVICOS";
		rs = statement.executeQuery(sql);
		while (rs.next()) {
			arrayServicos.add(rs.getString("ID_SERVICO"));
			servicos.addItem(rs.getString("TIPO_SERVICO"));
		}
		con.close();
		return arrayServicos;
	}

	// Traz na tela as informacoes da agenda de acordo com o dia selecionado
	public void atualizarTabela(JTable tabelaAgendamento, JCalendar calendario) throws Exception {
		String dataCalendar = null;
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
		dataCalendar = dataFormat.format(calendario.getDate());
		DefaultTableModel model = (DefaultTableModel) tabelaAgendamento.getModel();
		int linhas = model.getRowCount();
		for (int i = 0; i < linhas; i++) {
			model.removeRow(0);
		}
		conectar();
		String sql = "SELECT A.DATA_AGENDAMENTO, A.HORA_INICIO_AGEND, A.HORA_FIM_AGEND, F.NOME_FUNC, C.NOME_CLI, U.NOME_UNIDADE, SE.TIPO_SERVICO, S.STATUS_AGEND, A.ID_AGENDAMENTO,F.ID_FUNC "
				+ "FROM TB_AGENDAMENTO A " + "INNER JOIN TB_CLIENTE C ON C.ID_CLIENTE = A.ID_CLIENTE "
				+ "INNER JOIN TB_FUNCIONARIO F ON F.ID_FUNC = A.ID_FUNC "
				+ "INNER JOIN TB_UNIDADE U ON U.ID_UNIDADE = F.ID_UNIDADE "
				+ "INNER JOIN TB_STATUS S ON S.ID_STATUS = A.ID_STATUS "
				+ "INNER JOIN TB_SERVICOS SE ON SE.ID_SERVICO = A.ID_SERVICO " + "WHERE A.DATA_AGENDAMENTO = '"
				+ dataCalendar + "' ORDER BY A.HORA_INICIO_AGEND";
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			model.addRow(
					new String[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10) });
		}
		con.close();
	}

	// Colorindo as linhas que aparecem na tela de agendamento
	public static JTable getNewRenderedTable(final JTable table) {
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int col) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
				String status = (String) table.getModel().getValueAt(row, 7);
				if (status.equalsIgnoreCase("ESPERA")) {
					setForeground(Color.MAGENTA);
				} else if (status.equalsIgnoreCase("ATENDIDO")) {
					setForeground(new Color(34, 139, 34));
				} else if (status.equalsIgnoreCase("CANCELADO")) {
					setForeground(Color.RED);
				} else if (status.equalsIgnoreCase("AGENDADO")) {
					setForeground(Color.BLUE);
				}
				return this;
			}
		});
		return table;
	}

	// Verifica se o horario solicitado esta disponivel
	public boolean verificarDisponibilidade(String idFuncionario, String data, String horaInicio, String horaFim)
			throws Exception {
		Funcoes funcoes = new Funcoes();
		Time horaInicioTime = funcoes.converterHora(horaInicio);
		Time horaFimTime = funcoes.converterHora(horaFim);
		conectar();
		String sql = "SELECT HORA_INICIO_AGEND, HORA_FIM_AGEND, ID_STATUS FROM TB_AGENDAMENTO WHERE " + "ID_FUNC = '"
				+ idFuncionario + "' AND DATA_AGENDAMENTO = '" + data + "' AND ID_STATUS <> '3' AND ID_STATUS <> '2' ";
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			Time horaI = funcoes.converterHora(rs.getString(1));
			Time horaF = funcoes.converterHora(rs.getString(2));
			if (horaInicioTime.equals(horaI) || horaFimTime.equals(horaF)) {
				con.close();
				return false;
			} else if (horaInicioTime.after(horaI) && horaInicioTime.before(horaF)
					|| horaFimTime.after(horaI) && horaFimTime.before(horaF)) {
				con.close();
				return false;
			} else if (horaInicioTime.before(horaI) && horaFimTime.after(horaF)) {
				con.close();
				return false;
			}
		}
		con.close();
		return true;
	}

	// Verifica se o horario do agenramento esta dentro da jornada de trabalho
	// do funcionario selecionado
	public boolean verificarHorarioFunc(String horaInicio, String horaFim, String horaFuncIni, String horaFuncFim)
			throws ParseException, SQLException {
		Funcoes funcoes = new Funcoes();
		Time horaInicioTime = funcoes.converterHora(horaInicio);
		Time horaFimTime = funcoes.converterHora(horaFim);
		Time horaFuncTimeIni = funcoes.converterHora(horaFuncIni);
		Time horaFuncTimeFim = funcoes.converterHora(horaFuncFim);
		if (horaInicioTime.before(horaFuncTimeIni) || horaInicioTime.after(horaFuncTimeFim)
				|| horaFimTime.after(horaFuncTimeFim)) {
			con.close();
			return false;
		}
		con.close();
		return true;
	}

	// Faz a alteracao do status quando necessario
	public boolean atualizarStatus(int idAgend, int statusAgend) throws SQLException {
		try {
			conectar();
			String sql = "UPDATE TB_AGENDAMENTO SET ID_STATUS='" + statusAgend + "'" + " WHERE ID_AGENDAMENTO = '"
					+ idAgend + "'";
			statement.executeUpdate(sql);
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			con.close();
			return false;
		}
		return true;
	}

}
