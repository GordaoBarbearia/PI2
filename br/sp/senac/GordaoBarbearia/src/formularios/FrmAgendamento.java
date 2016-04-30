package formularios;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.text.ParseException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import com.toedter.calendar.JCalendar;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.DefaultComboBoxModel;

public class FrmAgendamento {

	static JFrame formPrincipal;
	private JScrollPane scroll;
	private JTable tabelaPrincipal;
	private JMenuBar menuBarPrincipal;
	private JMenu mnCadastros;
	private JMenuItem mntmClientes;
	private JMenuItem mntmFuncionrios;
	private JTextField txtCliente;
	private JTextField txtHorarioInicio;
	private JTextField txtHorarioFim;
	private JTextField txtCpf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, JTextField txtCpf) {
		
		FrmPrincipal frmPrincipal = new FrmPrincipal();
		
		
		
		txtCpf.setText(frmPrincipal.txtConsultaCpf.getText());
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
	 * @throws ParseException 
	 */
	private void initialize() throws ParseException {
		
		//---------------------------------------------------------------------------------------
		// Criando Componentes
		formPrincipal = new JFrame();
		formPrincipal.setTitle("Barbearia O Gord\u00E3o");
		formPrincipal.setBounds(100, 100, 960, 582);
		formPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		formPrincipal.getContentPane().setLayout(null);
		
		tabelaPrincipal = new JTable(0, 2);
		tabelaPrincipal.setBounds(407, 111, 243, 250);
		tabelaPrincipal.setSurrendersFocusOnKeystroke(true);
		tabelaPrincipal.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "DATA", "HORA", "FUNCIONÁRIO","CLIENTE" }) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}

		});

		formPrincipal.getContentPane().add(tabelaPrincipal);
		
		//Bloqueia a rendenização das tabelas
		tabelaPrincipal.getTableHeader().setResizingAllowed(false);
		//Bloqueia a reordenação das tabelas
		tabelaPrincipal.getTableHeader().setReorderingAllowed(false);
		
		tabelaPrincipal.getColumnModel().getColumn(0).setPreferredWidth(70);
		tabelaPrincipal.getColumnModel().getColumn(1).setPreferredWidth(70);
		tabelaPrincipal.getColumnModel().getColumn(2).setPreferredWidth(120);
		tabelaPrincipal.getColumnModel().getColumn(3).setPreferredWidth(70);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Chaves", "Henrique", "Cesar"}));
		comboBox.setBounds(145, 176, 101, 20);
		formPrincipal.getContentPane().add(comboBox);
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBounds(6, 122, 108, 20);
		formPrincipal.getContentPane().add(txtCpf);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(6, 107, 46, 14);
		formPrincipal.getContentPane().add(lblCpf);
		
		JButton btnConsultarCpf = new JButton("Consultar");
		btnConsultarCpf.setBounds(124, 121, 89, 23);
		formPrincipal.getContentPane().add(btnConsultarCpf);
	
		
		scroll = new JScrollPane(tabelaPrincipal);
		scroll.setBounds(402, 184, 532, 337);
		formPrincipal.getContentPane().add(scroll);
		
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(6, 298, 370, 223);		
		formPrincipal.getContentPane().add(calendar);
		
		txtCliente = new JTextField();
		txtCliente.setBounds(8, 176, 108, 20);
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
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(303, 264, 89, 23);
		formPrincipal.getContentPane().add(btnExcluir);
		
		JButton bntEditar = new JButton("Editar");
		bntEditar.setBounds(204, 264, 89, 23);
		formPrincipal.getContentPane().add(bntEditar);
		formPrincipal.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtCliente,  btnSalvar, btnCancelar, bntEditar}));
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(8, 161, 46, 14);
		formPrincipal.getContentPane().add(lblCliente);
		
		JLabel lblHorrio = new JLabel("Hor\u00E1rio inicio");
		lblHorrio.setBounds(8, 208, 87, 14);
		formPrincipal.getContentPane().add(lblHorrio);
		
		MaskFormatter mask = new MaskFormatter("##:##");
		
		JLabel lblFuncionrio = new JLabel("Funcion\u00E1rio");
		lblFuncionrio.setBounds(145, 161, 81, 14);
		formPrincipal.getContentPane().add(lblFuncionrio);
		
		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(FrmAgendamento.class.getResource("/image/Fundo_MarcaDagua_G.fw.png")));
		lblFundo.setBounds(0, -4, 999, 532);
		formPrincipal.getContentPane().add(lblFundo);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(FrmAgendamento.class.getResource("/image/Logo_entalhe_403x132.fw.png")));
		lblLogo.setBounds(221, 3, 403, 132);
		formPrincipal.getContentPane().add(lblLogo);
		
		menuBarPrincipal = new JMenuBar();
		formPrincipal.setJMenuBar(menuBarPrincipal);
		
		mnCadastros = new JMenu("Cadastros");
		menuBarPrincipal.add(mnCadastros);
		
		mntmFuncionrios = new JMenuItem("Funcion\u00E1rios");		
		mntmFuncionrios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrmCadastroFuncionario frmCadastroFuncionario = new FrmCadastroFuncionario();
				
				frmCadastroFuncionario.formCadFunc.setVisible(true);
			}
		});
		mnCadastros.add(mntmFuncionrios);
		
		mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCadastroCliente frmCadastroCliente = new FrmCadastroCliente();
				
				frmCadastroCliente.formCadCli.setVisible(true);
			}
		});
		mnCadastros.add(mntmClientes);
		
		JMenu mnAgendamento = new JMenu("Agendamento");
		menuBarPrincipal.add(mnAgendamento);
		
		JMenuItem mntmNovoAgendamento = new JMenuItem("Novo Agendamento");
		mnAgendamento.add(mntmNovoAgendamento);
		
		
		
		
		
	}
}
