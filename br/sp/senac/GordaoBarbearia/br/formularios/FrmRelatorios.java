package formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

import DAO.DaoRelatorio;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;

public class FrmRelatorios {

	private JFrame frmRelatorios;
	private JScrollPane scroll;
	private JTable tabRelat;
	private ArrayList<String> arrayFunc;
	private ArrayList<String> arrayCli;
	private ArrayList<String> arrayPessoa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRelatorios window = new FrmRelatorios();
					window.frmRelatorios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmRelatorios() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRelatorios = new JFrame();
		frmRelatorios.setTitle("Barbearia O Gard\u00E3o - Relat\u00F3rios");
		frmRelatorios.setBounds(100, 100, 1061, 651);
		frmRelatorios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRelatorios.getContentPane().setLayout(null);

		tabRelat = new JTable(0, 2);
		tabRelat.setBounds(407, 111, 243, 250);
		tabRelat.setSurrendersFocusOnKeystroke(true);
		tabRelat.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "DATA", "HR NICIO", "HR FINAL",
				"FUNCIONÁRIO", "CLIENTE", "UNIDADE", "SERVIÇO", "STATUS", "" }) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}

		});

		frmRelatorios.getContentPane().add(tabRelat);

		// Bloqueia a rendenização das tabelas
		tabRelat.getTableHeader().setResizingAllowed(false);
		// Bloqueia a reordenação das tabelas
		tabRelat.getTableHeader().setReorderingAllowed(false);
		tabRelat.getColumnModel().getColumn(0).setPreferredWidth(66);
		tabRelat.getColumnModel().getColumn(1).setPreferredWidth(66);
		tabRelat.getColumnModel().getColumn(2).setPreferredWidth(66);
		tabRelat.getColumnModel().getColumn(3).setPreferredWidth(120);
		tabRelat.getColumnModel().getColumn(4).setPreferredWidth(66);
		tabRelat.getColumnModel().getColumn(5).setPreferredWidth(66);
		tabRelat.getColumnModel().getColumn(6).setPreferredWidth(66);
		tabRelat.getColumnModel().getColumn(7).setPreferredWidth(66);
		// Codigo para ocultar a coluna ID do agendamento
		tabRelat.getColumnModel().getColumn(8).setMaxWidth(0);
		tabRelat.getColumnModel().getColumn(8).setMinWidth(0);
		tabRelat.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);

		JComboBox cboRelatPessoa = new JComboBox();
		cboRelatPessoa.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				

			}
		});
		cboRelatPessoa.setEnabled(false);
		cboRelatPessoa.setBounds(205, 117, 175, 20);
		frmRelatorios.getContentPane().add(cboRelatPessoa);

		JComboBox cboTipoRelat = new JComboBox();
		cboTipoRelat.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				DaoRelatorio daoRelatorio = new DaoRelatorio();
				if (cboTipoRelat.getSelectedItem().equals("Data")) {
					cboRelatPessoa.removeAllItems();
					cboRelatPessoa.setEnabled(false);
				} else if (cboTipoRelat.getSelectedItem().equals("Funcionario")) {

					try {
						arrayPessoa = daoRelatorio.atualizarComboFuncionario(cboRelatPessoa);
						cboRelatPessoa.setEnabled(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					try {
						arrayPessoa = daoRelatorio.atualizarComboCliente(cboRelatPessoa);
						cboRelatPessoa.setEnabled(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		cboTipoRelat.setModel(new DefaultComboBoxModel(new String[] { "Data", "Cliente", "Funcionario" }));
		cboTipoRelat.setBounds(20, 117, 175, 20);
		frmRelatorios.getContentPane().add(cboTipoRelat);

		JLabel lblNewLabel = new JLabel("Selecione o tipo de relat\u00F3rio");
		lblNewLabel.setBounds(20, 101, 167, 14);
		frmRelatorios.getContentPane().add(lblNewLabel);

		JLabel lblQuem = new JLabel("Quem?");
		lblQuem.setBounds(205, 101, 142, 14);
		frmRelatorios.getContentPane().add(lblQuem);

		JDateChooser dateChooserInicio = new JDateChooser();
		dateChooserInicio.setBounds(390, 117, 99, 20);
		frmRelatorios.getContentPane().add(dateChooserInicio);

		JDateChooser dateChooserFim = new JDateChooser();
		dateChooserFim.setBounds(499, 117, 99, 20);
		frmRelatorios.getContentPane().add(dateChooserFim);

		JButton btnRelatOptionOK = new JButton("OK");
		btnRelatOptionOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println("Nome "+cboRelatPessoa.getSelectedItem());
				
				int id = cboRelatPessoa.getSelectedIndex();
				
				System.out.println("ID "+arrayPessoa.get(id));
			}
		});
		btnRelatOptionOK.setBounds(608, 116, 61, 23);
		frmRelatorios.getContentPane().add(btnRelatOptionOK);

		scroll = new JScrollPane(tabRelat);
		scroll.setBounds(14, 195, 1015, 406);
		frmRelatorios.getContentPane().add(scroll);

		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setBounds(390, 101, 70, 14);
		frmRelatorios.getContentPane().add(lblInicio);

		JLabel lblFim = new JLabel("Fim");
		lblFim.setBounds(499, 101, 70, 14);
		frmRelatorios.getContentPane().add(lblFim);
		
		JRadioButton rdbtnTodos = new JRadioButton("Todos");
		rdbtnTodos.setBounds(13, 170, 70, 23);
		frmRelatorios.getContentPane().add(rdbtnTodos);
		
		JRadioButton rdbtnAgendados = new JRadioButton("Agendados");
		rdbtnAgendados.setBounds(85, 170, 92, 23);
		frmRelatorios.getContentPane().add(rdbtnAgendados);
		
		JRadioButton rdbtnAtendidos = new JRadioButton("Atendidos");
		rdbtnAtendidos.setBounds(179, 170, 84, 23);
		frmRelatorios.getContentPane().add(rdbtnAtendidos);
		
		JRadioButton rdbtnCancelados = new JRadioButton("Cancelados");
		rdbtnCancelados.setBounds(271, 170, 99, 23);
		frmRelatorios.getContentPane().add(rdbtnCancelados);
		
		JRadioButton rdbtnEspera = new JRadioButton("Espera");
		rdbtnEspera.setBounds(372, 170, 78, 23);
		frmRelatorios.getContentPane().add(rdbtnEspera);

	}
}
