package common;

import java.io.Serializable;

/**
 * La classe <em>CentroVaccinale</em> viene utilizzata per creare un oggetto che identifica un centro vaccinale nell'applicazione.
 * @author Invanov Aleksandar Evgeniev, Mazza Serghej, Berti Davide, Rizzi Silvio
 */
public class CentroVaccinale implements Serializable {
    private String nome, indirizzo, tipologia;
    private int IDcentro;

	/**
	 *
	 */
	public CentroVaccinale(){ }

	/**
	 *
	 * @param nome
	 * @param indirizzo
	 * @param tipologia
	 */
    public CentroVaccinale(String nome,String indirizzo,String tipologia) {
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.tipologia = tipologia;
	}

	/**
	 *
	 * @param IDcentro
	 * @param nome
	 * @param indirizzo
	 * @param tipologia
	 */
	public CentroVaccinale(int IDcentro,String nome,String indirizzo,String tipologia) {
    	this.IDcentro=IDcentro;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.tipologia = tipologia;
	}

	/**
	 *
	 * @return
	 */
	public int getIDcentro(){return IDcentro; }

	/**
	 *
	 * @return
	 */
	public String getNome() {
		return nome;
	}

	/**
	 *
	 * @return
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 *
	 * @return
	 */
	public String getTipologia() {
		return tipologia;
	}
}
