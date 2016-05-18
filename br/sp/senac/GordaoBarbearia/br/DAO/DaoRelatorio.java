package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Agendamento;
import modelo.Relatorio;

public class DaoRelatorio {

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

	public ArrayList<String> atualizarComboFuncionario(JComboBox<String> comboFuncionario) throws Exception {

		comboFuncionario.removeAllItems();
		ArrayList<String> arrayFuncionario = new ArrayList<>();
		ResultSet rs;

		conectar();

		String sql = "SELECT NOME_FUNC, ID_FUNC FROM TB_FUNCIONARIO";

		rs = statement.executeQuery(sql);

		while (rs.next()) {
			comboFuncionario.addItem(rs.getString("NOME_FUNC"));
			arrayFuncionario.add(rs.getString("ID_FUNC"));
		}

		con.close();
		return arrayFuncionario;
	}

	public ArrayList<String> atualizarComboCliente(JComboBox<String> comboCliente) throws Exception {

		comboCliente.removeAllItems();
		ArrayList<String> arrayCliente = new ArrayList<>();
		ResultSet rs;

		conectar();

		String sql = "SELECT NOME_CLI, ID_CLIENTE FROM TB_CLIENTE";

		rs = statement.executeQuery(sql);

		while (rs.next()) {
			comboCliente.addItem(rs.getString("NOME_CLI"));
			arrayCliente.add(rs.getString("ID_CLIENTE"));
		}

		con.close();
		return arrayCliente;
	}

	public ArrayList<String> atualizarComboUnidade(JComboBox<String> comboUnidades) throws Exception {

		comboUnidades.removeAllItems();
		ArrayList<String> arrayUnidade = new ArrayList<>();
		ResultSet rs;

		conectar();

		String sql = "SELECT NOME_UNIDADE, ID_UNIDADE FROM TB_UNIDADE";

		rs = statement.executeQuery(sql);

		while (rs.next()) {
			comboUnidades.addItem(rs.getString("NOME_UNIDADE"));
			arrayUnidade.add(rs.getString("ID_UNIDADE"));
		}
		con.close();
		return arrayUnidade;
	}

		
	
	
	public Vector<Relatorio> atualizarTabelaFunc(JTable tabela, String id, String dataInicio, String dataFim)
			throws Exception {

		/*
		 * DefaultTableModel model = (DefaultTableModel) tabela.getModel(); int
		 * linhas = model.getRowCount();
		 * 
		 * for (int i = 0; i < linhas; i++) { model.removeRow(0); }
		 */

		conectar();

		String sql = "SELECT A.DATA_AGENDAMENTO, A.HORA_INICIO_AGEND, A.HORA_FIM_AGEND, F.NOME_FUNC, C.NOME_CLI, U.NOME_UNIDADE, SE.TIPO_SERVICO, S.STATUS_AGEND, A.ID_AGENDAMENTO"
				+ " FROM TB_AGENDAMENTO A " + "INNER JOIN TB_CLIENTE C ON C.ID_CLIENTE = A.ID_CLIENTE "
				+ "INNER JOIN TB_FUNCIONARIO F ON F.ID_FUNC = A.ID_FUNC "
				+ "INNER JOIN TB_UNIDADE U ON U.ID_UNIDADE = F.ID_UNIDADE "
				+ "INNER JOIN TB_STATUS S ON S.ID_STATUS = A.ID_STATUS "
				+ "INNER JOIN TB_SERVICOS SE ON SE.ID_SERVICO = A.ID_SERVICO WHERE A.DATA_AGENDAMENTO BETWEEN '"
				+ dataInicio + "' AND '" + dataFim + "' and '" + dataFim + "' and A.ID_FUNC = '" + id
				+ "' ORDER BY A.DATA_AGENDAMENTO,A.HORA_INICIO_AGEND ";

		ResultSet rs = statement.executeQuery(sql);

		Vector<Relatorio> vectorRelatorio = new Vector<Relatorio>();

		while (rs.next()) {
			Relatorio relatorio = new Relatorio();
			relatorio.setData(rs.getString(1));
			relatorio.setHoraInicio(rs.getString(2));
			relatorio.setHoraFim(rs.getString(3));
			relatorio.setFuncionario(rs.getString(4));
			relatorio.setCliente(rs.getString(5));
			relatorio.setUnidade(rs.getString(6));
			relatorio.setServico(rs.getString(7));
			relatorio.setStatus(rs.getString(8));
			relatorio.setIdAgend(rs.getString(9));
			vectorRelatorio.addElement(relatorio);
		}

		/*
		 * while (rs.next()) { model.addRow(new String[] {
		 * rs.getString("A.DATA_AGENDAMENTO"),
		 * rs.getString(" A.HORA_INICIO_AGEND"),
		 * rs.getString("A.HORA_FIM_AGEND"), rs.getString("F.NOME_FUNC"),
		 * rs.getString("C.NOME_CLI"), rs.getString("U.NOME_UNIDADE"),
		 * rs.getString("SE.TIPO_SERVICO"), rs.getString("S.STATUS_AGEND"),
		 * rs.getString("A.ID_AGENDAMENTO") }); }
		 */
		con.close();
		return vectorRelatorio;
	}

