package modelo;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

public class Funcoes {

	public Date converterData(String dataString) throws ParseException{		
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatoData.parse(dataString);		
		return date;
	}
	
	public Time converterHora(String horaString) throws ParseException{		
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
		Date hora = formatoHora.parse(horaString);
		Time horaTime = new Time(hora.getTime());		
		return horaTime;
	}
	
	public Time horaAtual() throws ParseException{		
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
		String horaAtual = formatoHora.format(new Date());
		Date horaAtualDate = formatoHora.parse(horaAtual);
		Time hrAtual = new Time(horaAtualDate.getTime());		
		return hrAtual;
	}
	

	public void limparCampos(JTextField txtNome, JFormattedTextField txtCpf, JTextField txtTelefone) {
		txtNome.setText("");
		txtCpf.setText("");
		txtTelefone.setText("");
	}
	
	public boolean validarCampos(JTextField txtNome, JFormattedTextField txtCpf, JTextField txtTelefone) {
		if (txtNome.getText().trim().equals("") || txtCpf.getText().trim().length() <14
				|| txtTelefone.getText().trim().length()<13) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean validarData(String data) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");		 
		String dataAtualString = dateFormat.format(new Date());
		Date dataAtual = dateFormat.parse(dataAtualString);		
		Date dataAgendamento = dateFormat.parse(data);		
		
		//System.out.println(dataAtual);
		//System.out.println(data);
		if (dataAgendamento.before(dataAtual)) {
			JOptionPane.showMessageDialog(null, "DATA DO AGENDAMENTO INV�LIDA", "Agendamento",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	public Date dataCalendar(JCalendar calendario) throws ParseException{
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yy");
		String dataString = dataFormat.format(calendario.getDate());
		Date dataCalendar = converterData(dataString);
		return dataCalendar;
	}
	
	public Date dataAtual() throws ParseException{
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yy");
		String dataString = dataFormat.format(new Date());
		Date dataAtual = converterData(dataString);
		return dataAtual;
	}
	
	public void bloquearCampos(JTextField txtNome, JFormattedTextField txtCpf, JTextField txtTelefone){
		txtNome.setEnabled(false);
		txtCpf.setEnabled(false);
		txtTelefone.setEnabled(false);
		
	}
	
	public void desbloquearCampos(JTextField txtNome, JFormattedTextField txtCpf, JTextField txtTelefone){
		txtNome.setEnabled(true);
		txtCpf.setEnabled(true);
		txtTelefone.setEnabled(true);
	}

	public void bloquearCamposAgendamento(JComboBox<String> cboFuncionario, JComboBox<String> cboServico, JComboBox<String> cboStatus, JComboBox<String> cboUnidade,
			JButton btnSalvar, JButton btnEditar, JButton btnCancelar, JTextField txtHorarioInicio, JTextField txtHorarioFim, JCheckBox chckbxFilaEspera ){
		cboFuncionario.setEnabled(false);
		cboServico.setEnabled(false);
		cboStatus.setEnabled(false);
		cboUnidade.setEnabled(false);
		btnSalvar.setEnabled(false);
		chckbxFilaEspera.setEnabled(false);
		txtHorarioInicio.setEnabled(false);
		btnCancelar.setEnabled(false);
		btnEditar.setEnabled(false);
		txtHorarioFim.setEnabled(false);
	}
	
	
	public void limparcamposAgendamento(JComboBox<String> cboFuncionario, JComboBox<String> cboServico, JComboBox<String> cboStatus, JComboBox<String> cboUnidade,
			JTextField txtHorarioInicio, JTextField txtHorarioFim, JCheckBox chckbxFilaEspera, JTextField txtCpf, JTextField txtCliente ){
		cboFuncionario.removeAllItems();
		cboUnidade.removeAllItems();
		cboStatus.removeAllItems();
		cboServico.removeAllItems();
		txtHorarioInicio.setText("");
		txtHorarioFim.setText("");
		txtCpf.setText("");
		txtCliente.setText("");
	}
	
}
