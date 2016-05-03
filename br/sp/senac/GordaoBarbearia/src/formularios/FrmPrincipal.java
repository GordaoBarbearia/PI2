package formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.sun.corba.se.pept.transport.Connection;

import DAO.DaoClientes;

import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class FrmPrincipal {

	private JFrame frmGordoBarbearia;
	static JTextField txtConsultaCpf;
	static String consultaCpf = null;

	/**
	 * Launch the application.
	 */
 	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal window = new FrmPrincipal();
					window.frmGordoBarbearia.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGordoBarbearia = new JFrame();
		frmGordoBarbearia.setTitle("Gord\u00E3o Barbearia");
		frmGordoBarbearia.setBounds(100, 100, 450, 347);
		frmGordoBarbearia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGordoBarbearia.getContentPane().setLayout(null);

		JLabel lblConsultarClientecpf = new JLabel("Consultar cliente(CPF)");
		lblConsultarClientecpf.setBounds(10, 65, 125, 14);
		frmGordoBarbearia.getContentPane().add(lblConsultarClientecpf);

		JButton btnClientes = new JButton("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				FrmCadastroCliente cadastroCliente = new FrmCadastroCliente();
				cadastroCliente.formCadCli.setVisible(true);
			}
		});
		btnClientes.setBounds(10, 124, 89, 58);
		frmGordoBarbearia.getContentPane().add(btnClientes);

		JButton btnRealizarAgendamentos = new JButton("Realizar agendamentos");
		btnRealizarAgendamentos.setBounds(120, 124, 145, 58);
		frmGordoBarbearia.getContentPane().add(btnRealizarAgendamentos);

		JButton btnGerarReltorios = new JButton("Gerar rel\u00E1torios");
		btnGerarReltorios.setBounds(279, 124, 145, 58);
		frmGordoBarbearia.getContentPane().add(btnGerarReltorios);

		txtConsultaCpf = new JTextField();
		txtConsultaCpf.setBounds(11, 81, 113, 20);
		frmGordoBarbearia.getContentPane().add(txtConsultaCpf);
		txtConsultaCpf.setColumns(10);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Chamando a classe de interação com o banco de dados
				DaoClientes daoClientes = new DaoClientes();

				// pegando o valor do campo txtConsultaCpf
				consultaCpf = (txtConsultaCpf.getText());

				// chamando o metodo de pesquisa da classe daoClientes, passando
				// o cpf como parametross
				// e atribuindo o resultado Boleano a variavel "pesquisaCliente"
				boolean pesquisaCliente = daoClientes
						.pesquisarCliente(consultaCpf);

				// se o resultado da pesquisa for verdadeiro, apresento a
				// mensagem que o cliente já possui cadastro, se não, informa
				// que não possui cadastro para aquele cliente
				if (pesquisaCliente) {
					int confirmacao = JOptionPane.showConfirmDialog(null,"Cliente cadastrado, deseja realizar um agendamento?",
									"Gordão Barbearia",
									JOptionPane.YES_NO_OPTION);
					if(confirmacao == JOptionPane.YES_OPTION){
						try {
							FrmAgendamento frmAgendamento = new FrmAgendamento();
							frmAgendamento.formPrincipal.setVisible(true);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
				} else {
					JOptionPane.showMessageDialog(null,
							"Cliente não cadastrado");
				}

			}
		});
		btnConsultar.setBounds(134, 80, 89, 23);
		frmGordoBarbearia.getContentPane().add(btnConsultar);
	}
}