	public Vector<Relatorio> atualizarTabelaFuncStatus(JTable tabela, String id, String dataInicio, String dataFim,
			String status) throws Exception {

		/*
		 * DefaultTableModel model = (DefaultTableModel) tabela.getModel(); int
		 * linhas = model.getRowCount();
		 * 
		 * for (int i = 0; i < linhas; i++) { model.removeRow(0); }
		 */

		conectar();

		String sql = "SELECT A.DATA_AGENDAMENTO, A.HORA_INICIO_AGEND, A.HORA_FIM_AGEND, F.NOME_FUNC, C.NOME_CLI, U.NOME_UNIDADE, SE.TIPO_SERVICO, S.STATUS_AGEND, A.ID_AGENDAMENTO"
				+ " FROM TB_AGENDAMENTO A " + "INNER JOIN TB_CLIENTE C ON C.ID_CLIENTE = A.ID_CLIENTE "
				+ "INNER JOIN TB_FUNCIONARIO F ON F.ID_FUNC = A.ID_FUNC "
				+ "INNER JOIN TB_UNIDADE U ON U.ID_UNIDADE = F.ID_UNIDADE "
				+ "INNER JOIN TB_STATUS S ON S.ID_STATUS = A.ID_STATUS "
				+ "INNER JOIN TB_SERVICOS SE ON SE.ID_SERVICO = A.ID_SERVICO WHERE A.DATA_AGENDAMENTO BETWEEN '"
				+ dataInicio + "' AND '" + dataFim + "' and '" + dataFim + "' and A.ID_FUNC = '" + id
				+ "' and A.ID_STATUS = '" + status + "'ORDER BY A.DATA_AGENDAMENTO,A.HORA_INICIO_AGEND ";

		ResultSet rs = statement.executeQuery(sql);

		Vector<Relatorio> vectorRelatorio = new Vector<Relatorio>();
		while (rs.next()) {
			Relatorio relatorio = new Relatorio();
			relatorio.setData(rs.getString(1));
			relatorio.setHoraInicio(rs.getString(2));
			relatorio.setHoraFim(rs.getString(3));
			relatorio.setFuncionario(rs.getString(4));
			relatorio.setCliente(rs.getString(5));
			relatorio.setUnidade(rs.getString(6));
			relatorio.setServico(rs.getString(7));
			relatorio.setStatus(rs.getString(8));
			relatorio.setIdAgend(rs.getString(9));
			vectorRelatorio.addElement(relatorio);
		}

		/*
		 * while (rs.next()) { model.addRow(new String[] {
		 * rs.getString("A.DATA_AGENDAMENTO"),
		 * rs.getString(" A.HORA_INICIO_AGEND"),
		 * rs.getString("A.HORA_FIM_AGEND"), rs.getString("F.NOME_FUNC"),
		 * rs.getString("C.NOME_CLI"), rs.getString("U.NOME_UNIDADE"),
		 * rs.getString("SE.TIPO_SERVICO"), rs.getString("S.STATUS_AGEND"),
		 * rs.getString("A.ID_AGENDAMENTO") }); }
		 */
		con.close();
		return vectorRelatorio;
	}

