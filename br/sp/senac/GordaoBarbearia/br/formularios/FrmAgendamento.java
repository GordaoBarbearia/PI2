package formularios;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
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
import org.eclipse.wb.swing.FocusTraversalOnArray;
import DAO.DaoAgendamento;
import DAO.DaoClientes;
import modelo.Agendamento;
import modelo.Funcionario;
import modelo.Funcoes;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.toedter.calendar.JCalendar;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Cursor;
import java.awt.Font;

/**
 * @author NH2
 */

public class FrmAgendamento {

	static JFrame formAgendamento;
	private JScrollPane scroll;
	private JTable tabelaAgendamento;
	private JMenuBar menuBarPrincipal;
	private JMenu mnCadastros;
	private JMenuItem mntmClientes;
	private JTextField txtCliente;
	private JFormattedTextField txtHorarioInicio;
	private JFormattedTextField txtHorarioFim;
	static JFormattedTextField txtCpf;
	private ArrayList<String> arrayStatus;
	private ArrayList<String> arrayUnidade;
	private Vector<Funcionario> vectorFuncionario;
	private ArrayList<String> arrayServicos;
	private String idCliente;
	DaoAgendamento daoAgendamento = new DaoAgendamento();
	Funcoes funcoes = new Funcoes();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAgendamento window = new FrmAgendamento();
					window.formAgendamento.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
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

		JButton btnSalvar = new JButton("Salvar");
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		JButton btnEditar = new JButton("Editar");

		JComboBox<String> cboUnidade = new JComboBox<String>();
		cboUnidade.setEnabled(false);
		JComboBox<String> cboFuncionario = new JComboBox<String>();
		JComboBox<String> cboStatus = new JComboBox<String>();
		JComboBox<String> cboServico = new JComboBox<String>();

		JCalendar calendar = new JCalendar();
		// ---------------------------------------------------------------------------------------
		// Criando Componentes
		formAgendamento = new JFrame();
		formAgendamento.setResizable(false);
		formAgendamento.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		formAgendamento.setTitle("Gord\u00E3o barbearia - Agendamentos");
		formAgendamento.setBounds(100, 100, 1134, 713);
		formAgendamento.getContentPane().setLayout(null);

