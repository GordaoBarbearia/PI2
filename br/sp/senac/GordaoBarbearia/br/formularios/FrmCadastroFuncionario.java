package formularios;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.DaoFuncionario;
import modelo.Funcionario;

import javax.swing.JButton;

public class FrmCadastroFuncionario {

	static JFrame formCadFunc;
	private JScrollPane scrollTabelaFuncionario;
	private JTable tabelaFuncionario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCadastroFuncionario window = new FrmCadastroFuncionario();
					window.formCadFunc.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmCadastroFuncionario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		formCadFunc = new JFrame();
		formCadFunc.setResizable(false);
		formCadFunc.setTitle("Barbearia O Gord\u00E3o - Cadastro de Funcion\u00E1rios");
		formCadFunc.setBounds(100, 100, 705, 414);
		formCadFunc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		formCadFunc.getContentPane().setLayout(null);
		
		//criação da tabela de exibição dos funcionários
		tabelaFuncionario = new JTable(0,0);
		tabelaFuncionario.setBounds(50,50,50,50);
		tabelaFuncionario.setSurrendersFocusOnKeystroke(true);
		tabelaFuncionario.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID","NOME","CPF","RG","TELEFONE","UNIDADE" ,"HR.ENTRADA","HR.SAIDA"}){
			public boolean isCellEditable(int row, int col){
				return false;
			}
		});
		//Bloqueia a rendenização das tabelas
		tabelaFuncionario.getTableHeader().setResizingAllowed(false);
		//Bloqueia a reordenação das tabelas
		tabelaFuncionario.getTableHeader().setReorderingAllowed(false);
		//tamanho de cara coluna
		tabelaFuncionario.getColumnModel().getColumn(0).setPreferredWidth(30);
		tabelaFuncionario.getColumnModel().getColumn(1).setPreferredWidth(80);
		tabelaFuncionario.getColumnModel().getColumn(2).setPreferredWidth(80);
		tabelaFuncionario.getColumnModel().getColumn(3).setPreferredWidth(80);	
		tabelaFuncionario.getColumnModel().getColumn(4).setPreferredWidth(80);
		tabelaFuncionario.getColumnModel().getColumn(5).setPreferredWidth(100);
		tabelaFuncionario.getColumnModel().getColumn(6).setPreferredWidth(80);
		tabelaFuncionario.getColumnModel().getColumn(7).setPreferredWidth(60);
		
		scrollTabelaFuncionario = new JScrollPane(tabelaFuncionario);
		scrollTabelaFuncionario.setBounds(10,116,678,260);
		

		formCadFunc.getContentPane().add(scrollTabelaFuncionario);
		
		JLabel lblLogoCadFunc = new JLabel("");
		lblLogoCadFunc.setIcon(new ImageIcon(FrmCadastroFuncionario.class.getResource("/image/Logo_CadFunci_240x81.fw.png")));
		lblLogoCadFunc.setBounds(228, 0, 240, 92);
		formCadFunc.getContentPane().add(lblLogoCadFunc);
		
		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(FrmCadastroFuncionario.class.getResource("/image/Fundo_MarcaDagua_G.fw.png")));
		lblFundo.setBounds(0, 0, 697, 387);
		formCadFunc.getContentPane().add(lblFundo);
		
		
				
		Vector<Funcionario>vetor = new Vector<>();
		DaoFuncionario daoFuncionario = new DaoFuncionario();
		//retorna em um vetor todos os funcionários cadastrados
		vetor = daoFuncionario.funcionarios();
		//preenche a tabela de funcionários com os dados
		daoFuncionario.preencherTabela(vetor, tabelaFuncionario);
		
	}
}