	public Vector<Relatorio> atualizarTabelaCli(JTable tabela, String id, String dataInicio, String dataFim)
			throws Exception {

		/*
		 * DefaultTableModel model = (DefaultTableModel) tabela.getModel(); int
		 * linhas = model.getRowCount();
		 * 
		 * for (int i = 0; i < linhas; i++) { model.removeRow(0); }
		 */
		conectar();

		String sql = "SELECT A.DATA_AGENDAMENTO, A.HORA_INICIO_AGEND, A.HORA_FIM_AGEND, F.NOME_FUNC, C.NOME_CLI, U.NOME_UNIDADE, SE.TIPO_SERVICO, S.STATUS_AGEND, A.ID_AGENDAMENTO"
				+ " FROM TB_AGENDAMENTO A " + "INNER JOIN TB_CLIENTE C ON C.ID_CLIENTE = A.ID_CLIENTE "
				+ "INNER JOIN TB_FUNCIONARIO F ON F.ID_FUNC = A.ID_FUNC "
				+ "INNER JOIN TB_UNIDADE U ON U.ID_UNIDADE = F.ID_UNIDADE "
				+ "INNER JOIN TB_STATUS S ON S.ID_STATUS = A.ID_STATUS "
				+ "INNER JOIN TB_SERVICOS SE ON SE.ID_SERVICO = A.ID_SERVICO WHERE A.DATA_AGENDAMENTO BETWEEN '"
				+ dataInicio + "' AND '" + dataFim + "' and '" + dataFim + "' AND A.ID_CLIENTE = '" + id
				+ "' ORDER BY A.DATA_AGENDAMENTO,A.HORA_INICIO_AGEND ";

		ResultSet rs = statement.executeQuery(sql);

		Vector<Relatorio> vectorRelatorio = new Vector<Relatorio>();
		while (rs.next()) {
			Relatorio relatorio = new Relatorio();
			relatorio.setData(rs.getString(1));
			relatorio.setHoraInicio(rs.getString(2));
			relatorio.setHoraFim(rs.getString(3));
			relatorio.setFuncionario(rs.getString(4));
			relatorio.setCliente(rs.getString(5));
			relatorio.setUnidade(rs.getString(6));
			relatorio.setServico(rs.getString(7));
			relatorio.setStatus(rs.getString(8));
			relatorio.setIdAgend(rs.getString(9));
			vectorRelatorio.addElement(relatorio);
		}

		/*
		 * while (rs.next()) { model.addRow(new String[] {
		 * rs.getString("A.DATA_AGENDAMENTO"),
		 * rs.getString(" A.HORA_INICIO_AGEND"),
		 * rs.getString("A.HORA_FIM_AGEND"), rs.getString("F.NOME_FUNC"),
		 * rs.getString("C.NOME_CLI"), rs.getString("U.NOME_UNIDADE"),
		 * rs.getString("SE.TIPO_SERVICO"), rs.getString("S.STATUS_AGEND"),
		 * rs.getString("A.ID_AGENDAMENTO") }); }
		 */
		con.close();
		return vectorRelatorio;
	}

	public Vector<Relatorio> atualizarTabelaCliStatus(JTable tabela, String id, String dataInicio, String dataFim,
			String status) throws Exception {
		/*
		 * DefaultTableModel model = (DefaultTableModel) tabela.getModel(); int
		 * linhas = model.getRowCount();
		 * 
		 * for (int i = 0; i < linhas; i++) { model.removeRow(0); }
		 */
		conectar();

		String sql = "SELECT A.DATA_AGENDAMENTO, A.HORA_INICIO_AGEND, A.HORA_FIM_AGEND, F.NOME_FUNC, C.NOME_CLI, U.NOME_UNIDADE, SE.TIPO_SERVICO, S.STATUS_AGEND, A.ID_AGENDAMENTO"
				+ " FROM TB_AGENDAMENTO A " + "INNER JOIN TB_CLIENTE C ON C.ID_CLIENTE = A.ID_CLIENTE "
				+ "INNER JOIN TB_FUNCIONARIO F ON F.ID_FUNC = A.ID_FUNC "
				+ "INNER JOIN TB_UNIDADE U ON U.ID_UNIDADE = F.ID_UNIDADE "
				+ "INNER JOIN TB_STATUS S ON S.ID_STATUS = A.ID_STATUS "
				+ "INNER JOIN TB_SERVICOS SE ON SE.ID_SERVICO = A.ID_SERVICO WHERE A.DATA_AGENDAMENTO BETWEEN '"
				+ dataInicio + "' AND '" + dataFim + "' and '" + dataFim + "' and A.ID_CLIENTE = '" + id
				+ "' AND A.ID_STATUS = '" + status + "'ORDER BY A.DATA_AGENDAMENTO,A.HORA_INICIO_AGEND ";

		ResultSet rs = statement.executeQuery(sql);

		Vector<Relatorio> vectorRelatorio = new Vector<Relatorio>();
		while (rs.next()) {
			Relatorio relatorio = new Relatorio();
			relatorio.setData(rs.getString(1));
			relatorio.setHoraInicio(rs.getString(2));
			relatorio.setHoraFim(rs.getString(3));
			relatorio.setFuncionario(rs.getString(4));
			relatorio.setCliente(rs.getString(5));
			relatorio.setUnidade(rs.getString(6));
			relatorio.setServico(rs.getString(7));
			relatorio.setStatus(rs.getString(8));
			relatorio.setIdAgend(rs.getString(9));
			vectorRelatorio.addElement(relatorio);
		}

		/*
		 * while (rs.next()) { model.addRow(new String[] {
		 * rs.getString("A.DATA_AGENDAMENTO"),
		 * rs.getString(" A.HORA_INICIO_AGEND"),
		 * rs.getString("A.HORA_FIM_AGEND"), rs.getString("F.NOME_FUNC"),
		 * rs.getString("C.NOME_CLI"), rs.getString("U.NOME_UNIDADE"),
		 * rs.getString("SE.TIPO_SERVICO"), rs.getString("S.STATUS_AGEND"),
		 * rs.getString("A.ID_AGENDAMENTO") }); }
		 */
		con.close();
		return vectorRelatorio;
	}

