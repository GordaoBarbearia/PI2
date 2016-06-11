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

/**
* @author NH2
*/

public class FrmPrincipal {

	static JFrame frmGordoBarbearia;


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
		frmGordoBarbearia.setTitle("Gord\u00E3o barbearia");
		frmGordoBarbearia.setBounds(100, 100, 617, 310);
		frmGordoBarbearia.getContentPane().setLayout(null);

		JButton btnClientes = new JButton("Clientes");
		btnClientes.setHorizontalAlignment(SwingConstants.LEFT);
		btnClientes.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/image/1297713679_list-add-user (Custom).png")));
		btnClientes.addActionListener(new ActionListener() {
			//ação no botão para abrir o formulário de cadastro de cliente
			public void actionPerformed(ActionEvent arg0) {
				FrmCadastroCliente cadastroCliente;
				try {
					cadastroCliente = new FrmCadastroCliente();
					cadastroCliente.formCadCli.setVisible(true);
					frmGordoBarbearia.setVisible(false);
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
			//ação no botão para abrir o formulário de Agendamento
			public void actionPerformed(ActionEvent arg0) {
				try {
					FrmAgendamento frmAgendamento = new FrmAgendamento();
					frmAgendamento.formAgendamento.setVisible(true);
					frmGordoBarbearia.setVisible(false);
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
		btnGerarReltorios.addActionListener(new ActionListener() {
			//ação no botão para abrir o formulário de relatorio
			public void actionPerformed(ActionEvent arg0) {
				FrmRelatorios frmRelatorios = new FrmRelatorios();
				frmRelatorios.formRelatorios.setVisible(true);
				frmGordoBarbearia.setVisible(false);
			}
		});
		btnGerarReltorios.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/image/relatorio - icon (Custom).png")));
		btnGerarReltorios.setHorizontalAlignment(SwingConstants.LEFT);
		btnGerarReltorios.setBounds(412, 208, 187, 58);
		frmGordoBarbearia.getContentPane().add(btnGerarReltorios);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/image/Logo_entalhe_403x132.fw.png")));
		
		lblLogo.setBounds(97, 0, 416, 144);
		frmGordoBarbearia.getContentPane().add(lblLogo);

		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/image/Fundo_1024.png")));
		lblFundo.setBounds(0, -23, 611, 309);
		frmGordoBarbearia.getContentPane().add(lblFundo);
		frmGordoBarbearia.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frmGordoBarbearia.getContentPane()}));
	}
}
