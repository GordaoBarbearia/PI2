package formularios;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import objetos.Agendamento;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import DAO.DaoAgendamento;
import DAO.DaoClientes;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.toedter.calendar.JCalendar;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JCheckBox;

public class FrmAgendamento {

	static JFrame formPrincipal;
	private JScrollPane scroll;
	private JTable tabelaPrincipal;
	private JMenuBar menuBarPrincipal;
	private JMenu mnCadastros;
	private JMenuItem mntmClientes;
	private JTextField txtCliente;
	private JFormattedTextField txtHorarioInicio;
	private JFormattedTextField txtHorarioFim;
	private JFormattedTextField txtCpf;
	private ArrayList<String> arrayStatus;
	private ArrayList<String> arrayUnidade;
	private ArrayList<String> arrayFuncionario;
	private ArrayList<String> arrayServicos;
	private String idCliente;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAgendamento window = new FrmAgendamento();
					window.formPrincipal.setVisible(true);
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
	 * @throws Exception
	 */
	public FrmAgendamento() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 */
	private void initialize() throws Exception {

		// ---------------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------------
		// Criando Componentes
		formPrincipal = new JFrame();
		formPrincipal.setTitle("Barbearia O Gord\u00E3o");
		formPrincipal.setBounds(100, 100, 1061, 661);
		formPrincipal.getContentPane().setLayout(null);

		tabelaPrincipal = new JTable(0, 2);
		tabelaPrincipal.setBounds(407, 111, 243, 250);
		tabelaPrincipal.setSurrendersFocusOnKeystroke(true);
		tabelaPrincipal.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "DATA", "INICIO", "FINAL",
				"FUNCIONÁRIO", "CLIENTE", "UNIDADE", "SERVIÇO", "STATUS", "" }) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}

		});
		
		
		formPrincipal.getContentPane().add(tabelaPrincipal);

		// Bloqueia a rendenização das tabelas
		tabelaPrincipal.getTableHeader().setResizingAllowed(false);
		// Bloqueia a reordenação das tabelas
		tabelaPrincipal.getTableHeader().setReorderingAllowed(false);

		tabelaPrincipal.getColumnModel().getColumn(0).setPreferredWidth(90);
		tabelaPrincipal.getColumnModel().getColumn(1).setPreferredWidth(50);
		tabelaPrincipal.getColumnModel().getColumn(2).setPreferredWidth(50);
		tabelaPrincipal.getColumnModel().getColumn(3).setPreferredWidth(90);
		tabelaPrincipal.getColumnModel().getColumn(4).setPreferredWidth(90);
		tabelaPrincipal.getColumnModel().getColumn(5).setPreferredWidth(110);
		tabelaPrincipal.getColumnModel().getColumn(6).setPreferredWidth(90);
		tabelaPrincipal.getColumnModel().getColumn(7).setPreferredWidth(90);

		tabelaPrincipal.getColumnModel().getColumn(8).setMaxWidth(0);
		tabelaPrincipal.getColumnModel().getColumn(8).setMinWidth(0);
		tabelaPrincipal.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);

		JComboBox<String> cboFuncionario = new JComboBox<String>();
		cboFuncionario.setEnabled(false);
		cboFuncionario.setBounds(138, 246, 101, 20);
		formPrincipal.getContentPane().add(cboFuncionario);

		MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
		txtCpf = new JFormattedTextField(maskCpf);
		txtCpf.setFocusLostBehavior(JFormattedTextField.COMMIT);
		txtCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					txtCliente.setText(null);
					// Chamando a classe de interação com o banco de dados
					DaoClientes daoClientes = new DaoClientes();

					// pegando o valor do campo txtConsultaCpf
					String consultaCpf = (txtCpf.getText().replaceAll("[./-]", ""));

					// chamando o metodo de pesquisa da classe daoClientes,
					// passando
					// o cpf como parametross
					// e atribuindo o resultado Boleano a variavel
					// "pesquisaCliente"
					ArrayList<String> pesquisaCliente;

					pesquisaCliente = daoClientes.pesquisarCliente(consultaCpf);

					// se o resultado da pesquisa for verdadeiro, apresento a
					// mensagem que o cliente já possui cadastro, se não,
					// informa
					// que não possui cadastro para aquele cliente
					if (pesquisaCliente.size() > 0) {

						txtCliente.setText(pesquisaCliente.get(1).toString());
						idCliente = pesquisaCliente.get(0).toString();

					} else {
						int confirmacao = JOptionPane.showConfirmDialog(null,
								"Cliente não cadastrado, deseja cadastra-lo?", "Gordão Barbearia",
								JOptionPane.YES_NO_OPTION);

						if (confirmacao == JOptionPane.YES_OPTION) {
							FrmCadastroCliente frmCadastroCliente = new FrmCadastroCliente();
							frmCadastroCliente.formCadCli.setVisible(true);
						} else {
							txtCpf.setText(null);
						}
					}
				} catch (SQLException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});


		JComboBox<String> cboUnidade = new JComboBox<String>();
		cboUnidade.setEnabled(false);
		cboUnidade.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					if (cboUnidade.getSelectedItem() != null) {
						if (cboUnidade.getSelectedItem().equals("")) {
							cboFuncionario.removeAllItems();
						} else {
							cboFuncionario.removeAllItems();
							int posicaoArray = cboUnidade.getSelectedIndex();
							String idUnidade = arrayUnidade.get(posicaoArray);
							DaoAgendamento daoAgendamento = new DaoAgendamento();
							arrayFuncionario = daoAgendamento.atualizarComboFuncionario(cboFuncionario, idUnidade);

						}
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		cboUnidade.setBounds(10, 244, 112, 20);
		formPrincipal.getContentPane().add(cboUnidade);

		JComboBox<String> cboStatus = new JComboBox<String>();
		cboStatus.setEnabled(false);
		cboStatus.setEditable(true);
		cboStatus.setBounds(159, 291, 123, 20);
		formPrincipal.getContentPane().add(cboStatus);

		JComboBox<String> cboServico = new JComboBox<String>();
		cboServico.setEnabled(false);
		cboServico.setBounds(260, 246, 101, 20);
		formPrincipal.getContentPane().add(cboServico);
		// txtCpf.setText(frmPrincipal.consultaCpf.toString());
		txtCpf.setColumns(10);
		txtCpf.setBounds(10, 200, 108, 20);
		formPrincipal.getContentPane().add(txtCpf);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(10, 185, 46, 14);
		formPrincipal.getContentPane().add(lblCpf);

		scroll = new JScrollPane(tabelaPrincipal);
		scroll.setBounds(402, 259, 629, 337);
		formPrincipal.getContentPane().add(scroll);

		JCalendar calendar = new JCalendar();
		calendar.setBounds(10, 371, 370, 223);
		formPrincipal.getContentPane().add(calendar);

		txtCliente = new JTextField();
		txtCliente.setEditable(false);
		txtCliente.setBounds(138, 200, 108, 20);
		formPrincipal.getContentPane().add(txtCliente);
		txtCliente.setColumns(10);

		MaskFormatter maskHora = new MaskFormatter("##:##");
		txtHorarioInicio = new JFormattedTextField(maskHora);
		txtHorarioInicio.setEnabled(false);
		txtHorarioInicio.setFocusLostBehavior(JFormattedTextField.COMMIT);
		txtHorarioInicio.setColumns(10);
		txtHorarioInicio.setBounds(11, 290, 50, 20);
		formPrincipal.getContentPane().add(txtHorarioInicio);

		txtHorarioFim = new JFormattedTextField(maskHora);
		txtHorarioFim.setEnabled(false);
		txtHorarioFim.setFocusLostBehavior(JFormattedTextField.COMMIT);
		txtHorarioFim.setColumns(10);
		txtHorarioFim.setBounds(89, 291, 50, 20);
		formPrincipal.getContentPane().add(txtHorarioFim);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					int codFuncionario = cboFuncionario.getSelectedIndex();
					String funcionario = arrayFuncionario.get(codFuncionario);

					int codServicos = cboServico.getSelectedIndex();
					String servicos = arrayServicos.get(codServicos);

					String horaInicio = txtHorarioInicio.getText().trim().replaceAll("/D", "");
					String horaFim = txtHorarioFim.getText().trim().replaceAll("/D", "");

					int codStatus = cboStatus.getSelectedIndex();
					String status = arrayStatus.get(codStatus);

					String dataCalendario = null;
					SimpleDateFormat formatoData = new SimpleDateFormat("yyyy/MM/dd");
					dataCalendario = formatoData.format(calendar.getDate());

					DaoAgendamento daoAgendamento = new DaoAgendamento();
					
					Agendamento agendamento = new Agendamento(funcionario, idCliente, status, servicos, dataCalendario,
							horaInicio, horaFim);
					boolean validarHorario = daoAgendamento.verificarDisponibilidade(funcionario, dataCalendario, horaInicio, horaFim);
					
					if(!validarHorario){
						daoAgendamento.salvarAgendamento(agendamento);
						daoAgendamento.atualizarTabela(tabelaPrincipal);
					}else{
						JOptionPane.showMessageDialog(null, "Horario indisponivel");
					}
					
					

				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		JCheckBox chckbxFilaEspera = new JCheckBox("Fila espera");
		chckbxFilaEspera.setEnabled(false);
		chckbxFilaEspera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (chckbxFilaEspera.isSelected()) {
					cboStatus.setSelectedIndex(3);
				} else {
					cboStatus.setSelectedIndex(0);
				}
			}
		});
		chckbxFilaEspera.setBounds(288, 290, 89, 23);
		formPrincipal.getContentPane().add(chckbxFilaEspera);
		btnSalvar.setBounds(208, 337, 89, 23);
		formPrincipal.getContentPane().add(btnSalvar);

		JButton btnNovoCancelar = new JButton("Novo");
		btnNovoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if (btnNovoCancelar.getText().equals("Novo")) {
						btnNovoCancelar.setText("Cancelar");
						btnSalvar.setEnabled(true);
						cboFuncionario.setEnabled(true);
						cboServico.setEnabled(true);
						cboStatus.setEnabled(true);
						cboUnidade.setEnabled(true);
						txtHorarioInicio.setEnabled(true);
						txtHorarioFim.setEnabled(true);
						DaoAgendamento daoAgendamento = new DaoAgendamento();
						daoAgendamento.atualizarTabela(tabelaPrincipal);
						arrayStatus = daoAgendamento.atualiazaComboStatus(cboStatus);
						arrayUnidade = daoAgendamento.atualiazaComboUnidade(cboUnidade);
						arrayServicos = daoAgendamento.atualizarComboServicos(cboServico);
						cboStatus.setSelectedIndex(0);
						chckbxFilaEspera.setEnabled(true);
					} else {
						btnNovoCancelar.setText("Novo");
						cboFuncionario.setEnabled(false);
						cboServico.setEnabled(false);
						cboStatus.setEnabled(false);
						cboUnidade.setEnabled(false);
						btnSalvar.setEnabled(false);
						chckbxFilaEspera.setEnabled(false);
						cboFuncionario.removeAllItems();
						cboUnidade.removeAllItems();
						cboStatus.removeAllItems();
						cboServico.removeAllItems();
						txtHorarioInicio.setEnabled(false);
						txtHorarioFim.setEnabled(false);

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNovoCancelar.setBounds(10, 337, 89, 23);
		formPrincipal.getContentPane().add(btnNovoCancelar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.setBounds(109, 337, 89, 23);
		formPrincipal.getContentPane().add(btnEditar);

		JLabel lblServicos = new JLabel("Servi\u00E7os");
		lblServicos.setBounds(260, 231, 81, 14);
		formPrincipal.getContentPane().add(lblServicos);

		JLabel lblHorrioSaida = new JLabel("Hor\u00E1rio fim");
		lblHorrioSaida.setBounds(89, 276, 71, 14);
		formPrincipal.getContentPane().add(lblHorrioSaida);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(159, 276, 81, 14);
		formPrincipal.getContentPane().add(lblStatus);

		JLabel lblUnidade = new JLabel("Unidade");
		lblUnidade.setBounds(10, 229, 112, 14);
		formPrincipal.getContentPane().add(lblUnidade);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(138, 185, 46, 14);
		formPrincipal.getContentPane().add(lblCliente);

		JLabel lblHorrio = new JLabel("Inicio");
		lblHorrio.setBounds(11, 275, 60, 14);
		formPrincipal.getContentPane().add(lblHorrio);

		JLabel lblFuncionrio = new JLabel("Funcion\u00E1rio");
		lblFuncionrio.setBounds(138, 231, 81, 14);
		formPrincipal.getContentPane().add(lblFuncionrio);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(FrmAgendamento.class.getResource("/image/Logo_entalhe_403x132.fw.png")));
		lblLogo.setBounds(301, 3, 403, 132);
		formPrincipal.getContentPane().add(lblLogo);

		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(FrmAgendamento.class.getResource("/image/Fundo_1024.png")));
		lblFundo.setBounds(0, -4, 1031, 606);
		formPrincipal.getContentPane().add(lblFundo);

		menuBarPrincipal = new JMenuBar();
		formPrincipal.setJMenuBar(menuBarPrincipal);

		mnCadastros = new JMenu("Cadastros");
		menuBarPrincipal.add(mnCadastros);

		mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCadastroCliente frmCadastroCliente;
				try {
					frmCadastroCliente = new FrmCadastroCliente();
					frmCadastroCliente.formCadCli.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (java.text.ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnCadastros.add(mntmClientes);

		JMenu mnAgendamento = new JMenu("Relatorios");
		menuBarPrincipal.add(mnAgendamento);

		JMenuItem mntmNovoAgendamento = new JMenuItem("Gerar relat\u00F3rio");
		mnAgendamento.add(mntmNovoAgendamento);

		tabelaPrincipal.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				DefaultTableModel model = (DefaultTableModel) tabelaPrincipal.getModel();
				if (model.getRowCount() > 0) {
					if (tabelaPrincipal.getSelectedRow() >= 0) {
						cboFuncionario.removeAllItems();
						btnEditar.setEnabled(true);
						txtHorarioInicio.setText(
								(String) tabelaPrincipal.getModel().getValueAt(tabelaPrincipal.getSelectedRow(), 1));
						txtHorarioFim.setText(
								(String) tabelaPrincipal.getModel().getValueAt(tabelaPrincipal.getSelectedRow(), 2));
						cboFuncionario.addItem(
								(String) tabelaPrincipal.getModel().getValueAt(tabelaPrincipal.getSelectedRow(), 3));
					}
				}
			}
		});

		// atualizando combobox do status
		DaoAgendamento daoAgendamento = new DaoAgendamento();
		daoAgendamento.atualizarTabela(tabelaPrincipal);
		daoAgendamento.getNewRenderedTable(tabelaPrincipal);

		formPrincipal.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { txtCpf, cboUnidade,
				cboFuncionario, cboServico, txtHorarioInicio, txtHorarioFim, btnSalvar, btnNovoCancelar, btnEditar }));

	}
}