		tabelaAgendamento = new JTable(0, 2);
		tabelaAgendamento.setBounds(407, 111, 243, 250);
		tabelaAgendamento.setSurrendersFocusOnKeystroke(true);
		tabelaAgendamento.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "DATA", "INICIO", "FINAL",
				"FUNCIONÁRIO", "CLIENTE", "UNIDADE", "SERVIÇO", "STATUS", "ID", "" }) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}

		});

		formAgendamento.getContentPane().add(tabelaAgendamento);

		// Bloqueia a rendenização das tabelas
		tabelaAgendamento.getTableHeader().setResizingAllowed(false);
		// Bloqueia a reordenação das tabelas
		tabelaAgendamento.getTableHeader().setReorderingAllowed(false);
		tabelaAgendamento.getColumnModel().getColumn(0).setPreferredWidth(70);
		tabelaAgendamento.getColumnModel().getColumn(1).setPreferredWidth(50);
		tabelaAgendamento.getColumnModel().getColumn(2).setPreferredWidth(50);
		tabelaAgendamento.getColumnModel().getColumn(3).setPreferredWidth(110);
		tabelaAgendamento.getColumnModel().getColumn(4).setPreferredWidth(110);
		tabelaAgendamento.getColumnModel().getColumn(5).setPreferredWidth(110);
		tabelaAgendamento.getColumnModel().getColumn(6).setPreferredWidth(60);
		tabelaAgendamento.getColumnModel().getColumn(7).setPreferredWidth(90);
		tabelaAgendamento.getColumnModel().getColumn(8).setMaxWidth(50);
		tabelaAgendamento.getColumnModel().getColumn(8).setMinWidth(50);
		tabelaAgendamento.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(50);
		tabelaAgendamento.getColumnModel().getColumn(9).setMaxWidth(0);
		tabelaAgendamento.getColumnModel().getColumn(9).setMinWidth(0);
		tabelaAgendamento.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);

		cboFuncionario.setEnabled(false);
		cboFuncionario.setBounds(150, 295, 101, 20);
		formAgendamento.getContentPane().add(cboFuncionario);

		MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
		txtCpf = new JFormattedTextField(maskCpf);
		txtCpf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				// tabelaPrincipal.putClientProperty("terminateEditOnFocusLost",
				// Boolean.TRUE);
				tabelaAgendamento.clearSelection();
				txtHorarioFim.setText("");
				txtHorarioInicio.setText("");
				btnEditar.setEnabled(false);
			}
		});
		txtCpf.setFocusLostBehavior(JFormattedTextField.COMMIT);
		txtCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabelaAgendamento.setEnabled(false);
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
						btnSalvar.setEnabled(true);
						cboFuncionario.setEnabled(true);
						cboServico.setEnabled(true);
						cboStatus.setEnabled(true);
						cboUnidade.setEnabled(true);
						txtHorarioInicio.setEnabled(true);
						txtHorarioFim.setEnabled(true);
						daoAgendamento.atualizarTabela(tabelaAgendamento, calendar);
						arrayUnidade = daoAgendamento.atualiazaComboUnidade(cboUnidade);
						arrayStatus = daoAgendamento.atualiazaComboStatus(cboStatus);
						arrayServicos = daoAgendamento.atualizarComboServicos(cboServico);
						cboStatus.setSelectedIndex(0);
						btnCancelar.setEnabled(true);

					} else {
						int confirmacao = JOptionPane.showConfirmDialog(null,
								"CLIENTE NÃO CADASTRADO, DESEJA CADASTRA-LO?", "Gordão Barbearia",
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
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
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
							vectorFuncionario = daoAgendamento.atualizarComboFuncionario(cboFuncionario, idUnidade);
						}
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		cboUnidade.setBounds(10, 293, 112, 20);
		formAgendamento.getContentPane().add(cboUnidade);

		cboStatus.setEnabled(false);
		cboStatus.setEditable(true);
		cboStatus.setBounds(149, 340, 123, 20);
		formAgendamento.getContentPane().add(cboStatus);

		cboServico.setEnabled(false);
		cboServico.setBounds(273, 295, 101, 20);
		formAgendamento.getContentPane().add(cboServico);
		// txtCpf.setText(frmPrincipal.consultaCpf.toString());
		txtCpf.setColumns(10);
		txtCpf.setBounds(10, 249, 108, 20);
		formAgendamento.getContentPane().add(txtCpf);

		JLabel lblCpf = new JLabel("CPF do cliente");
		lblCpf.setBounds(10, 234, 101, 14);
		formAgendamento.getContentPane().add(lblCpf);

		scroll = new JScrollPane(tabelaAgendamento);
		scroll.setBounds(402, 308, 715, 337);
		formAgendamento.getContentPane().add(scroll);

		calendar.setBounds(10, 420, 370, 223);
		formAgendamento.getContentPane().add(calendar);

		txtCliente = new JTextField();
		txtCliente.setEditable(false);
		txtCliente.setBounds(148, 249, 108, 20);
		formAgendamento.getContentPane().add(txtCliente);
		txtCliente.setColumns(10);

		MaskFormatter maskHora = new MaskFormatter("##:##");
		MaskFormatter maskHora1 = new MaskFormatter("##:##");

		txtHorarioInicio = new JFormattedTextField(maskHora);
		txtHorarioInicio.setEnabled(false);
		txtHorarioInicio.setFocusLostBehavior(JFormattedTextField.COMMIT);
		txtHorarioInicio.setColumns(10);
		txtHorarioInicio.setBounds(11, 339, 50, 20);
		formAgendamento.getContentPane().add(txtHorarioInicio);

		txtHorarioFim = new JFormattedTextField(maskHora1);
		txtHorarioFim.setEnabled(false);
		txtHorarioFim.setFocusLostBehavior(JFormattedTextField.COMMIT);
		txtHorarioFim.setColumns(10);
		txtHorarioFim.setBounds(71, 340, 50, 20);
		formAgendamento.getContentPane().add(txtHorarioFim);

		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(212, 386, 89, 23);
		formAgendamento.getContentPane().add(btnSalvar);

		btnCancelar.setBounds(10, 386, 89, 23);
		formAgendamento.getContentPane().add(btnCancelar);

		btnEditar.setEnabled(false);
		btnEditar.setBounds(109, 386, 97, 23);
		formAgendamento.getContentPane().add(btnEditar);

		JButton pesqCli = new JButton("");
		pesqCli.setToolTipText("Selecionar cliente");
		pesqCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					FrmCadastroCliente frmCadastroCliente = new FrmCadastroCliente();
					frmCadastroCliente.formCadCli.setVisible(true);
					formAgendamento.setVisible(false);
				} catch (java.text.ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		pesqCli.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		pesqCli.setIcon(new ImageIcon(FrmAgendamento.class.getResource("/image/procurar.png")));
		pesqCli.setBounds(271, 247, 103, 23);
		formAgendamento.getContentPane().add(pesqCli);

		JLabel lblDigiteOCpf = new JLabel(
				"Digite o CPF do cliente e pressione ENTER, ou clique na Lupa para pesquisar por nome");
		lblDigiteOCpf.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDigiteOCpf.setBounds(10, 176, 497, 14);
		formAgendamento.getContentPane().add(lblDigiteOCpf);

		JLabel lblServicos = new JLabel("Servi\u00E7os");
		lblServicos.setBounds(273, 280, 81, 14);
		formAgendamento.getContentPane().add(lblServicos);

		JLabel lblHorrioSaida = new JLabel("Fim");
		lblHorrioSaida.setBounds(71, 325, 46, 14);
		formAgendamento.getContentPane().add(lblHorrioSaida);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(149, 325, 81, 14);
		formAgendamento.getContentPane().add(lblStatus);

		JLabel lblUnidade = new JLabel("Unidade");
		lblUnidade.setBounds(10, 278, 112, 14);
		formAgendamento.getContentPane().add(lblUnidade);

		JLabel lblCliente = new JLabel("Nome do cliente");
		lblCliente.setBounds(148, 234, 103, 14);
		formAgendamento.getContentPane().add(lblCliente);

		JLabel lblHorrio = new JLabel("Inicio");
		lblHorrio.setBounds(11, 324, 60, 14);
		formAgendamento.getContentPane().add(lblHorrio);

		JLabel lblFuncionrio = new JLabel("Funcion\u00E1rio");
		lblFuncionrio.setBounds(150, 280, 81, 14);
		formAgendamento.getContentPane().add(lblFuncionrio);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(FrmAgendamento.class.getResource("/image/Logo_entalhe_403x132.fw.png")));
		lblLogo.setBounds(301, 3, 403, 132);
		formAgendamento.getContentPane().add(lblLogo);

		JLabel lblFundo = new JLabel("");
		lblFundo.setToolTipText("");
		lblFundo.setIcon(new ImageIcon(FrmAgendamento.class.getResource("/image/Fundo_MarcaDagua_2000x1200.fw.png")));
		lblFundo.setBounds(-7, -4, 1144, 667);
		formAgendamento.getContentPane().add(lblFundo);

		menuBarPrincipal = new JMenuBar();
		formAgendamento.setJMenuBar(menuBarPrincipal);

		mnCadastros = new JMenu("Cadastros");
		menuBarPrincipal.add(mnCadastros);

		mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCadastroCliente frmCadastroCliente;
				try {
					frmCadastroCliente = new FrmCadastroCliente();
					frmCadastroCliente.formCadCli.setVisible(true);
					formAgendamento.setVisible(false);
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

		JMenuItem mntmFuncionarios = new JMenuItem("Funcion\u00E1rios");
		mntmFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmCadastroFuncionario frmCadastroFuncionario = new FrmCadastroFuncionario();

				frmCadastroFuncionario.formCadFunc.setVisible(true);
			}
		});
		mnCadastros.add(mntmFuncionarios);

		JMenu mnAgendamento = new JMenu("Relatorios");
		menuBarPrincipal.add(mnAgendamento);

		JMenuItem mntmNovoAgendamento = new JMenuItem("Gerar relat\u00F3rio");
		mntmNovoAgendamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmRelatorios frmRelatorios = new FrmRelatorios();
				frmRelatorios.formRelatorios.setVisible(true);

			}
		});
		mnAgendamento.add(mntmNovoAgendamento);

		tabelaAgendamento.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				DefaultTableModel model = (DefaultTableModel) tabelaAgendamento.getModel();
				if (model.getRowCount() > 0) {
					cboStatus.removeAllItems();
					if (tabelaAgendamento.getSelectedRow() >= 0) {
						cboFuncionario.removeAllItems();
						btnEditar.setEnabled(true);
						/*
						 * txtHorarioInicio.setText((String)
						 * tabelaAgendamento.getModel()
						 * .getValueAt(tabelaAgendamento.getSelectedRow(), 1));
						 * txtHorarioFim.setText((String)
						 * tabelaAgendamento.getModel()
						 * .getValueAt(tabelaAgendamento.getSelectedRow(), 2));
						 * cboFuncionario.addItem((String)
						 * tabelaAgendamento.getModel()
						 * .getValueAt(tabelaAgendamento.getSelectedRow(), 3));
						 * txtCliente.setText((String)
						 * tabelaAgendamento.getModel()
						 * .getValueAt(tabelaAgendamento.getSelectedRow(), 4));
						 * System.out.println((String)
						 * tabelaAgendamento.getModel()
						 * .getValueAt(tabelaAgendamento.getSelectedRow(), 5));
						 * cboServico.addItem((String)
						 * tabelaAgendamento.getModel()
						 * .getValueAt(tabelaAgendamento.getSelectedRow(), 6));
						 */
						cboStatus.addItem((String) tabelaAgendamento.getModel()
								.getValueAt(tabelaAgendamento.getSelectedRow(), 7));
					}
				}
			}
		});
		calendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				btnEditar.setEnabled(false);
				tabelaAgendamento.clearSelection();

				try {
					String dataCalendario = null;
					SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
					dataCalendario = formatoData.format(calendar.getDate());

					// pegando a data atual do sistema em string e convertendo
					// para date no formato dia mes ano
					String dataAtualString = formatoData.format(new Date());
					Date dataAtual = formatoData.parse(dataAtualString);

					// funcao para converter string em data
					Date dataSelecionada = funcoes.converterData(dataCalendario);

					// comparando se a data atual é igual ou maior que a data
					// selecionada no FORM,
					// para impedir de realizar um agendamento na data passada
					if (dataAtual.after(dataSelecionada)) {
						txtCpf.setEnabled(false);
						pesqCli.setEnabled(false);
						btnSalvar.setEnabled(false);
					} else if (dataAtual.equals(dataSelecionada)) {
						txtCpf.setEnabled(true);
						pesqCli.setEnabled(true);
						//btnSalvar.setEnabled(true);
					} else {
						txtCpf.setEnabled(true);
						pesqCli.setEnabled(true);
						//btnSalvar.setEnabled(true);
					}

				} catch (java.text.ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					daoAgendamento.atualizarTabela(tabelaAgendamento, calendar);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnEditar.setText("Editar");

				try {
					daoAgendamento.atualizarTabela(tabelaAgendamento, calendar);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tabelaAgendamento.setEnabled(true);
				cboFuncionario.setEnabled(false);
				cboServico.setEnabled(false);
				cboStatus.setEnabled(false);
				cboUnidade.setEnabled(false);
				btnSalvar.setEnabled(false);
				txtHorarioInicio.setEnabled(false);
				btnCancelar.setEnabled(false);
				btnEditar.setEnabled(false);
				txtHorarioFim.setEnabled(false);
				cboFuncionario.removeAllItems();
				cboUnidade.removeAllItems();
				cboStatus.removeAllItems();
				cboServico.removeAllItems();
				txtHorarioInicio.setText("");
				txtHorarioFim.setText("");
				txtCpf.setText("");
				txtCliente.setText("");
				idCliente = "";

			}
		});
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if (cboFuncionario.getSelectedItem() != null && txtHorarioInicio.getText().trim().length() == 5
							&& txtHorarioFim.getText().trim().length() == 5) {
					String horaInicioString = txtHorarioInicio.getText();
					String horaFimString = txtHorarioFim.getText();
					Time horaInicioTime;
					horaInicioTime = funcoes.converterHora(horaInicioString);
					Time horaFimTime = funcoes.converterHora(horaFimString);
					Time horaAtual = funcoes.horaAtual();
					
					String dataCalendario = null;
					SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
					dataCalendario = formatoData.format(calendar.getDate());

					// pegando a data atual do sistema em string e convertendo
					// para date no formato dia mes ano
					String dataAtualString = formatoData.format(new Date());
					Date dataAtual = formatoData.parse(dataAtualString);

					// funcao para converter string em data
					Date dataSelecionada = funcoes.converterData(dataCalendario);


				if (horaInicioTime.equals(horaFimTime)) {
					JOptionPane.showMessageDialog(null," Favor verificar se horário final não esta igual ao horário de inicio","Gordão Barbearia", JOptionPane.INFORMATION_MESSAGE);
					txtHorarioFim.setText("");
				}else if(horaFimTime.before(horaInicioTime)){
					JOptionPane.showMessageDialog(null,"Favor verificar se horário final não esta menor que horário de inicio","Gordão Barbearia", JOptionPane.INFORMATION_MESSAGE);
					txtHorarioFim.setText("");
				}else if(horaInicioTime.before(horaAtual) && dataSelecionada.equals(dataAtual) ){
					JOptionPane.showMessageDialog(null,"Favor verificar se horário de inicio não esta menor que horário atual","Gordão Barbearia", JOptionPane.INFORMATION_MESSAGE);
					txtHorarioFim.setText("");
				} else {


							int codFuncionario = cboFuncionario.getSelectedIndex();
							String funcionario = vectorFuncionario.get(codFuncionario).getIdFunc();

							int codServicos = cboServico.getSelectedIndex();
							String servicos = arrayServicos.get(codServicos);

							String horaInicio = txtHorarioInicio.getText().trim();
							String horaFim = txtHorarioFim.getText().trim();

							int codStatus = cboStatus.getSelectedIndex();
							String status = arrayStatus.get(codStatus);

							Agendamento agendamento = new Agendamento(funcionario, idCliente, status, servicos,
									dataCalendario, horaInicio, horaFim);

							String horaFuncIni = vectorFuncionario.get(codFuncionario).getHoraInicio();
							String horaFuncFim = vectorFuncionario.get(codFuncionario).getHoraFim();

							boolean validarHorarioFun = false;
							validarHorarioFun = daoAgendamento.verificarHorarioFunc(horaInicio, horaFim, horaFuncIni,
									horaFuncFim);
							if (validarHorarioFun) {
								boolean validarHorario = true;
								if (!cboStatus.getSelectedItem().equals("ESPERA")) {
									validarHorario = daoAgendamento.verificarDisponibilidade(funcionario,
											dataCalendario, horaInicio, horaFim);
								}

								if (validarHorario) {
									daoAgendamento.salvarAgendamento(agendamento);
									daoAgendamento.atualizarTabela(tabelaAgendamento, calendar);
									JOptionPane.showMessageDialog(null, "Agendado com sucesso!", "Gordão Barbearia",
											JOptionPane.INFORMATION_MESSAGE);
									cboFuncionario.setEnabled(false);
									cboServico.setEnabled(false);
									cboStatus.setEnabled(false);
									cboUnidade.setEnabled(false);
									btnSalvar.setEnabled(false);
									cboFuncionario.removeAllItems();
									cboUnidade.removeAllItems();
									cboStatus.removeAllItems();
									cboServico.removeAllItems();
									txtHorarioInicio.setEnabled(false);
									txtHorarioFim.setEnabled(false);
									txtHorarioInicio.setText("");
									txtHorarioFim.setText("");
									txtCpf.setText("");
									txtCliente.setText("");
									btnCancelar.setEnabled(false);
									idCliente = "";
									tabelaAgendamento.setEnabled(true);
								} else {
									JOptionPane.showMessageDialog(null, "Horário indisponível", "Gordão Barbearia",
											JOptionPane.INFORMATION_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "Horário fora do atendimento do funcionário",
										"Gordão Barbearia", JOptionPane.INFORMATION_MESSAGE);
							}


				}
					} else {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Gordão Barbearia",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
			}
		});
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (btnEditar.getText().equals("Editar")) {
					btnEditar.setText("Confirmar");
					tabelaAgendamento.setEnabled(false);
					try {
						arrayStatus = daoAgendamento.atualiazaComboStatus(cboStatus);
						// daoAgendamento.atualiazaComboUnidade(cboUnidade);
						// daoAgendamento.atualizarComboServicos(cboServico);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					cboStatus.setEnabled(true);
					btnCancelar.setEnabled(true);
					/*
					 * cboFuncionario.setEnabled(true);
					 * cboServico.setEnabled(true); cboUnidade.setEnabled(true);
					 * txtHorarioInicio.setEnabled(true);
					 * txtHorarioFim.setEnabled(true);
					 */

				} else {
					int codStatus = cboStatus.getSelectedIndex();
					String nomeStatus = cboStatus.getSelectedItem().toString();
					String horaInicio = (String) tabelaAgendamento.getModel()
							.getValueAt(tabelaAgendamento.getSelectedRow(), 1);
					String horaFim = (String) tabelaAgendamento.getModel()
							.getValueAt(tabelaAgendamento.getSelectedRow(), 2);

					String dataCalendario = null;
					SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
					dataCalendario = formatoData.format(calendar.getDate());
					boolean validarHorario = true;
					if (nomeStatus.equalsIgnoreCase("AGENDADO")) {

						String funcionario = (String) tabelaAgendamento.getModel()
								.getValueAt(tabelaAgendamento.getSelectedRow(), 9);
						try {
							validarHorario = daoAgendamento.verificarDisponibilidade(funcionario, dataCalendario,
									horaInicio, horaFim);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (validarHorario) {
						try {
							daoAgendamento.atualiazaComboStatus(cboStatus);
							/*
							 * daoAgendamento.atualiazaComboUnidade(cboUnidade);
							 * daoAgendamento.atualizarComboServicos(cboServico)
							 * ;
							 */
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						int statusAgend = Integer.parseInt(arrayStatus.get(codStatus));
						btnEditar.setText("Editar");
						cboStatus.setEnabled(false);
						tabelaAgendamento.setEnabled(true);
						btnCancelar.setEnabled(false);
						int idAgend = Integer.parseInt(((String) tabelaAgendamento.getModel()
								.getValueAt(tabelaAgendamento.getSelectedRow(), 8)));
						System.out.println(idAgend);

						boolean updateAgend = false;
						try {
							updateAgend = daoAgendamento.atualizarStatus(idAgend, statusAgend);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (updateAgend) {
							JOptionPane.showMessageDialog(null, "Status atualizado com sucesso", "Gordão Barbearia",
									JOptionPane.INFORMATION_MESSAGE);
							btnEditar.setEnabled(false);
						} else {
							JOptionPane.showMessageDialog(null, "Status não atualizado", "Gordão Barbearia",
									JOptionPane.ERROR_MESSAGE);
						}
						try {
							daoAgendamento.atualizarTabela(tabelaAgendamento, calendar);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						/*
						 * cboFuncionario.setEnabled(false);
						 * cboServico.setEnabled(false);
						 * cboUnidade.setEnabled(false);
						 * txtHorarioInicio.setEnabled(false);
						 * txtHorarioFim.setEnabled(false);
						 */
					} else {
						JOptionPane.showMessageDialog(null, "Funcionário já possui agendamento para esse horário",
								"Gordão Barbearia", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		formAgendamento.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				formAgendamento.setVisible(false);
				try {
					FrmPrincipal frmPrincipal = new FrmPrincipal();
					frmPrincipal.frmGordoBarbearia.setVisible(true);
				} catch (java.text.ParseException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}
			}
		});

		arrayUnidade = daoAgendamento.atualiazaComboUnidade(cboUnidade);
		// colorir os atendimentos que estão na fila de espera
		daoAgendamento.getNewRenderedTable(tabelaAgendamento);

		formAgendamento.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { txtCpf, cboUnidade,
				cboFuncionario, cboServico, txtHorarioInicio, txtHorarioFim, btnSalvar, btnCancelar, btnEditar }));
	}
}
