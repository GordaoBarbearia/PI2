package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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

	public void atualizarTabelaFunc(JTable tabela, String id, String dataInicio, String dataFim) throws Exception {

		DefaultTableModel model = (DefaultTableModel) tabela.getModel();
		int linhas = model.getRowCount();

		for (int i = 0; i < linhas; i++) {
			model.removeRow(0);
		}
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

		// System.out.println(rs.getString(8));
		while (rs.next()) {
			model.addRow(new String[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9) });
		}
	}

	public void atualizarTabelaFuncStatus(JTable tabela, String id, String dataInicio, String dataFim, String status)
			throws Exception {

		DefaultTableModel model = (DefaultTableModel) tabela.getModel();
		int linhas = model.getRowCount();

		for (int i = 0; i < linhas; i++) {
			model.removeRow(0);
		}
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

		// System.out.println(rs.getString(8));
		while (rs.next()) {
			model.addRow(new String[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9) });
		}
	}

	public void atualizarTabelaCli(JTable tabela, String id, String dataInicio, String dataFim) throws Exception {
		DefaultTableModel model = (DefaultTableModel) tabela.getModel();
		int linhas = model.getRowCount();

		for (int i = 0; i < linhas; i++) {
			model.removeRow(0);
		}
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

		while (rs.next()) {
			model.addRow(new String[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9) });
		}
	}

	public void atualizarTabelaCliStatus(JTable tabela, String id, String dataInicio, String dataFim, String status)
			throws Exception {
		DefaultTableModel model = (DefaultTableModel) tabela.getModel();
		int linhas = model.getRowCount();

		for (int i = 0; i < linhas; i++) {
			model.removeRow(0);
		}
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

		while (rs.next()) {
			model.addRow(new String[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9) });
		}
	}

	public void atualizarTabelaTodos(JTable tabela, String dataInicio, String dataFim) throws Exception {
		DefaultTableModel model = (DefaultTableModel) tabela.getModel();
		int linhas = model.getRowCount();

		for (int i = 0; i < linhas; i++) {
			model.removeRow(0);
		}
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

		while (rs.next()) {
			model.addRow(new String[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9) });
		}
	}

	public void atualizarTabelaTodosStatus(JTable tabela, String dataInicio, String dataFim, String status)
			throws Exception {
		DefaultTableModel model = (DefaultTableModel) tabela.getModel();
		int linhas = model.getRowCount();

		for (int i = 0; i < linhas; i++) {
			model.removeRow(0);
		}
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

		while (rs.next()) {
			model.addRow(new String[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9) });
		}
	}
}
