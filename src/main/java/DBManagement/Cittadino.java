
public class Cittadino {
	
	private String nome;
	private String cognome;
	private String email;
	private String pwd;
	private String idvaccinazione;
	private String idcentro;
	private String cf;
	
	public Cittadino(String cf,String nome,String cognome, String email,String pwd,String idvaccinazione,String idcentro) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.pwd = pwd;
		this.idvaccinazione = idvaccinazione;
		this.idcentro = idcentro;
		this.cf = cf;
	}

	public String getCF() {
		return cf;
	}
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return pwd;
	}
	
	public String getIDVaccinazione() {
		return idvaccinazione;
	}
	
	public String getIDCentro() {
		return idcentro;
	}
	

}
