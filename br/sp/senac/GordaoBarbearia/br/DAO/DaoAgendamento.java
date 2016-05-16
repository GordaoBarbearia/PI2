package DAO;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import objetos.Agendamento;
import objetos.Funcoes;

public class DaoAgendamento {

	private Connection con;
	private Statement statement;

	private void conectar() throws Exception {
		try {
			con = Conexao.abreConexao();
			statement = con.createStatement();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public boolean salvarAgendamento(Agendamento agendamento) {

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
		return false;
	}

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

	public ArrayList<String> atualizarComboFuncionario(JComboBox<String> funcionario, String idUnidade)
			throws Exception {

		ArrayList<String> arrayFuncionario = new ArrayList<>();
		ResultSet rs;

		conectar();

		String sql = "SELECT NOME_FUNC, ID_FUNC FROM TB_FUNCIONARIO WHERE ID_UNIDADE = '" + idUnidade + "' ";

		rs = statement.executeQuery(sql);

		while (rs.next()) {
			funcionario.addItem(rs.getString("NOME_FUNC"));
			arrayFuncionario.add(rs.getString("ID_FUNC"));
		}

		con.close();
		return arrayFuncionario;
	}

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

		String sql = "SELECT A.DATA_AGENDAMENTO, A.HORA_INICIO_AGEND, A.HORA_FIM_AGEND, F.NOME_FUNC, C.NOME_CLI, U.NOME_UNIDADE, SE.TIPO_SERVICO, S.STATUS_AGEND, A.ID_AGENDAMENTO "
				+ "FROM TB_AGENDAMENTO A " + "INNER JOIN TB_CLIENTE C ON C.ID_CLIENTE = A.ID_CLIENTE "
				+ "INNER JOIN TB_FUNCIONARIO F ON F.ID_FUNC = A.ID_FUNC "
				+ "INNER JOIN TB_UNIDADE U ON U.ID_UNIDADE = F.ID_UNIDADE "
				+ "INNER JOIN TB_STATUS S ON S.ID_STATUS = A.ID_STATUS "
				+ "INNER JOIN TB_SERVICOS SE ON SE.ID_SERVICO = A.ID_SERVICO " + "WHERE A.DATA_AGENDAMENTO = '"
				+ dataCalendar + "' ORDER BY A.HORA_INICIO_AGEND";

		ResultSet rs = statement.executeQuery(sql);

		// System.out.println(rs.getString(8));
		while (rs.next()) {
			model.addRow(new String[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9) });
		}
		con.close();
	}

	public static JTable getNewRenderedTable(final JTable table) {
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int col) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				String status = (String) table.getModel().getValueAt(row, 7);

				if (status.equalsIgnoreCase("ESPERA")) {
					setForeground(Color.red);
				} else {
					setForeground(Color.black);
				}

				return this;
			}
		});
		return table;
	}

	public boolean verificarDisponibilidade(String idFuncionario, String data, String horaInicio, String horaFim)
			throws Exception {

		Funcoes funcoes = new Funcoes();
		Time horaInicioTime = funcoes.converterHora(horaInicio);
		Time horaFimTime = funcoes.converterHora(horaFim);

		conectar();

		String sql = "SELECT HORA_INICIO_AGEND, HORA_FIM_AGEND FROM TB_AGENDAMENTO WHERE " + "ID_FUNC = '"
				+ idFuncionario + "' AND DATA_AGENDAMENTO = '" + data + "'";
		// AND HORA_INICIO_AGEND BETWEEN '"+horaInicio+"' AND '"+horaFim+"'";

		ResultSet rs = statement.executeQuery(sql);

		while (rs.next()) {
			Time horaI = funcoes.converterHora(rs.getString(1));
			Time horaF = funcoes.converterHora(rs.getString(2));

			System.out.println("hora banco inicio: " + horaI);
			System.out.println("hora banco fim: " + horaF);

			if (horaInicioTime.equals(horaI) || horaFimTime.equals(horaF)) {
				con.close();
				return true;

			} else if (horaInicioTime.after(horaI) && horaInicioTime.before(horaF)
					|| horaFimTime.after(horaI) && horaFimTime.before(horaF)) {
				con.close();
				return true;
			}
		}
		con.close();
		return false;
	}
	
	
}
