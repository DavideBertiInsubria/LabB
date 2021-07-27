package common;

import java.io.Serializable;

public class CentroVaccinale implements Serializable {
    private String nome, indirizzo, tipologia;
    private int IDcentro;

    public CentroVaccinale(){ }

    public CentroVaccinale(String nome,String indirizzo,String tipologia) {
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.tipologia = tipologia;
	}

	public CentroVaccinale(int IDcentro,String nome,String indirizzo,String tipologia) {
    	this.IDcentro=IDcentro;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.tipologia = tipologia;
	}

	public int getIDcentro(){return IDcentro; }

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
