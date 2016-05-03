package DAO;

import java.sql.*;

import javax.swing.JOptionPane;



/*
 * Classe para abrir ou fechar a conexão com o banco de dados
 */
public class Conexao {
	private static Connection conexao;

	// metodo que retornar a conexão
	public static Connection abreConexao() throws Exception {
		
		// String para pegar o caminho da pasta raiz do projeto.
		String caminho = System.getProperty("user.dir");	
		//driver de conexão para o SQLite
		Class.forName("org.sqlite.JDBC");
		// Abrindo a conexão com o banco de dados, que encontra-se na pasta raiz do projeto, na sub-pasta "bd"
		conexao = DriverManager.getConnection("jdbc:sqlite:"+caminho+"\\bd\\BD_GORDAO.db");
		conexao.setAutoCommit(false);
		conexao.setAutoCommit(true);
		
		return conexao;
	}

	//metodo para fechar uma conexão
	public static void fechaConexao() {
			try {
			conexao.close();
			//JOptionPane.showMessageDialog(null, "banco fechado");
		} catch (SQLException fecha) {
			//JOptionPane.showMessageDialog(null, "Não foi possivel " + "fechar o banco de dados: " + fecha);
		}

	}
}

	

