package common;

import java.io.Serializable;

public class CentroVaccinale implements Serializable {
    private String nome, indirizzo, tipologia;

    public CentroVaccinale(){ }

    public CentroVaccinale(String nome,String indirizzo,String tipologia) {
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.tipologia = tipologia;
	}

	public CentroVaccinale (String name, String addr, TipologiaCentro type) {
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
