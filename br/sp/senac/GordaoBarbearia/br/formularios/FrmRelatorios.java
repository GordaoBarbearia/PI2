package formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

import DAO.DaoAgendamento;
import DAO.DaoRelatorio;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class FrmRelatorios {

	static JFrame frmRelatorios;
	private JScrollPane scroll;
	private JTable tabRelat;
	private ArrayList<String> arrayFunc;
	private ArrayList<String> arrayCli;
	private ArrayList<String> arrayPessoa;
	private ButtonGroup group;
	
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
		frmRelatorios.setTitle("Gord\u00E3o barbearia - Relat\u00F3rios");
		frmRelatorios.setBounds(100, 100, 1061, 681);
		frmRelatorios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

		JRadioButton rdbtnEspera = new JRadioButton("Espera");
		JRadioButton rdbtnCancelados = new JRadioButton("Cancelados");

		JRadioButton rdbtnAtendidos = new JRadioButton("Atendidos");
		JRadioButton rdbtnAgendados = new JRadioButton("Agendados");
		JRadioButton rdbtnTodos = new JRadioButton("Todos");

		JComboBox<String> cboRelatPessoa = new JComboBox<String>();
		cboRelatPessoa.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				group.clearSelection();
			}
		});
		cboRelatPessoa.setEnabled(false);
		cboRelatPessoa.setBounds(205, 162, 175, 20);
		frmRelatorios.getContentPane().add(cboRelatPessoa);

		JComboBox<String> cboTipoRelat = new JComboBox<String>();
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
				group.clearSelection();
			}
		});
		cboTipoRelat.setModel(new DefaultComboBoxModel(new String[] { "Data", "Cliente", "Funcionario" }));
		cboTipoRelat.setBounds(20, 162, 175, 20);
		frmRelatorios.getContentPane().add(cboTipoRelat);

		JLabel lblNewLabel = new JLabel("Selecione o tipo de relat\u00F3rio");
		lblNewLabel.setBounds(20, 146, 167, 14);
		frmRelatorios.getContentPane().add(lblNewLabel);

		JLabel lblQuem = new JLabel("Quem?");
		lblQuem.setBounds(205, 146, 142, 14);
		frmRelatorios.getContentPane().add(lblQuem);

		JDateChooser dateChooserInicio = new JDateChooser();
		dateChooserInicio.setDateFormatString("dd/MM/yyyy");
		dateChooserInicio.setBounds(390, 162, 99, 20);
		frmRelatorios.getContentPane().add(dateChooserInicio);

		JDateChooser dateChooserFim = new JDateChooser();
		dateChooserFim.setDateFormatString("dd/MM/yyyy");
		dateChooserFim.setBounds(499, 162, 99, 20);
		frmRelatorios.getContentPane().add(dateChooserFim);

		scroll = new JScrollPane(tabRelat);
		scroll.setBounds(14, 223, 1015, 406);
		frmRelatorios.getContentPane().add(scroll);

		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setBounds(390, 146, 70, 14);
		frmRelatorios.getContentPane().add(lblInicio);

		JLabel lblFim = new JLabel("Fim");
		lblFim.setBounds(499, 146, 70, 14);
		frmRelatorios.getContentPane().add(lblFim);

		rdbtnTodos.setBounds(13, 195, 70, 23);
		frmRelatorios.getContentPane().add(rdbtnTodos);

		rdbtnAgendados.setBounds(85, 195, 92, 23);
		frmRelatorios.getContentPane().add(rdbtnAgendados);

		rdbtnAtendidos.setBounds(179, 195, 84, 23);
		frmRelatorios.getContentPane().add(rdbtnAtendidos);

		rdbtnCancelados.setBounds(271, 195, 99, 23);
		frmRelatorios.getContentPane().add(rdbtnCancelados);

		rdbtnEspera.setBounds(372, 195, 78, 23);
		frmRelatorios.getContentPane().add(rdbtnEspera);

		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(FrmRelatorios.class.getResource("/image/Fundo_MarcaDagua_2000x1200.fw.png")));
		lblFundo.setBounds(-5, 0, 1050, 643);
		frmRelatorios.getContentPane().add(lblFundo);

		group = new ButtonGroup();
		group.add(rdbtnTodos);
		group.add(rdbtnAgendados);
		group.add(rdbtnAtendidos);
		group.add(rdbtnCancelados);
		group.add(rdbtnEspera);
		
		rdbtnTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DaoRelatorio daoRelatorio = new DaoRelatorio();
				// pega a data selecionada
				SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
				String dataInicio = formatoData.format(dateChooserInicio.getDate());
				String dataFim = formatoData.format(dateChooserFim.getDate());

				if (cboTipoRelat.getSelectedItem().equals("Data")) {
					if (rdbtnTodos.isSelected()) {
						try {
							daoRelatorio.atualizarTabelaTodos(tabRelat, dataInicio, dataFim);

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else {
						try {
							String idStatus = idStatus(rdbtnAgendados, rdbtnAtendidos, rdbtnCancelados, rdbtnEspera);
							daoRelatorio.atualizarTabelaTodosStatus(tabRelat, dataInicio, dataFim, idStatus);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else if (cboTipoRelat.getSelectedItem().equals("Cliente")) {

					int id = cboRelatPessoa.getSelectedIndex();
					String idString = arrayPessoa.get(id);

					if (rdbtnTodos.isSelected()) {
						try {
							daoRelatorio.atualizarTabelaCli(tabRelat, idString, dataInicio, dataFim);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						try {
							String status = idStatus(rdbtnAgendados, rdbtnAtendidos, rdbtnCancelados, rdbtnEspera);
							daoRelatorio.atualizarTabelaCliStatus(tabRelat, idString, dataInicio, dataFim, status);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					int id = cboRelatPessoa.getSelectedIndex();
					String idString = arrayPessoa.get(id);

					if (rdbtnTodos.isSelected()) {
						try {
							daoRelatorio.atualizarTabelaFunc(tabRelat, idString, dataInicio, dataFim);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						try {
							String status = idStatus(rdbtnAgendados, rdbtnAtendidos, rdbtnCancelados, rdbtnEspera);
							daoRelatorio.atualizarTabelaFuncStatus(tabRelat, idString, dataInicio, dataFim, status);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}
		});
		rdbtnEspera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Pesquisa de agendamentos que estejam em ESPERA				 
				
				DaoRelatorio daoRelatorio = new DaoRelatorio();
				//pega a data selecionada transforma em string
				SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
				String dataInicio = formatoData.format(dateChooserInicio.getDate());
				String dataFim = formatoData.format(dateChooserFim.getDate());

				//Pesquisa geral por periodo e status ESPERA
				if (cboTipoRelat.getSelectedItem().equals("Data")) {
					try {
						String idStatus = idStatus(rdbtnAgendados, rdbtnAtendidos, rdbtnCancelados, rdbtnEspera);
						daoRelatorio.atualizarTabelaTodosStatus(tabRelat, dataInicio, dataFim, idStatus);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Pesquisa por Cliente/status Espera
				} else if (cboTipoRelat.getSelectedItem().equals("Cliente")) {
					int id = cboRelatPessoa.getSelectedIndex();
					String idString = arrayPessoa.get(id);

					try {
						String status = idStatus(rdbtnAgendados, rdbtnAtendidos, rdbtnCancelados, rdbtnEspera);
						daoRelatorio.atualizarTabelaCliStatus(tabRelat, idString, dataInicio, dataFim, status);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Pesquisa por Funcionario/status Espera
				} else {
					int id = cboRelatPessoa.getSelectedIndex();
					String idString = arrayPessoa.get(id);
					try {
						String status = idStatus(rdbtnAgendados, rdbtnAtendidos, rdbtnCancelados, rdbtnEspera);
						daoRelatorio.atualizarTabelaFuncStatus(tabRelat, idString, dataInicio, dataFim, status);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		rdbtnCancelados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DaoRelatorio daoRelatorio = new DaoRelatorio();
				// pega a data selecionada
				SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
				String dataInicio = formatoData.format(dateChooserInicio.getDate());
				String dataFim = formatoData.format(dateChooserFim.getDate());

				if (cboTipoRelat.getSelectedItem().equals("Data")) {
					try {
						String idStatus = idStatus(rdbtnAgendados, rdbtnAtendidos, rdbtnCancelados, rdbtnEspera);
						daoRelatorio.atualizarTabelaTodosStatus(tabRelat, dataInicio, dataFim, idStatus);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else if (cboTipoRelat.getSelectedItem().equals("Cliente")) {
					int id = cboRelatPessoa.getSelectedIndex();
					String idString = arrayPessoa.get(id);

					try {
						String status = idStatus(rdbtnAgendados, rdbtnAtendidos, rdbtnCancelados, rdbtnEspera);
						daoRelatorio.atualizarTabelaCliStatus(tabRelat, idString, dataInicio, dataFim, status);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					int id = cboRelatPessoa.getSelectedIndex();
					String idString = arrayPessoa.get(id);
					try {
						String status = idStatus(rdbtnAgendados, rdbtnAtendidos, rdbtnCancelados, rdbtnEspera);
						daoRelatorio.atualizarTabelaFuncStatus(tabRelat, idString, dataInicio, dataFim, status);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		rdbtnAgendados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DaoRelatorio daoRelatorio = new DaoRelatorio();
				// pega a data selecionada
				SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
				String dataInicio = formatoData.format(dateChooserInicio.getDate());
				String dataFim = formatoData.format(dateChooserFim.getDate());

				if (cboTipoRelat.getSelectedItem().equals("Data")) {
					try {
						String idStatus = idStatus(rdbtnAgendados, rdbtnAtendidos, rdbtnCancelados, rdbtnEspera);
						daoRelatorio.atualizarTabelaTodosStatus(tabRelat, dataInicio, dataFim, idStatus);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else if (cboTipoRelat.getSelectedItem().equals("Cliente")) {
					int id = cboRelatPessoa.getSelectedIndex();
					String idString = arrayPessoa.get(id);

					try {
						String status = idStatus(rdbtnAgendados, rdbtnAtendidos, rdbtnCancelados, rdbtnEspera);
						daoRelatorio.atualizarTabelaCliStatus(tabRelat, idString, dataInicio, dataFim, status);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					int id = cboRelatPessoa.getSelectedIndex();
					String idString = arrayPessoa.get(id);
						try {
							String status = idStatus(rdbtnAgendados, rdbtnAtendidos, rdbtnCancelados, rdbtnEspera);
							daoRelatorio.atualizarTabelaFuncStatus(tabRelat, idString, dataInicio, dataFim, status);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			}
		});
		rdbtnAtendidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DaoRelatorio daoRelatorio = new DaoRelatorio();
				// pega a data selecionada
				SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
				String dataInicio = formatoData.format(dateChooserInicio.getDate());
				String dataFim = formatoData.format(dateChooserFim.getDate());

				if (cboTipoRelat.getSelectedItem().equals("Data")) {
					try {
						String idStatus = idStatus(rdbtnAgendados, rdbtnAtendidos, rdbtnCancelados, rdbtnEspera);
						daoRelatorio.atualizarTabelaTodosStatus(tabRelat, dataInicio, dataFim, idStatus);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else if (cboTipoRelat.getSelectedItem().equals("Cliente")) {
					int id = cboRelatPessoa.getSelectedIndex();
					String idString = arrayPessoa.get(id);

					try {
						String status = idStatus(rdbtnAgendados, rdbtnAtendidos, rdbtnCancelados, rdbtnEspera);
						daoRelatorio.atualizarTabelaCliStatus(tabRelat, idString, dataInicio, dataFim, status);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					int id = cboRelatPessoa.getSelectedIndex();
					String idString = arrayPessoa.get(id);
						try {
							String status = idStatus(rdbtnAgendados, rdbtnAtendidos, rdbtnCancelados, rdbtnEspera);
							daoRelatorio.atualizarTabelaFuncStatus(tabRelat, idString, dataInicio, dataFim, status);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			}
		});
		
		
		DaoAgendamento daoAgendamento = new DaoAgendamento();
		// colorir os atendimentos que estão na fila de espera
		daoAgendamento.getNewRenderedTable(tabRelat);
	
	}	
	
		
	
	public static String idStatus(JRadioButton rdbtnAgend, JRadioButton rdbtnAtend, JRadioButton rdbtnCancel,
			JRadioButton rdbtnEsp) {

		if (rdbtnAgend.isSelected()) {
			return "1";
		} else if (rdbtnAtend.isSelected()) {
			return "2";
		} else if (rdbtnCancel.isSelected()) {
			return "3";
		} else {
			return "4";
		}
	}
}
