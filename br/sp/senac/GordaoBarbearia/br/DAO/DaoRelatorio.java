package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;

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
	
	public ArrayList<String> atualizarComboFuncionario(JComboBox<String> comboFuncionario)
			throws Exception {

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
	
	public ArrayList<String> atualizarComboCliente(JComboBox<String> comboCliente)throws Exception {

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
	
}
