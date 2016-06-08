package formularios;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import com.itextpdf.text.DocumentException;
import com.toedter.calendar.JDateChooser;
import DAO.DaoAgendamento;
import DAO.DaoRelatorio;
import modelo.ExportarPdf;
import modelo.Relatorio;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
* @author NH2
*/

public class FrmRelatorios {

	static JFrame formRelatorios;
	private JScrollPane scroll;
	private JTable tabRelat;
	private ArrayList<String> arrayFunc;
	private ArrayList<String> arrayCli;
	private ArrayList<String> arrayPessoa;
	private ButtonGroup group;
	private Vector<Relatorio> vetorRel;
	DaoRelatorio daoRelatorio = new DaoRelatorio();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRelatorios window = new FrmRelatorios();
					window.formRelatorios.setVisible(true);
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

		formRelatorios = new JFrame();
		formRelatorios.setResizable(false);
		formRelatorios.setTitle("Gord\u00E3o barbearia - Relat\u00F3rios");
		formRelatorios.setBounds(100, 100, 1044, 682);
		formRelatorios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		formRelatorios.getContentPane().setLayout(null);

		tabRelat = new JTable(0, 2);
		tabRelat.setBounds(407, 111, 243, 250);
		tabRelat.setSurrendersFocusOnKeystroke(true);
		tabRelat.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "DATA", "HR NICIO", "HR FINAL",
				"FUNCIONÁRIO", "CLIENTE", "UNIDADE", "SERVIÇO", "STATUS", "" }) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}

		});

		formRelatorios.getContentPane().add(tabRelat);

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

		// Criação dos componentes
		JRadioButton rdbtnEspera = new JRadioButton("Espera");
		JRadioButton rdbtnCancelados = new JRadioButton("Cancelados");
		JRadioButton rdbtnAtendidos = new JRadioButton("Atendidos");
		JRadioButton rdbtnAgendados = new JRadioButton("Agendados");
		JRadioButton rdbtnTodos = new JRadioButton("Todos");
		JComboBox<String> cboRelatPessoa = new JComboBox<String>();
		cboRelatPessoa.setEnabled(false);
		cboRelatPessoa.setBounds(198, 193, 175, 20);
		formRelatorios.getContentPane().add(cboRelatPessoa);
		JComboBox<String> cboTipoRelat = new JComboBox<String>();
		cboTipoRelat.setModel(new DefaultComboBoxModel(new String[] { "Data", "Cliente", "Funcionario", "Unidade" }));
		cboTipoRelat.setBounds(13, 193, 175, 20);
		formRelatorios.getContentPane().add(cboTipoRelat);
		JLabel lblNewLabel = new JLabel("Selecione o tipo de relat\u00F3rio");
		lblNewLabel.setBounds(13, 177, 167, 14);
		formRelatorios.getContentPane().add(lblNewLabel);



		JButton btnExportar = new JButton("Exportar para PDF");
		btnExportar.setHorizontalAlignment(SwingConstants.LEFT);
		btnExportar.setIcon(new ImageIcon(FrmRelatorios.class.getResource("/image/icone-pdf.ico")));
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel model = (DefaultTableModel) tabRelat.getModel();
				
				int linhas = model.getRowCount();
				
				if(linhas>0){
					ExportarPdf exportarPdf = new ExportarPdf();
					try {
						exportarPdf.gerarPdf(vetorRel);
					} catch (DocumentException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, e, "Gordão Barbearia",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "Não há dados para gerar o Relatório", "Gordão Barbearia",JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		btnExportar.setBounds(822, 193, 200, 57);
		formRelatorios.getContentPane().add(btnExportar);

		JDateChooser dateChooserInicio = new JDateChooser();
		dateChooserInicio.setDateFormatString("dd/MM/yyyy");
		dateChooserInicio.setBounds(383, 193, 99, 20);
		formRelatorios.getContentPane().add(dateChooserInicio);

		JDateChooser dateChooserFim = new JDateChooser();
		dateChooserFim.setDateFormatString("dd/MM/yyyy");
		dateChooserFim.setBounds(492, 193, 99, 20);
		formRelatorios.getContentPane().add(dateChooserFim);

		scroll = new JScrollPane(tabRelat);
		scroll.setBounds(7, 270, 1015, 362);
		formRelatorios.getContentPane().add(scroll);

		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setBounds(383, 177, 70, 14);
		formRelatorios.getContentPane().add(lblInicio);

		JLabel lblFim = new JLabel("Fim");
		lblFim.setBounds(492, 177, 70, 14);
		formRelatorios.getContentPane().add(lblFim);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(FrmRelatorios.class.getResource("/image/logo_relatorio_476x148.png")));
		label.setBounds(283, 0, 475, 148);
		formRelatorios.getContentPane().add(label);

		rdbtnTodos.setBounds(6, 242, 70, 23);
		formRelatorios.getContentPane().add(rdbtnTodos);

		rdbtnAgendados.setBounds(78, 242, 92, 23);
		formRelatorios.getContentPane().add(rdbtnAgendados);

		rdbtnAtendidos.setBounds(172, 242, 84, 23);
		formRelatorios.getContentPane().add(rdbtnAtendidos);

		rdbtnCancelados.setBounds(264, 242, 99, 23);
		formRelatorios.getContentPane().add(rdbtnCancelados);

		rdbtnEspera.setBounds(365, 242, 78, 23);
		formRelatorios.getContentPane().add(rdbtnEspera);

		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(FrmRelatorios.class.getResource("/image/Fundo_MarcaDagua_2000x1200.fw.png")));
		lblFundo.setBounds(-5, 0, 1050, 643);
		formRelatorios.getContentPane().add(lblFundo);

		group = new ButtonGroup();
		group.add(rdbtnTodos);
		group.add(rdbtnAgendados);
		group.add(rdbtnAtendidos);
		group.add(rdbtnCancelados);
		group.add(rdbtnEspera);
		
		JLabel lblQuem = new JLabel("Selecione o periodo");

		rdbtnTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (dateChooserInicio.getDate() != null && dateChooserFim.getDate() != null) {
					// pega a data selecionada
					SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
					String dataInicio = formatoData.format(dateChooserInicio.getDate());
					String dataFim = formatoData.format(dateChooserFim.getDate());

					if (cboTipoRelat.getSelectedItem().equals("Data")) {
						try {
							vetorRel = daoRelatorio.atualizarTabelaTodos(tabRelat, dataInicio, dataFim);
							daoRelatorio.preencherTabela(vetorRel, tabRelat);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (cboTipoRelat.getSelectedItem().equals("Cliente")) {
						int id = cboRelatPessoa.getSelectedIndex();
						String idString = arrayPessoa.get(id);
						try {
							vetorRel = daoRelatorio.atualizarTabelaCli(tabRelat, idString, dataInicio, dataFim);
							daoRelatorio.preencherTabela(vetorRel, tabRelat);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (cboTipoRelat.getSelectedItem().equals("Funcionario")) {
						int id = cboRelatPessoa.getSelectedIndex();
						String idString = arrayPessoa.get(id);
						try {
							vetorRel = daoRelatorio.atualizarTabelaFunc(tabRelat, idString, dataInicio, dataFim);
							daoRelatorio.preencherTabela(vetorRel, tabRelat);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (cboTipoRelat.getSelectedItem().equals("Unidade")) {
						int id = cboRelatPessoa.getSelectedIndex();
						String idString = arrayPessoa.get(id);						
						try {
							vetorRel = daoRelatorio.atualizarTabelaUnidade(tabRelat, idString, dataInicio, dataFim);
							daoRelatorio.preencherTabela(vetorRel, tabRelat);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Preencha os campos data inicio e data fim", "Gordão Barbearia",
							JOptionPane.INFORMATION_MESSAGE);
					group.clearSelection();
				}
			}
		});
		rdbtnEspera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Pesquisa de agendamentos que estejam em ESPERA
				if (dateChooserInicio.getDate() != null && dateChooserFim.getDate() != null) {

					// pega a data selecionada transforma em string
					SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
					String dataInicio = formatoData.format(dateChooserInicio.getDate());
					String dataFim = formatoData.format(dateChooserFim.getDate());

					// Pesquisa geral por periodo e status ESPERA
					if (cboTipoRelat.getSelectedItem().equals("Data")) {
						try {
							vetorRel = daoRelatorio.atualizarTabelaTodosStatus(tabRelat, dataInicio, dataFim, "4");
							daoRelatorio.preencherTabela(vetorRel, tabRelat);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// Pesquisa por Cliente/status Espera
					} else if (cboTipoRelat.getSelectedItem().equals("Cliente")) {
						int id = cboRelatPessoa.getSelectedIndex();
						String idString = arrayPessoa.get(id);
						try {

							vetorRel = daoRelatorio.atualizarTabelaCliStatus(tabRelat, idString, dataInicio, dataFim,
									"4");
							daoRelatorio.preencherTabela(vetorRel, tabRelat);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// Pesquisa por Funcionario/status Espera
					} else if (cboTipoRelat.getSelectedItem().equals("Funcionario")) {
						int id = cboRelatPessoa.getSelectedIndex();
						String idString = arrayPessoa.get(id);
						try {
							vetorRel = daoRelatorio.atualizarTabelaFuncStatus(tabRelat, idString, dataInicio, dataFim,"4");
							daoRelatorio.preencherTabela(vetorRel, tabRelat);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (cboTipoRelat.getSelectedItem().equals("Unidade")) {
						int id = cboRelatPessoa.getSelectedIndex();
						String idString = arrayPessoa.get(id);
						try {
							vetorRel = daoRelatorio.atualizarTabelaUnidadeStatus(tabRelat, idString, dataInicio, dataFim,"4");
							daoRelatorio.preencherTabela(vetorRel, tabRelat);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				} else {
					JOptionPane.showMessageDialog(null, "Preencha os campos data inicio e data fim", "Gordão Barbearia",
							JOptionPane.INFORMATION_MESSAGE);
					group.clearSelection();
				}
			}
		});
		rdbtnCancelados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (dateChooserInicio.getDate() != null && dateChooserFim.getDate() != null) {

					// pega a data selecionada
					SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
					String dataInicio = formatoData.format(dateChooserInicio.getDate());
					String dataFim = formatoData.format(dateChooserFim.getDate());

					if (cboTipoRelat.getSelectedItem().equals("Data")) {
						try {
							vetorRel = daoRelatorio.atualizarTabelaTodosStatus(tabRelat, dataInicio, dataFim, "3");
							daoRelatorio.preencherTabela(vetorRel, tabRelat);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (cboTipoRelat.getSelectedItem().equals("Cliente")) {
						int id = cboRelatPessoa.getSelectedIndex();
						String idString = arrayPessoa.get(id);

						try {
							vetorRel = daoRelatorio.atualizarTabelaCliStatus(tabRelat, idString, dataInicio, dataFim,"3");
							daoRelatorio.preencherTabela(vetorRel, tabRelat);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (cboTipoRelat.getSelectedItem().equals("Funcionario")) {
						int id = cboRelatPessoa.getSelectedIndex();
						String idString = arrayPessoa.get(id);
						try {
							vetorRel = daoRelatorio.atualizarTabelaFuncStatus(tabRelat, idString, dataInicio, dataFim,"3");
							daoRelatorio.preencherTabela(vetorRel, tabRelat);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (cboTipoRelat.getSelectedItem().equals("Unidade")){
						int id = cboRelatPessoa.getSelectedIndex();
						String idString = arrayPessoa.get(id);
						try {
							vetorRel = daoRelatorio.atualizarTabelaUnidadeStatus(tabRelat, idString, dataInicio, dataFim,"3");
							daoRelatorio.preencherTabela(vetorRel, tabRelat);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Preencha os campos data inicio e data fim", "Gordão Barbearia",
							JOptionPane.INFORMATION_MESSAGE);
					group.clearSelection();
				}
			}
		});
		rdbtnAgendados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (dateChooserInicio.getDate() != null && dateChooserFim.getDate() != null) {
					// pega a data selecionada
					SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
					String dataInicio = formatoData.format(dateChooserInicio.getDate());
					String dataFim = formatoData.format(dateChooserFim.getDate());

					if (cboTipoRelat.getSelectedItem().equals("Data")) {
						try {
							vetorRel = daoRelatorio.atualizarTabelaTodosStatus(tabRelat, dataInicio, dataFim, "1");
							daoRelatorio.preencherTabela(vetorRel, tabRelat);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (cboTipoRelat.getSelectedItem().equals("Cliente")) {
						int id = cboRelatPessoa.getSelectedIndex();
						String idString = arrayPessoa.get(id);

						try {
							vetorRel = daoRelatorio.atualizarTabelaCliStatus(tabRelat, idString, dataInicio, dataFim,
									"1");
							daoRelatorio.preencherTabela(vetorRel, tabRelat);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (cboTipoRelat.getSelectedItem().equals("Funcionario")) {
						int id = cboRelatPessoa.getSelectedIndex();
						String idString = arrayPessoa.get(id);
						try {
							vetorRel = daoRelatorio.atualizarTabelaFuncStatus(tabRelat, idString, dataInicio, dataFim,"1");
							daoRelatorio.preencherTabela(vetorRel, tabRelat);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (cboTipoRelat.getSelectedItem().equals("Unidade")) {
						int id = cboRelatPessoa.getSelectedIndex();
						String idString = arrayPessoa.get(id);
						try {
							vetorRel = daoRelatorio.atualizarTabelaUnidadeStatus(tabRelat, idString, dataInicio, dataFim,"1");
							daoRelatorio.preencherTabela(vetorRel, tabRelat);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Preencha os campos data inicio e data fim", "Gordão Barbearia",
							JOptionPane.INFORMATION_MESSAGE);
					group.clearSelection();
				}
			}
		});
		rdbtnAtendidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (dateChooserInicio.getDate() != null && dateChooserFim.getDate() != null) {
					// pega a data selecionada
					SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
					String dataInicio = formatoData.format(dateChooserInicio.getDate());
					String dataFim = formatoData.format(dateChooserFim.getDate());

					if (cboTipoRelat.getSelectedItem().equals("Data")) {
						try {
							vetorRel = daoRelatorio.atualizarTabelaTodosStatus(tabRelat, dataInicio, dataFim, "2");
							daoRelatorio.preencherTabela(vetorRel, tabRelat);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (cboTipoRelat.getSelectedItem().equals("Cliente")) {
						int id = cboRelatPessoa.getSelectedIndex();
						String idString = arrayPessoa.get(id);

						try {
							vetorRel = daoRelatorio.atualizarTabelaCliStatus(tabRelat, idString, dataInicio, dataFim,
									"2");
							daoRelatorio.preencherTabela(vetorRel, tabRelat);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (cboTipoRelat.getSelectedItem().equals("Funcionario")) {
						int id = cboRelatPessoa.getSelectedIndex();
						String idString = arrayPessoa.get(id);
						try {
							vetorRel = daoRelatorio.atualizarTabelaFuncStatus(tabRelat, idString, dataInicio, dataFim,"2");
							daoRelatorio.preencherTabela(vetorRel, tabRelat);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (cboTipoRelat.getSelectedItem().equals("Unidade")) {
						int id = cboRelatPessoa.getSelectedIndex();
						String idString = arrayPessoa.get(id);
						try {
							vetorRel = daoRelatorio.atualizarTabelaUnidadeStatus(tabRelat, idString, dataInicio, dataFim,"2");
							daoRelatorio.preencherTabela(vetorRel, tabRelat);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Preencha os campos data inicio e data fim", "Gordão Barbearia",
							JOptionPane.INFORMATION_MESSAGE);
					group.clearSelection();
				}
			}
		});
		cboRelatPessoa.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				group.clearSelection();
			}
		});
		cboTipoRelat.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if (cboTipoRelat.getSelectedItem().equals("Data")) {
					cboRelatPessoa.removeAllItems();
					cboRelatPessoa.setEnabled(false);
					lblQuem.setText("Selecione o periodo");
				} else if (cboTipoRelat.getSelectedItem().equals("Funcionario")) {
					try {
						arrayPessoa = daoRelatorio.atualizarComboFuncionario(cboRelatPessoa);
						cboRelatPessoa.setEnabled(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					lblQuem.setText("Selecione o Funcionário");
				} else if (cboTipoRelat.getSelectedItem().equals("Cliente")) {
					try {
						arrayPessoa = daoRelatorio.atualizarComboCliente(cboRelatPessoa);
						cboRelatPessoa.setEnabled(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					lblQuem.setText("Selecione o Cliente");
				} else if (cboTipoRelat.getSelectedItem().equals("Unidade")) { 
					try {
						arrayPessoa = daoRelatorio.atualizarComboUnidade(cboRelatPessoa);
						cboRelatPessoa.setEnabled(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					lblQuem.setText("Selecione a Unidade");
				}
				group.clearSelection();
			}
		});
		formRelatorios.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				formRelatorios.setVisible(false);
				try {
					FrmPrincipal frmPrincipal = new FrmPrincipal();
					frmPrincipal.frmGordoBarbearia.setVisible(true);
				} catch (java.text.ParseException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}
			}
		});
		
		DaoAgendamento daoAgendamento = new DaoAgendamento();
		// colorir os atendimentos que estão na fila de espera
		daoAgendamento.getNewRenderedTable(tabRelat);
		
		JLabel lblSelecioneOStatus = new JLabel("Selecione o Status");
		lblSelecioneOStatus.setBounds(13, 224, 167, 14);
		formRelatorios.getContentPane().add(lblSelecioneOStatus);
		

		lblQuem.setBounds(197, 177, 143, 14);
		formRelatorios.getContentPane().add(lblQuem);

	}
}
