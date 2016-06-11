package modelo;

public class Agendamento {

	private String idFuncionario;
	private String idCliente;
	private String idStatus;
	private String idServico;
	private String dataAgendamento;
	private String horaInicioAgendmento;
	private String horaFimAgendamento;
	

	

	public Agendamento(String idFuncionario, String idCliente, String idStatus, String idServico,
			String dataAgendamento, String horaInicioAgendmento, String horaFimAgendamento) {
			
		this.idFuncionario = idFuncionario;
		this.idCliente = idCliente;
		this.idStatus = idStatus;
		this.idServico = idServico;
		this.dataAgendamento = dataAgendamento;
		this.horaInicioAgendmento = horaInicioAgendmento;
		this.horaFimAgendamento = horaFimAgendamento;
	}

	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(String idStatus) {
		this.idStatus = idStatus;
	}

	public String getIdServico() {
		return idServico;
	}

	public void setIdServico(String idServico) {
		this.idServico = idServico;
	}

	public String getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public String getHoraInicioAgendmento() {
		return horaInicioAgendmento;
	}

	public void setHoraInicioAgendmento(String horaInicioAgendmento) {
		this.horaInicioAgendmento = horaInicioAgendmento;
	}

	public String getHoraFimAgendamento() {
		return horaFimAgendamento;
	}

	public void setHoraFimAgendamento(String horaFimAgendamento) {
		this.horaFimAgendamento = horaFimAgendamento;
	}
	
	

}
