package formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.sun.corba.se.pept.transport.Connection;

import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class FrmPrincipal {

	private JFrame frmGordoBarbearia;
	static JTextField txtConsultaCpf;

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

				Test test = new Test();
				
				try {
					test.conecta();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnConsultar.setBounds(134, 80, 89, 23);
		frmGordoBarbearia.getContentPane().add(btnConsultar);
	}
}
