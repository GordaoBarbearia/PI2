package formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.sun.corba.se.pept.transport.Connection;

import DAO.DaoClientes;

import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class FrmPrincipal {

	private JFrame frmGordoBarbearia;
	static String consultaCpf = "";

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
	 * 
	 * @throws ParseException
	 */
	public FrmPrincipal() throws ParseException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws ParseException
	 */
	private void initialize() throws ParseException {
		frmGordoBarbearia = new JFrame();
		frmGordoBarbearia.setResizable(false);
		frmGordoBarbearia.setTitle("Gord\u00E3o Barbearia");
		frmGordoBarbearia.setBounds(100, 100, 617, 310);
		frmGordoBarbearia.getContentPane().setLayout(null);

		JButton btnClientes = new JButton("Clientes");
		btnClientes.setHorizontalAlignment(SwingConstants.LEFT);
		btnClientes.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/image/1297713679_list-add-user (Custom).png")));
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				FrmCadastroCliente cadastroCliente;
				try {
					cadastroCliente = new FrmCadastroCliente();
					cadastroCliente.formCadCli.setVisible(true);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnClientes.setBounds(10, 208, 155, 58);
		frmGordoBarbearia.getContentPane().add(btnClientes);

		JButton btnRealizarAgendamentos = new JButton("Realizar agendamentos");
		btnRealizarAgendamentos.setHorizontalAlignment(SwingConstants.LEFT);
		btnRealizarAgendamentos.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/image/Calendar-icon (Custom).png")));
		btnRealizarAgendamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					FrmAgendamento frmAgendamento = new FrmAgendamento();
					frmAgendamento.formPrincipal.setVisible(true);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnRealizarAgendamentos.setBounds(175, 208, 227, 58);
		frmGordoBarbearia.getContentPane().add(btnRealizarAgendamentos);

		JButton btnGerarReltorios = new JButton("Gerar rel\u00E1torios");
		btnGerarReltorios.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/image/relatorio - icon (Custom).png")));
		btnGerarReltorios.setHorizontalAlignment(SwingConstants.LEFT);
		btnGerarReltorios.setBounds(412, 208, 187, 58);
		frmGordoBarbearia.getContentPane().add(btnGerarReltorios);

		MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/br/image/Logo_entalhe_403x132.fw.png")));
		lblNewLabel.setBounds(97, 0, 416, 144);
		frmGordoBarbearia.getContentPane().add(lblNewLabel);

		JLabel lblFundo = new JLabel("New label");
		lblFundo.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/br/image/Fundo_MarcaDagua_G.fw.png")));
		lblFundo.setBounds(0, -23, 611, 309);
		frmGordoBarbearia.getContentPane().add(lblFundo);
		frmGordoBarbearia.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frmGordoBarbearia.getContentPane()}));
	}
}