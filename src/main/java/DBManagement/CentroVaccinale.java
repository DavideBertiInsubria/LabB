
public class CentroVaccinale {
	private String nome,indirizzo,tipologia;
	
	public CentroVaccinale(String nome,String indirizzo,String tipologia) {
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.tipologia = tipologia;
	}

	public String getNome() {
		return nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getTipologia() {
		return tipologia;
	}

}
