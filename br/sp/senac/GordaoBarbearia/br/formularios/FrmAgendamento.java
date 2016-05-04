package formularios;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import com.toedter.calendar.JCalendar;

import DAO.DaoClientes;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;

public class FrmAgendamento {

	static JFrame formPrincipal;
	private JScrollPane scroll;
	private JTable tabelaPrincipal;
	private JMenuBar menuBarPrincipal;
	private JMenu mnCadastros;
	private JMenuItem mntmClientes;
	private JTextField txtCliente;
	private JTextField txtHorarioInicio;
	private JTextField txtHorarioFim;
	private JFormattedTextField txtCpf;
	FrmPrincipal frmPrincipal = new FrmPrincipal();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, JTextField txtCpf) {

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
	 * Create the application. TESTE
	 * 
	 * @throws ParseException
	 */
	public FrmAgendamento() throws ParseException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws ParseException
	 */
	private void initialize() throws ParseException {

		// ---------------------------------------------------------------------------------------
		// Criando Componentes
		formPrincipal = new JFrame();
		formPrincipal.setTitle("Barbearia O Gord\u00E3o");
		formPrincipal.setBounds(100, 100, 1011, 582);
		formPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		formPrincipal.getContentPane().setLayout(null);

		tabelaPrincipal = new JTable(0, 2);
		tabelaPrincipal.setBounds(407, 111, 243, 250);
		tabelaPrincipal.setSurrendersFocusOnKeystroke(true);
		tabelaPrincipal.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "DATA", "HORA INICIO","HORA FINAL" ,"FUNCIONÁRIO", "CLIENTE", "UNIDADE","" }) {
					public boolean isCellEditable(int row, int col) {
						return false;
					}

				});

		formPrincipal.getContentPane().add(tabelaPrincipal);

		// Bloqueia a rendenização das tabelas
		tabelaPrincipal.getTableHeader().setResizingAllowed(false);
		// Bloqueia a reordenação das tabelas
		tabelaPrincipal.getTableHeader().setReorderingAllowed(false);

		tabelaPrincipal.getColumnModel().getColumn(0).setPreferredWidth(50);
		tabelaPrincipal.getColumnModel().getColumn(1).setPreferredWidth(70);
		tabelaPrincipal.getColumnModel().getColumn(2).setPreferredWidth(70);
		tabelaPrincipal.getColumnModel().getColumn(3).setPreferredWidth(90);
		tabelaPrincipal.getColumnModel().getColumn(4).setPreferredWidth(90);
		tabelaPrincipal.getColumnModel().getColumn(5).setPreferredWidth(90);
		tabelaPrincipal.getColumnModel().getColumn(6).setMaxWidth(0);
		tabelaPrincipal.getColumnModel().getColumn(6).setMinWidth(0);
		tabelaPrincipal.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);



		JComboBox cboFuncionario = new JComboBox();
		cboFuncionario.setModel(new DefaultComboBoxModel(new String[] {"", "01 Chaves", "02 Henrique", "03 Cesar"}));
		cboFuncionario.setBounds(7, 176, 101, 20);
		formPrincipal.getContentPane().add(cboFuncionario);

		MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
		txtCpf = new JFormattedTextField(maskCpf);
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
						int id = Integer.parseInt(pesquisaCliente.get(0).toString());

						System.out.println(id + " aquiiiiiiiiiiiiiiiiiii");
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
				}
			}
		});
		txtCpf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {

			}
		});
		
		JComboBox cboUnidade = new JComboBox();
		cboUnidade.setBounds(125, 176, 101, 20);
		formPrincipal.getContentPane().add(cboUnidade);
		txtCpf.setText(frmPrincipal.consultaCpf.toString());
		txtCpf.setColumns(10);
		txtCpf.setBounds(6, 122, 108, 20);
		formPrincipal.getContentPane().add(txtCpf);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(6, 107, 46, 14);
		formPrincipal.getContentPane().add(lblCpf);

		scroll = new JScrollPane(tabelaPrincipal);
		scroll.setBounds(402, 184, 583, 337);
		formPrincipal.getContentPane().add(scroll);

		JCalendar calendar = new JCalendar();
		calendar.setBounds(6, 298, 370, 223);
		formPrincipal.getContentPane().add(calendar);

		txtCliente = new JTextField();
		txtCliente.setEditable(false);
		txtCliente.setBounds(138, 122, 108, 20);
		formPrincipal.getContentPane().add(txtCliente);
		txtCliente.setColumns(10);

		txtHorarioInicio = new JTextField();
		txtHorarioInicio.setColumns(10);
		txtHorarioInicio.setBounds(8, 223, 70, 20);
		formPrincipal.getContentPane().add(txtHorarioInicio);

		txtHorarioFim = new JTextField();
		txtHorarioFim.setColumns(10);
		txtHorarioFim.setBounds(145, 223, 70, 20);
		formPrincipal.getContentPane().add(txtHorarioFim);

		JLabel lblHorrioSaida = new JLabel("Hor\u00E1rio fim");
		lblHorrioSaida.setBounds(145, 208, 81, 14);
		formPrincipal.getContentPane().add(lblHorrioSaida);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(6, 264, 89, 23);
		formPrincipal.getContentPane().add(btnSalvar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(105, 264, 89, 23);
		formPrincipal.getContentPane().add(btnCancelar);

		JButton bntEditar = new JButton("Editar");
		bntEditar.setBounds(204, 264, 89, 23);
		formPrincipal.getContentPane().add(bntEditar);
		
		JLabel lblUnidade = new JLabel("Unidade");
		lblUnidade.setBounds(125, 161, 81, 14);
		formPrincipal.getContentPane().add(lblUnidade);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(138, 107, 46, 14);
		formPrincipal.getContentPane().add(lblCliente);

		JLabel lblHorrio = new JLabel("Hor\u00E1rio inicio");
		lblHorrio.setBounds(8, 208, 87, 14);
		formPrincipal.getContentPane().add(lblHorrio);

		MaskFormatter mask = new MaskFormatter("##:##");

		JLabel lblFuncionrio = new JLabel("Funcion\u00E1rio");
		lblFuncionrio.setBounds(7, 161, 81, 14);
		formPrincipal.getContentPane().add(lblFuncionrio);

		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(FrmAgendamento.class.getResource("/image/Fundo_1024.png")));
		lblFundo.setBounds(0, -4, 1005, 532);
		formPrincipal.getContentPane().add(lblFundo);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(FrmAgendamento.class.getResource("/image/Logo_entalhe_403x132.fw.png")));
		lblLogo.setBounds(221, 3, 403, 132);
		formPrincipal.getContentPane().add(lblLogo);

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
				}
			}
		});
		mnCadastros.add(mntmClientes);

		JMenu mnAgendamento = new JMenu("Relatorios");
		menuBarPrincipal.add(mnAgendamento);

		JMenuItem mntmNovoAgendamento = new JMenuItem("Gerar relat\u00F3rio");
		mnAgendamento.add(mntmNovoAgendamento);
		formPrincipal.setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { txtCpf, txtCliente, btnSalvar, btnCancelar, bntEditar }));

	}
}
