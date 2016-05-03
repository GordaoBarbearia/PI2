package objetos;

public class Cliente {
	
	private String cpfCliente;
	private String nomeCliente;
	private String telefoneCliente;
	
	public Cliente(String cpfCli, String nomeCli, String telefoneCli){
		this.cpfCliente = cpfCli;
		this.nomeCliente = nomeCli;
		this.telefoneCliente = telefoneCli;
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
