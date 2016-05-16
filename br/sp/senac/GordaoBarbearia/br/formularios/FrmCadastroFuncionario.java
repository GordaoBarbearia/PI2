package formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class FrmCadastroFuncionario {

	static JFrame formCadFunc;
	private JTextField txtNomeFunc;
	private JTextField txtCpfFunc;
	private JTextField txtRgFunc;
	private JTextField txtTelFunc;
	private JScrollPane scrollTabelaFuncionario;
	private JTable tabelaFuncionario;
	private JTextField txtHorarioEntrada;
	private JTextField txtHorarioSaida;

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
		formCadFunc.setTitle("Barbearia O Gord\u00E3o - Cadastro de Funcion\u00E1rios");
		formCadFunc.setBounds(100, 100, 709, 412);
		formCadFunc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		formCadFunc.getContentPane().setLayout(null);
		
		tabelaFuncionario = new JTable(0,0);
		tabelaFuncionario.setBounds(50,50,50,50);
		tabelaFuncionario.setSurrendersFocusOnKeystroke(true);
		tabelaFuncionario.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Nome","CPF","RG","Telefone"}){
			public boolean isCellEditable(int row, int col){
				return false;
			}
		});
		//Bloqueia a rendenização das tabelas
		tabelaFuncionario.getTableHeader().setResizingAllowed(false);
		//Bloqueia a reordenação das tabelas
		tabelaFuncionario.getTableHeader().setReorderingAllowed(false);
		//tamanho de cara coluna
		tabelaFuncionario.getColumnModel().getColumn(0).setPreferredWidth(70);
		tabelaFuncionario.getColumnModel().getColumn(1).setPreferredWidth(70);
		tabelaFuncionario.getColumnModel().getColumn(2).setPreferredWidth(70);
		tabelaFuncionario.getColumnModel().getColumn(3).setPreferredWidth(70);		
		
		scrollTabelaFuncionario = new JScrollPane(tabelaFuncionario);
		scrollTabelaFuncionario.setBounds(327,108,356,260);
		

		formCadFunc.getContentPane().add(scrollTabelaFuncionario);
		
		
		txtNomeFunc = new JTextField();
		txtNomeFunc.setColumns(10);
		txtNomeFunc.setBounds(10, 111, 111, 23);
		formCadFunc.getContentPane().add(txtNomeFunc);
		
		txtHorarioEntrada = new JTextField();
		txtHorarioEntrada.setColumns(10);
		txtHorarioEntrada.setBounds(10, 217, 77, 23);
		formCadFunc.getContentPane().add(txtHorarioEntrada);
		
		txtHorarioSaida = new JTextField();
		txtHorarioSaida.setColumns(10);
		txtHorarioSaida.setBounds(171, 217, 77, 23);
		formCadFunc.getContentPane().add(txtHorarioSaida);
		
		JLabel lblHorarioSada = new JLabel("Horario sa\u00EDda");
		lblHorarioSada.setBounds(173, 202, 109, 14);
		formCadFunc.getContentPane().add(lblHorarioSada);
		
		JLabel lblNomeFunc = new JLabel("Nome");
		lblNomeFunc.setBounds(12, 97, 46, 14);
		formCadFunc.getContentPane().add(lblNomeFunc);
		
		JLabel lblCpfFunc = new JLabel("CPF");
		lblCpfFunc.setBounds(12, 145, 46, 14);
		formCadFunc.getContentPane().add(lblCpfFunc);
		
		JLabel lblHorarioEntrada = new JLabel("Horario entrada");
		lblHorarioEntrada.setBounds(12, 202, 109, 14);
		formCadFunc.getContentPane().add(lblHorarioEntrada);
		
		txtCpfFunc = new JTextField();
		txtCpfFunc.setColumns(10);
		txtCpfFunc.setBounds(10, 159, 111, 23);
		formCadFunc.getContentPane().add(txtCpfFunc);
		
		JLabel lblRgFunc = new JLabel("RG");
		lblRgFunc.setBounds(161, 144, 59, 14);
		formCadFunc.getContentPane().add(lblRgFunc);
		
		txtRgFunc = new JTextField();
		txtRgFunc.setColumns(10);
		txtRgFunc.setBounds(161, 159, 101, 23);
		formCadFunc.getContentPane().add(txtRgFunc);
		
		txtTelFunc = new JTextField();
		txtTelFunc.setColumns(10);
		txtTelFunc.setBounds(161, 111, 101, 23);
		formCadFunc.getContentPane().add(txtTelFunc);
		
		JLabel lblTelFunc = new JLabel("Telefone");
		lblTelFunc.setBounds(161, 96, 59, 14);
		formCadFunc.getContentPane().add(lblTelFunc);
		
		JButton btnSalvarFunc = new JButton("Salvar");
		btnSalvarFunc.setBounds(10, 265, 95, 25);
		formCadFunc.getContentPane().add(btnSalvarFunc);
		
		JButton btnCancelarFunc = new JButton("Cancelar");
		btnCancelarFunc.setBounds(125, 301, 95, 25);
		formCadFunc.getContentPane().add(btnCancelarFunc);
		
		JButton btnEditarFunc = new JButton("Editar");
		btnEditarFunc.setBounds(125, 265, 95, 25);
		formCadFunc.getContentPane().add(btnEditarFunc);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(10, 301, 95, 25);
		formCadFunc.getContentPane().add(btnExcluir);
		
		JLabel lblLogoCadFunc = new JLabel("");
		lblLogoCadFunc.setIcon(new ImageIcon(FrmCadastroFuncionario.class.getResource("/image/Logo_CadFunci_240x81.fw.png")));
		lblLogoCadFunc.setBounds(229, -6, 240, 92);
		formCadFunc.getContentPane().add(lblLogoCadFunc);
		
		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(FrmCadastroFuncionario.class.getResource("/image/Fundo_MarcaDagua_G.fw.png")));
		lblFundo.setBounds(0, 0, 693, 374);
		formCadFunc.getContentPane().add(lblFundo);
	}
}
