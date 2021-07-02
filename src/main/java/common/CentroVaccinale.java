package common;

import java.io.Serializable;

public class CentroVaccinale implements Serializable {
    private String nome, indirizzo;
    private TipologiaCentro tipologia;

    public CentroVaccinale(){ }

    public CentroVaccinale(String nome,String indirizzo,TipologiaCentro tipologia) {
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

	public TipologiaCentro getTipologia() {
		return tipologia;
	}
}
