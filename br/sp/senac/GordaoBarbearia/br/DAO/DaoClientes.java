package DAO;

/*
 * Classe para interação com o banco de dados
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import objetos.Cliente;



public class DaoClientes {

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
	
	
	// Metodo de pesquisa, retorna TRUE se encontrou o CPF pesquisado, ou FALSE
	// se não encontrou
	public ArrayList<String> pesquisarCliente(String nomeCliente) throws SQLException {

		ResultSet resultset;
		
		// chamando a classe de conexão
			

		ArrayList<String> pesquisa = new ArrayList<String>();;
		try {
			conectar();

			// atribuindo a conexão a variavel

			statement = con.createStatement();

			// Query para pesquisar o CPF no banco de dados
			String sql = "SELECT ID_CLIENTE, NOME_CLI FROM TB_CLIENTE WHERE CPF_CLI = '"
					+ nomeCliente + "'";

			// Atribuindo o resutlado da pesquisa na variavel resultset
			resultset = statement.executeQuery(sql);

			// verificando se a pesquisa possui algum resultado, se Sim ele
			// fecha a conexão e retorna TRUE
			if (resultset.next()) {				
				System.out.println(resultset.getString(1).toString());
				pesquisa.add(resultset.getString(1).toString());
				pesquisa.add(resultset.getString(2).toString());
				con.close();
				return pesquisa;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			con.close();
		}

		// Caso ntenha nenhum resultado na pesquisa, ele retorna falso
		con.close();
		return pesquisa;
	}

	public boolean salvarCliente(Cliente cliente){
		
		try {
			conectar();
			
			String sql = "INSERT INTO TB_CLIENTE(NOME_CLI, TELEFONE_CLI, CPF_CLI) VALUES"
					+ "('"+cliente.getNomeCliente()+"','"+cliente.getTelefoneCliente()+"','"+cliente.getCpfCliente()+"')";
			
			statement.executeUpdate(sql);
			return true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return false;
	}
		
	public void atualizarTabelas(JTable tableCliente) throws Exception{
		
		DefaultTableModel model = (DefaultTableModel) tableCliente.getModel();
		int linhas = model.getRowCount();
		
		for (int i = 0; i < linhas; i++) {
			model.removeRow(0);			
		}
		
		conectar();
		
		String sql = "SELECT * FROM TB_CLIENTE";
		
		ResultSet result = statement.executeQuery(sql);
		
		while(result.next()){
			
			model.addRow(new String[]{result.getString(1),result.getString(2),result.getString(4),result.getString(3)});
		}
				
	}
}
