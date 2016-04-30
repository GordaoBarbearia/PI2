package formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmCadastroCliente {

	static JFrame formCadCli;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtTelefone;
	private JTable tableNome;
	JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCadastroCliente window = new FrmCadastroCliente();
					window.formCadCli.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmCadastroCliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		formCadCli = new JFrame();
		formCadCli.setResizable(false);
		formCadCli.setTitle("Barbearia O Gord\u00E3o - Cadastro Cliente");
		formCadCli.setBounds(100, 100, 573, 382);
		formCadCli.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		formCadCli.getContentPane().setLayout(null);
		
		JButton btnsalvar = new JButton("Salvar");
		btnsalvar.setEnabled(false);
		btnsalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		btnsalvar.setBounds(12, 320, 93, 23);
		formCadCli.getContentPane().add(btnsalvar);
		
		JButton btnCancelarNovo = new JButton("Novo");
		btnCancelarNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(btnCancelarNovo.getText().equals("Novo")){
					btnCancelarNovo.setText("Cancelar");
					btnsalvar.setEnabled(true);
					txtCpf.setEnabled(true);
					txtNome.setEnabled(true);
					txtTelefone.setEnabled(true);
				}else{
					btnCancelarNovo.setText("Novo");
					btnsalvar.setEnabled(false);
					txtCpf.setEnabled(false);
					txtNome.setEnabled(false);
					txtTelefone.setEnabled(false);
					
				}

			}
		});
		btnCancelarNovo.setBounds(12, 252, 93, 23);
		formCadCli.getContentPane().add(btnCancelarNovo);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEditar.setBounds(12, 286, 93, 23);
		formCadCli.getContentPane().add(btnEditar);
		
		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setBounds(12, 169, 105, 23);
		formCadCli.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(14, 155, 46, 14);
		formCadCli.getContentPane().add(lblNome);
		
		txtCpf = new JTextField();
		txtCpf.setEnabled(false);
		txtCpf.setColumns(10);
		txtCpf.setBounds(12, 121, 105, 23);
		formCadCli.getContentPane().add(txtCpf);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(14, 107, 46, 14);
		formCadCli.getContentPane().add(lblCpf);
		
		txtTelefone = new JTextField();
		txtTelefone.setEnabled(false);
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(12, 218, 105, 23);
		formCadCli.getContentPane().add(txtTelefone);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(12, 203, 59, 14);
		formCadCli.getContentPane().add(lblTelefone);
		
		tableNome = new JTable(0,0);
		tableNome.setBounds(407, 111, 243, 250);
		tableNome.setSurrendersFocusOnKeystroke(true);
		tableNome.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Nome", "CPF","Telefone"}){
			public boolean isCellEditable(int row, int col){
				return false;
			}
		
		});
		formCadCli.getContentPane().add(tableNome);
		scrollPane = new JScrollPane(tableNome);
		scrollPane.setBounds(165, 108, 391, 235);
		formCadCli.getContentPane().add(scrollPane);
		
		JLabel lblLogoCadCli = new JLabel("");
		lblLogoCadCli.setIcon(new ImageIcon(FrmCadastroCliente.class.getResource("/image/Logo_CadCli_240X81.png")));
		lblLogoCadCli.setBounds(163, 1, 240, 81);
		formCadCli.getContentPane().add(lblLogoCadCli);
		
		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(FrmCadastroCliente.class.getResource("/image/Fundo_MarcaDagua_G.fw.png")));
		lblFundo.setBounds(0, -2, 567, 366);
		formCadCli.getContentPane().add(lblFundo);
	}

}
