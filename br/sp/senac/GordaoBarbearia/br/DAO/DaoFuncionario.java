package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Funcionario;


public class DaoFuncionario extends Conexao {

	private Connection con;
	private Statement statement;
	private ResultSet rs;


	public Vector<Funcionario> funcionarios() {
		Vector<Funcionario> vectorFunc = new Vector<>();

		try {
			con = abreConexao();
			statement = con.createStatement();

			String sql = "SELECT F.ID_FUNC, U.NOME_UNIDADE, F.NOME_FUNC, F.CPF_FUNC, F.RG_FUNC, F.TELEFONE_FUNC, F.HORA_INICIO_FUNC, F.HORA_FIM_FUNC "
					+ "FROM TB_FUNCIONARIO F "
					+ "INNER JOIN TB_UNIDADE U ON U.ID_UNIDADE = F.ID_UNIDADE ";

			rs = statement.executeQuery(sql);
			
			while(rs.next()){
				Funcionario funcionario = new Funcionario();
				funcionario.setIdFunc(rs.getString("ID_FUNC"));
				funcionario.setIdUnidade(rs.getString(2));
				funcionario.setNomeFunc(rs.getString("NOME_FUNC"));
				funcionario.setCpf(rs.getString("CPF_FUNC"));
				funcionario.setRg(rs.getString("RG_FUNC"));
				funcionario.setTelefone(rs.getString("TELEFONE_FUNC"));
				funcionario.setHoraInicio(rs.getString("HORA_INICIO_FUNC"));
				funcionario.setHoraFim(rs.getString("HORA_FIM_FUNC"));
				vectorFunc.addElement(funcionario);
			}			
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vectorFunc;
	}
	
	public void preencherTabela(Vector<Funcionario> vetorFunc, JTable tabelaFunc) {
		
		DefaultTableModel model = (DefaultTableModel) tabelaFunc.getModel();
		int linhas = model.getRowCount();

		for (int i = 0; i < linhas; i++) {
			model.removeRow(0);
		}
		
		for (int i = 0; i < vetorFunc.size(); i++) {
			Funcionario funcionario = vetorFunc.get(i);
			
			model.addRow(new String[]{funcionario.getIdFunc(), funcionario.getNomeFunc(), funcionario.getCpf(), funcionario.getRg(), funcionario.getTelefone(), funcionario.getIdUnidade(),
					funcionario.getHoraInicio(), funcionario.getHoraFim()});			
		}
	}

}
