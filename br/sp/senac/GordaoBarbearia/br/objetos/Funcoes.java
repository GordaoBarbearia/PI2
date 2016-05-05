package objetos;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
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

	public void limparCampos(JTextField txtNome, JFormattedTextField txtCpf, JTextField txtTelefone) {
		txtNome.setText("");
		txtCpf.setText("");
		txtTelefone.setText("");
	}
	
	public boolean validarCampos(JTextField txtNome, JFormattedTextField txtCpf, JTextField txtTelefone) {
		if (txtNome.getText().trim().equals("") || txtCpf.getText().trim().length() <11
				|| txtTelefone.getText().trim().length()<11) {
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
			JOptionPane.showMessageDialog(null, "DATA DO AGENDAMENTO INVÁLIDA", "Agendamento",
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
}
