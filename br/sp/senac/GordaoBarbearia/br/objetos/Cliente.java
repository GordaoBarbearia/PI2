package objetos;

public class Cliente {
	
	private String cpfCliente;
	private String nomeCliente;
	private String telefoneCliente;
	
	public Cliente(String cpfCliente, String nomeCliente, String telefoneCliente){
		this.cpfCliente = cpfCliente;
		this.nomeCliente = nomeCliente;
		this.telefoneCliente = telefoneCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}
	
	

}
