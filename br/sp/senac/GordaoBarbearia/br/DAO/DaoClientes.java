package DAO;

/*
 * Classe utilizada para tratar interacoes entre bd e app na tabela clientes
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;


public class DaoClientes {
	private Connection con;
	private Statement statement;
	// Abre a conexao com BD
	private void conectar() throws Exception {
		try {
			con = Conexao.abreConexao();
			statement = con.createStatement();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Retorna id e nome do cliente buscando pelo CPF
	public ArrayList<String> pesquisarCliente(String cpfCliente) throws SQLException {
		ResultSet resultset;
		// chamando a classe de conexão
		ArrayList<String> pesquisa = new ArrayList<String>();
		;
		try {
			conectar();
			// atribuindo a conexão a variavel
			statement = con.createStatement();
			// Query para pesquisar o CPF no banco de dados
			String sql = "SELECT ID_CLIENTE, NOME_CLI FROM TB_CLIENTE WHERE CPF_CLI = '" + cpfCliente + "'";
			// Atribuindo o resutlado da pesquisa na variavel resultset
			resultset = statement.executeQuery(sql);
			if (resultset.next()) {
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
		con.close();
		return pesquisa;
	}

	// Salva os clientes cadastrados
	public boolean salvarCliente(Cliente cliente) {
		try {
			conectar();
			String sql = "INSERT INTO TB_CLIENTE(NOME_CLI, TELEFONE_CLI, CPF_CLI) VALUES" + "('"
					+ cliente.getNomeCliente() + "','" + cliente.getTelefoneCliente() + "','" + cliente.getCpfCliente()
					+ "')";
			statement.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// Edita um cliente ja cadastrado
	public boolean editarCliente(Cliente cliente, String idCliente) {
		try {
			conectar();
			String sql = "UPDATE TB_CLIENTE SET NOME_CLI='"+cliente.getNomeCliente()+"', "
					+ "TELEFONE_CLI='"+cliente.getTelefoneCliente()+"', CPF_CLI='"+cliente.getCpfCliente()+"'"
					+ "WHERE ID_CLIENTE = '"+idCliente+"'";
			statement.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// Atualiza os dados na tela que exibe a tabela de clientes
	public void atualizarTabelas(JTable tableCliente) throws Exception {
		DefaultTableModel model = (DefaultTableModel) tableCliente.getModel();
		int linhas = model.getRowCount();
		for (int i = 0; i < linhas; i++) {
			model.removeRow(0);
		}
		conectar();
		String sql = "SELECT * FROM TB_CLIENTE";
		ResultSet result = statement.executeQuery(sql);
		while (result.next()) {

			model.addRow(new String[] { result.getString(1), result.getString(2), result.getString(4),
					result.getString(3) });
		}
	}
	
}
