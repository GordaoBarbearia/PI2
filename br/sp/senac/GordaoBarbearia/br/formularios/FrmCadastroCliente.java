package formularios;

import java.awt.EventQueue;
import java.awt.TextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import DAO.DaoClientes;
import objetos.Cliente;
import objetos.Funcoes;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class FrmCadastroCliente {

	static JFrame formCadCli;
	private JTextField txtNome;
	private JFormattedTextField txtCpf;
	private JTextField txtTelefone;
	private JTable tabelaNome;
	JScrollPane scrollPane;

	/**
	 * Launch the application.
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

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
	 * 
	 * @throws ParseException
	 */
	public FrmCadastroCliente() throws ParseException {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws ParseException
	 */
	@SuppressWarnings("serial")
	private void initialize() throws ParseException {
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

				String nomeCli = txtNome.getText();
				String cpfCli = txtCpf.getText();
				String telefoneCli = txtTelefone.getText();

				Funcoes funcoes = new Funcoes();
				boolean salvar = false;
				boolean validarCampos = funcoes.validarCampos(txtNome, txtCpf, txtTelefone);

				if (validarCampos) {
					Cliente cliente = new Cliente(cpfCli, nomeCli, telefoneCli);
					DaoClientes daoClientes = new DaoClientes();
					salvar = daoClientes.salvarCliente(cliente);

					if (salvar) {
						JOptionPane.showMessageDialog(null, "Cadastrado com sucesso", "Gordão Barbearia",
								JOptionPane.INFORMATION_MESSAGE);
						funcoes.limparCampos(txtNome, txtCpf, txtTelefone);
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao efetuaro o cadastro", "Gordão Barbearia",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS CAMPOS", "Gordão Barbearia",
							JOptionPane.ERROR_MESSAGE);
				}
				atualizarTableCliente(tabelaNome);
			}
		});
		btnsalvar.setBounds(12, 320, 93, 23);
		formCadCli.getContentPane().add(btnsalvar);

		JButton btnCancelarNovo = new JButton("Novo");
		btnCancelarNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Funcoes funcoes = new Funcoes();
				if (btnCancelarNovo.getText().equals("Novo")) {
					btnCancelarNovo.setText("Cancelar");
					btnsalvar.setEnabled(true);
					funcoes.desbloquearCampos(txtNome, txtCpf, txtTelefone);
					txtCpf.requestFocus();
				} else {
					btnCancelarNovo.setText("Novo");
					btnsalvar.setEnabled(false);
					funcoes.bloquearCampos(txtNome, txtCpf, txtTelefone);
					funcoes.limparCampos(txtNome, txtCpf, txtTelefone);
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

		MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
		txtCpf = new JFormattedTextField(maskCpf);
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

		tabelaNome = new JTable(0, 0);
		tabelaNome.setBounds(407, 111, 243, 250);
		tabelaNome.setSurrendersFocusOnKeystroke(true);
		tabelaNome.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nome", "CPF", "Telefone" }) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}

		});
		formCadCli.getContentPane().add(tabelaNome);
		scrollPane = new JScrollPane(tabelaNome);
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

		atualizarTableCliente(tabelaNome);

	}

	static void atualizarTableCliente(JTable tableNome) {
		DaoClientes daoClientes = new DaoClientes();
		try {
			daoClientes.atualizarTabelas(tableNome);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