	public Vector<Relatorio> atualizarTabelaUnidade(JTable tabela, String id, String dataInicio, String dataFim) throws Exception {

		conectar();

		String sql = "SELECT A.DATA_AGENDAMENTO, A.HORA_INICIO_AGEND, A.HORA_FIM_AGEND, F.NOME_FUNC, C.NOME_CLI, U.NOME_UNIDADE, SE.TIPO_SERVICO, S.STATUS_AGEND, A.ID_AGENDAMENTO"
				+ " FROM TB_AGENDAMENTO A " + "INNER JOIN TB_CLIENTE C ON C.ID_CLIENTE = A.ID_CLIENTE "
				+ "INNER JOIN TB_FUNCIONARIO F ON F.ID_FUNC = A.ID_FUNC "
				+ "INNER JOIN TB_UNIDADE U ON U.ID_UNIDADE = F.ID_UNIDADE "
				+ "INNER JOIN TB_STATUS S ON S.ID_STATUS = A.ID_STATUS "
				+ "INNER JOIN TB_SERVICOS SE ON SE.ID_SERVICO = A.ID_SERVICO WHERE A.DATA_AGENDAMENTO BETWEEN '"
				+ dataInicio + "' AND '" + dataFim + "' and '" + dataFim + "' AND U.ID_UNIDADE = '" + id
				+ "' ORDER BY A.DATA_AGENDAMENTO,A.HORA_INICIO_AGEND ";

		ResultSet rs = statement.executeQuery(sql);

		Vector<Relatorio> vectorRelatorio = new Vector<Relatorio>();
		while (rs.next()) {
			Relatorio relatorio = new Relatorio();
			relatorio.setData(rs.getString(1));
			relatorio.setHoraInicio(rs.getString(2));
			relatorio.setHoraFim(rs.getString(3));
			relatorio.setFuncionario(rs.getString(4));
			relatorio.setCliente(rs.getString(5));
			relatorio.setUnidade(rs.getString(6));
			relatorio.setServico(rs.getString(7));
			relatorio.setStatus(rs.getString(8));
			relatorio.setIdAgend(rs.getString(9));
			vectorRelatorio.addElement(relatorio);
		}
		con.close();
		return vectorRelatorio;
	}

