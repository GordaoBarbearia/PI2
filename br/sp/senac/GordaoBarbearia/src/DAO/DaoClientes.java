package DAO;

/*
 * Classe para intera��o com o banco de dados
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DaoClientes {

	// Metodo de pesquisa, retorna TRUE se encontrou o CPF pesquisado, ou FALSE
	// se n�o encontrou
	public boolean pesquisarCliente(String nomeCliente) {
		Statement statement;
		ResultSet resultset;
		Connection con;
		// chamando a classe de conex�o
		Conexao conexao = new Conexao();

		try {

			// atribuindo a conex�o a variavel
			con = conexao.abreConexao();
			statement = con.createStatement();

			// Query para pesquisar o CPF no banco de dados
			String sql = "SELECT CPF_CLI FROM TB_CLIENTE WHERE CPF_CLI = '"
					+ nomeCliente + "'";

			// Atribuindo o resutlado da pesquisa na variavel resultset
			resultset = statement.executeQuery(sql);

			// verificando se a pesquisa possui algum resultado, se Sim ele
			// fecha a conex�o e retorna TRUE
			if (resultset.next()) {
				conexao.fechaConexao();
				return true;

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conexao.fechaConexao();
		}

		// Caso ntenha nenhum resultado na pesquisa, ele retorna falso
		conexao.fechaConexao();
		return false;
	}

}
