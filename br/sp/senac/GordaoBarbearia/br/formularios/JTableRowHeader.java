package formularios;

import java.sql.*;

import javax.swing.JOptionPane;

public class Test {
	private Connection conexao;
	public Statement statement;
	public ResultSet resultset;
	public PreparedStatement prep;

	public Connection conectar() throws Exception {
		Class.forName("org.sqlite.JDBC");
		conexao = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\cquadros\\Desktop\\BD_GORDAO.db");
		statement = conexao.createStatement();
		conexao.setAutoCommit(false);
		conexao.setAutoCommit(true);
		
		return conexao;
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