	public Vector<Relatorio> atualizarTabelaUnidadeStatus(JTable tabela, String id, String dataInicio, String dataFim,
			String status) throws Exception {

		conectar();

		String sql = "SELECT A.DATA_AGENDAMENTO, A.HORA_INICIO_AGEND, A.HORA_FIM_AGEND, F.NOME_FUNC, C.NOME_CLI, U.NOME_UNIDADE, SE.TIPO_SERVICO, S.STATUS_AGEND, A.ID_AGENDAMENTO"
				+ " FROM TB_AGENDAMENTO A " + "INNER JOIN TB_CLIENTE C ON C.ID_CLIENTE = A.ID_CLIENTE "
				+ "INNER JOIN TB_FUNCIONARIO F ON F.ID_FUNC = A.ID_FUNC "
				+ "INNER JOIN TB_UNIDADE U ON U.ID_UNIDADE = F.ID_UNIDADE "
				+ "INNER JOIN TB_STATUS S ON S.ID_STATUS = A.ID_STATUS "
				+ "INNER JOIN TB_SERVICOS SE ON SE.ID_SERVICO = A.ID_SERVICO WHERE A.DATA_AGENDAMENTO BETWEEN '"
				+ dataInicio + "' AND '" + dataFim + "' and '" + dataFim + "' and U.ID_UNIDADE = '" + id
				+ "' AND A.ID_STATUS = '" + status + "'ORDER BY A.DATA_AGENDAMENTO,A.HORA_INICIO_AGEND ";

		ResultSet rs = statement.executeQuery(sql);

		Vector<Relatorio> vectorRelatorio = new Vector<Relatorio>();
		while (rs.next()) {
			Relatorio relatorio = new Relatorio();
			relatorio.setData(rs.getString(1));
			relatorio.setHoraInicio(rs.getString(2));
			relatorio.setHoraFim(rs.getString(3));
			relatorio.setFuncionario(rs.getString(4));
			relatorio.setCliente(rs.getString(5));
			relatorio.setUnidade(rs.getString(6));
			relatorio.setServico(rs.getString(7));
			relatorio.setStatus(rs.getString(8));
			relatorio.setIdAgend(rs.getString(9));
			vectorRelatorio.addElement(relatorio);
		}

		con.close();
		return vectorRelatorio;
	}
	
	
	
	
	
	
	public Vector<Relatorio> atualizarTabelaTodos(JTable tabela, String dataInicio, String dataFim) throws Exception {

		/*
		 * DefaultTableModel model = (DefaultTableModel) tabela.getModel(); int
		 * linhas = model.getRowCount();
		 * 
		 * for (int i = 0; i < linhas; i++) { model.removeRow(0); }
		 */
		conectar();

		String sql = "SELECT A.DATA_AGENDAMENTO, A.HORA_INICIO_AGEND, A.HORA_FIM_AGEND, F.NOME_FUNC, C.NOME_CLI, U.NOME_UNIDADE, SE.TIPO_SERVICO, S.STATUS_AGEND, A.ID_AGENDAMENTO"
				+ " FROM TB_AGENDAMENTO A " + "INNER JOIN TB_CLIENTE C ON C.ID_CLIENTE = A.ID_CLIENTE "
				+ "INNER JOIN TB_FUNCIONARIO F ON F.ID_FUNC = A.ID_FUNC "
				+ "INNER JOIN TB_UNIDADE U ON U.ID_UNIDADE = F.ID_UNIDADE "
				+ "INNER JOIN TB_STATUS S ON S.ID_STATUS = A.ID_STATUS "
				+ "INNER JOIN TB_SERVICOS SE ON SE.ID_SERVICO = A.ID_SERVICO WHERE A.DATA_AGENDAMENTO BETWEEN '"
				+ dataInicio + "' AND '" + dataFim + "' and '" + dataFim
				+ "' ORDER BY A.DATA_AGENDAMENTO,A.HORA_INICIO_AGEND ";

		ResultSet rs = statement.executeQuery(sql);

		Vector<Relatorio> vectorRelatorio = new Vector<Relatorio>();
		while (rs.next()) {
			Relatorio relatorio = new Relatorio();
			relatorio.setData(rs.getString(1));
			relatorio.setHoraInicio(rs.getString(2));
			relatorio.setHoraFim(rs.getString(3));
			relatorio.setFuncionario(rs.getString(4));
			relatorio.setCliente(rs.getString(5));
			relatorio.setUnidade(rs.getString(6));
			relatorio.setServico(rs.getString(7));
			relatorio.setStatus(rs.getString(8));
			relatorio.setIdAgend(rs.getString(9));
			vectorRelatorio.addElement(relatorio);
		}

		/*
		 * while (rs.next()) { model.addRow(new String[] {
		 * rs.getString("A.DATA_AGENDAMENTO"),
		 * rs.getString(" A.HORA_INICIO_AGEND"),
		 * rs.getString("A.HORA_FIM_AGEND"), rs.getString("F.NOME_FUNC"),
		 * rs.getString("C.NOME_CLI"), rs.getString("U.NOME_UNIDADE"),
		 * rs.getString("SE.TIPO_SERVICO"), rs.getString("S.STATUS_AGEND"),
		 * rs.getString("A.ID_AGENDAMENTO") }); }
		 */
		con.close();
		return vectorRelatorio;
	}

