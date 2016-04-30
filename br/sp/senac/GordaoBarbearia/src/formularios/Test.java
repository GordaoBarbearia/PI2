package formularios;

import java.sql.*;

import javax.swing.JOptionPane;

public class Test {
	private Connection conexao;
	public Statement statement;
	public ResultSet resultset;
	public PreparedStatement prep;

	public void conecta() throws Exception {
		Class.forName("org.sqlite.JDBC");
		conexao = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\henri\\Desktop\\teste.db");
		statement = conexao.createStatement();
		conexao.setAutoCommit(false);
		conexao.setAutoCommit(true);

		String sql = "select * from tb_cliente";

		resultset = statement.executeQuery(sql);

		while (resultset.next()) {
			
			JOptionPane.showMessageDialog(null, resultset.getString(0));
		}

	}

	public void exec(String sql) throws Exception {
		resultset = statement.executeQuery(sql);
	}

	public void desconecta() {
		boolean result = true;
		try {
			conexao.close();
			JOptionPane.showMessageDialog(null, "banco fechado");
		} catch (SQLException fecha) {
			JOptionPane.showMessageDialog(null, "Não foi possivel " + "fechar o banco de dados: " + fecha);
			result = false;
		}

	}
}