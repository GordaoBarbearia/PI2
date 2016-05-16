package formularios;

import java.awt.EventQueue;
import java.awt.TextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import DAO.DaoClientes;
import objetos.Cliente;
import objetos.Funcoes;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ListSelectionModel;

public class FrmCadastroCliente {

	static JFrame formCadCli;
	private String testeCpf;
	private JTextField txtNome;
	private JFormattedTextField txtCpf;
	private JFormattedTextField txtTelefone;
	private JTable tabelaNome;
	JScrollPane scrollTable;

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
		formCadCli.setTitle("Gord\u00E3o barbearia - Clientes");
		formCadCli.setBounds(100, 100, 573, 382);
		formCadCli.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		formCadCli.getContentPane().setLayout(null);

		JButton btnCancelarNovo = new JButton("Novo");
		JButton btnEditar = new JButton("Editar");
		JButton btnsalvar = new JButton("Salvar");

		btnsalvar.setEnabled(false);
		btnsalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String nomeCli = txtNome.getText().toUpperCase();
					String cpfCli = txtCpf.getText().replaceAll("[./-]", "").toUpperCase();
					String telefoneCli = txtTelefone.getText().replaceAll("[./-]", "").toUpperCase();

					Funcoes funcoes = new Funcoes();
					boolean salvar = false;
					boolean validarCampos = funcoes.validarCampos(txtNome, txtCpf, txtTelefone);

					DaoClientes daoClientes = new DaoClientes();
					boolean validarDuplicidade;