	public Vector<Relatorio> atualizarTabelaTodosStatus(JTable tabela, String dataInicio, String dataFim, String status)
			throws Exception {
		/*
		 * DefaultTableModel model = (DefaultTableModel) tabela.getModel(); int
		 * linhas = model.getRowCount();
		 * 
		 * for (int i = 0; i < linhas; i++) { model.removeRow(0); }
		 */
		conectar();

		String sql = "SELECT A.DATA_AGENDAMENTO, A.HORA_INICIO_AGEND, A.HORA_FIM_AGEND, F.NOME_FUNC, C.NOME_CLI, U.NOME_UNIDADE, SE.TIPO_SERVICO, S.STATUS_AGEND, A.ID_AGENDAMENTO"
				+ " FROM TB_AGENDAMENTO A " + "INNER JOIN TB_CLIENTE C ON C.ID_CLIENTE = A.ID_CLIENTE "
				+ "INNER JOIN TB_FUNCIONARIO F ON F.ID_FUNC = A.ID_FUNC "
				+ "INNER JOIN TB_UNIDADE U ON U.ID_UNIDADE = F.ID_UNIDADE "
				+ "INNER JOIN TB_STATUS S ON S.ID_STATUS = A.ID_STATUS "
				+ "INNER JOIN TB_SERVICOS SE ON SE.ID_SERVICO = A.ID_SERVICO WHERE A.DATA_AGENDAMENTO BETWEEN '"
				+ dataInicio + "' AND '" + dataFim + "' AND '" + dataFim + "' AND A.ID_STATUS = '" + status
				+ "'ORDER BY A.DATA_AGENDAMENTO,A.HORA_INICIO_AGEND ";

		ResultSet rs = statement.executeQuery(sql);

		Vector<Relatorio> vectorRelatorio = new Vector<Relatorio>();
		while (rs.next()) {
			Relatorio relatorio = new Relatorio();
			relatorio.setData(rs.getString(1));
			relatorio.setHoraInicio(rs.getString(2));
			relatorio.setHoraFim(rs.getString(3));
			relatorio.setFuncionario(rs.getString(4));
			relatorio.setCliente(rs.getString(5));
			relatorio.setUnidade(rs.getString(6));
			relatorio.setServico(rs.getString(7));
			relatorio.setStatus(rs.getString(8));
			relatorio.setIdAgend(rs.getString(9));
			vectorRelatorio.addElement(relatorio);
		}

		/*
		 * while (rs.next()) { model.addRow(new String[] {
		 * rs.getString("A.DATA_AGENDAMENTO"),
		 * rs.getString(" A.HORA_INICIO_AGEND"),
		 * rs.getString("A.HORA_FIM_AGEND"), rs.getString("F.NOME_FUNC"),
		 * rs.getString("C.NOME_CLI"), rs.getString("U.NOME_UNIDADE"),
		 * rs.getString("SE.TIPO_SERVICO"), rs.getString("S.STATUS_AGEND"),
		 * rs.getString("A.ID_AGENDAMENTO") }); }
		 */
		con.close();
		return vectorRelatorio;
	}

	public void preencherTabela(Vector<Relatorio> vetorRel, JTable tabelaRel) {

		DefaultTableModel model = (DefaultTableModel) tabelaRel.getModel();
		int linhas = model.getRowCount();

		for (int i = 0; i < linhas; i++) {
			model.removeRow(0);
		}

		for (int i = 0; i < vetorRel.size(); i++) {
			Relatorio relatorio = vetorRel.get(i);

			model.addRow(new String[] { relatorio.getData(), relatorio.getHoraInicio(), relatorio.getHoraFim(),
					relatorio.getFuncionario(), relatorio.getCliente(), relatorio.getUnidade(), relatorio.getServico(),
					relatorio.getStatus(), relatorio.getIdAgend(), relatorio.getIdAgend() });
		}
	}
}