					if (validarCampos) {
						validarDuplicidade = validarDuplicidade(cpfCli);
						Cliente cliente = new Cliente(cpfCli, nomeCli, telefoneCli);
						if (validarDuplicidade) {
							salvar = daoClientes.salvarCliente(cliente);
							if (salvar) {
								JOptionPane.showMessageDialog(null, "Cadastrado com sucesso", "Gordão Barbearia",
										JOptionPane.INFORMATION_MESSAGE);
								funcoes.limparCampos(txtNome, txtCpf, txtTelefone);
								funcoes.bloquearCampos(txtNome, txtCpf, txtTelefone);
								btnCancelarNovo.setText("Novo");
								btnsalvar.setEnabled(false);
								tabelaNome.setEnabled(true);
							} else {
								JOptionPane.showMessageDialog(null, "Erro ao efetuaro o cadastro", "Gordão Barbearia",
										JOptionPane.INFORMATION_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "CPF já cadastrado", "Gordão Barbearia",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS CAMPOS", "Gordão Barbearia",
								JOptionPane.ERROR_MESSAGE);
					}

					atualizarTableCliente(tabelaNome);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnsalvar.setBounds(12, 320, 93, 23);
		formCadCli.getContentPane().add(btnsalvar);

		btnCancelarNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Funcoes funcoes = new Funcoes();
				if (btnCancelarNovo.getText().equals("Novo")) {
					atualizarTableCliente(tabelaNome);
					btnCancelarNovo.setText("Cancelar");
					btnsalvar.setEnabled(true);
					btnEditar.setEnabled(false);
					funcoes.desbloquearCampos(txtNome, txtCpf, txtTelefone);
					funcoes.limparCampos(txtNome, txtCpf, txtTelefone);
					txtCpf.requestFocus();
					tabelaNome.setEnabled(false);

				} else {
					atualizarTableCliente(tabelaNome);
					btnCancelarNovo.setText("Novo");
					btnEditar.setText("Editar");
					btnsalvar.setEnabled(false);
					btnEditar.setEnabled(false);
					funcoes.bloquearCampos(txtNome, txtCpf, txtTelefone);
					funcoes.limparCampos(txtNome, txtCpf, txtTelefone);
					tabelaNome.setEnabled(true);

				}
			}
		});
		btnCancelarNovo.setBounds(12, 252, 93, 23);
		formCadCli.getContentPane().add(btnCancelarNovo);

		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
								
				try {
					Funcoes funcoes = new Funcoes();
					if (btnEditar.getText().equals("Editar")) {
						funcoes.desbloquearCampos(txtNome, txtCpf, txtTelefone);
						btnEditar.setText("Confirmar");
						btnCancelarNovo.setText("Cancelar");
						tabelaNome.setEnabled(false);
						testeCpf = txtCpf.getText().replaceAll("[./-]", "");
						//System.out.println(testeCpf);
					} else {
						String nomeCli = txtNome.getText();
						String cpfCli = txtCpf.getText().replaceAll("[./-]", "");
						String telefoneCli = txtTelefone.getText().replaceAll("[./-]", "");

						String idCliente = (String) tabelaNome.getModel().getValueAt(tabelaNome.getSelectedRow(), 0);

						DaoClientes daoClientes = new DaoClientes();
						Cliente cliente = new Cliente(cpfCli, nomeCli, telefoneCli);
						boolean validarDulicidade = true;
						
						boolean validarCampos = funcoes.validarCampos(txtNome, txtCpf, txtTelefone);
						
						//System.out.println(testeCpf);
						
						if (validarCampos) {
							if(!testeCpf.equals(cpfCli)){
								validarDulicidade = validarDuplicidade(cpfCli);
							}							

							if (validarDulicidade) {
								boolean editar = daoClientes.editarCliente(cliente, idCliente);
								if (editar) {
									JOptionPane.showMessageDialog(null, "Alterado com sucesso", "Gordão Barbearia",
											JOptionPane.INFORMATION_MESSAGE);
									atualizarTableCliente(tabelaNome);
									funcoes.bloquearCampos(txtNome, txtCpf, txtTelefone);
									funcoes.limparCampos(txtNome, txtCpf, txtTelefone);
									btnCancelarNovo.setText("Novo");
									btnEditar.setText("Editar");
									btnEditar.setEnabled(false);
									tabelaNome.setEnabled(true);

								}
							}else{
								JOptionPane.showMessageDialog(null, "CPF já cadastrado", "Gordão Barbearia",
										JOptionPane.ERROR_MESSAGE);
							}

						} else {
							JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS CAMPOS", "Gordão Barbearia",
									JOptionPane.ERROR_MESSAGE);
						}

					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		txtCpf.setFocusLostBehavior(JFormattedTextField.COMMIT);
		txtCpf.setEnabled(false);
		txtCpf.setColumns(10);
		txtCpf.setBounds(12, 121, 105, 23);
		formCadCli.getContentPane().add(txtCpf);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(14, 107, 46, 14);
		formCadCli.getContentPane().add(lblCpf);

		MaskFormatter maskTelefone = new MaskFormatter("##-####-#####");
		txtTelefone = new JFormattedTextField(maskTelefone);
		txtTelefone.setFocusLostBehavior(JFormattedTextField.COMMIT);
		txtTelefone.setEnabled(false);
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(12, 218, 105, 23);
		formCadCli.getContentPane().add(txtTelefone);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(12, 203, 59, 14);
		formCadCli.getContentPane().add(lblTelefone);

		tabelaNome = new JTable(0, 0);
		tabelaNome.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaNome.setBounds(407, 111, 243, 250);
		tabelaNome.setSurrendersFocusOnKeystroke(true);
		tabelaNome.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nome", "CPF", "Telefone" }) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}

		});
		
		tabelaNome.getTableHeader().setReorderingAllowed(false);
		tabelaNome.getColumnModel().getColumn(0).setResizable(false);
		tabelaNome.getColumnModel().getColumn(1).setResizable(false);
		tabelaNome.getColumnModel().getColumn(2).setResizable(false);
		tabelaNome.getColumnModel().getColumn(3).setResizable(false);
		
		tabelaNome.getColumnModel().getColumn(0).setMaxWidth(50);
		tabelaNome.getColumnModel().getColumn(1).setMaxWidth(130);
		tabelaNome.getColumnModel().getColumn(2).setMaxWidth(110);
		tabelaNome.getColumnModel().getColumn(3).setMaxWidth(110);
		
		
		formCadCli.getContentPane().add(tabelaNome);
		scrollTable = new JScrollPane(tabelaNome);
		scrollTable.setBounds(165, 108, 391, 235);
		formCadCli.getContentPane().add(scrollTable);

		JLabel lblLogoCadCli = new JLabel("");
		lblLogoCadCli.setIcon(new ImageIcon(FrmCadastroCliente.class.getResource("/image/Logo_CadCli_240X81.png")));
		lblLogoCadCli.setBounds(163, 1, 240, 81);
		formCadCli.getContentPane().add(lblLogoCadCli);

		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(FrmCadastroCliente.class.getResource("/image/Fundo_MarcaDagua_G.fw.png")));
		lblFundo.setBounds(0, -2, 567, 366);
		formCadCli.getContentPane().add(lblFundo);
		atualizarTableCliente(tabelaNome);

		tabelaNome.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				btnEditar.setEnabled(true);
				DefaultTableModel model = (DefaultTableModel) tabelaNome.getModel();

				if (model.getRowCount() > 0) {
					if (tabelaNome.getSelectedRow() >= 0) {
						String cpf = (String) tabelaNome.getModel().getValueAt(tabelaNome.getSelectedRow(), 2);
						String nome = (String) tabelaNome.getModel().getValueAt(tabelaNome.getSelectedRow(), 1);
						String telefone = (String) tabelaNome.getModel().getValueAt(tabelaNome.getSelectedRow(), 3);

						
						txtCpf.setText("");
						txtNome.setText("");
						txtTelefone.setText("");
						txtCpf.setText(cpf);
						txtNome.setText(nome);
						txtTelefone.setText(telefone);
					}
				}

			}
		});

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
	static boolean validarDuplicidade(String cpf) throws SQLException {

		DaoClientes daoClientes = new DaoClientes();
		ArrayList<String> validarDupli = daoClientes.pesquisarCliente(cpf);
		if (validarDupli.size() > 0) {
			return false;
		}
		return true;

	}
}
