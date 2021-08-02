package common;

import java.io.Serializable;

/**
 * La classe <em>CentroVaccinale</em> viene utilizzata per creare un oggetto che identifica un centro vaccinale all'interno dell'applicazione.
 * @author Invanov Aleksandar Evgeniev, Mazza Serghej, Berti Davide, Rizzi Silvio
 */
public class CentroVaccinale implements Serializable {
    private String nome, indirizzo, tipologia;
    private int IDcentro;

	/**
	 *Costruttore utilizzato per creare oggetti della classe senza dover inizializzare i parametri.
	 */
	public CentroVaccinale(){ }

	/**
	 *Costruttore utilizzato per creare oggetti  della classe con inizializzazione dei parametri della classe.
	 * @param nome Parametro di tipo <i>String </i> che identifica il nome del centro vaccinale.
	 * @param indirizzo Parametro di tipo <i>String </i> che identifica l'indirizzo del centro vaccinale.
	 * @param tipologia Parametro di tipo <i>String </i> che identifica il tipo di centro vaccinale (HUB/OSPEDALIERO/AZIENDALE).
	 */
    public CentroVaccinale(String nome,String indirizzo,String tipologia) {
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.tipologia = tipologia;
	}

	/**
	 *Costruttore utilizzato per
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
	 *Il metodo <em>getIDcentro</em> &egrave; utilizzato per ottenere l'ID di un centro.
 	 * @return Un valore di tipo <i>Int</i> che identifica l'ID di un centro.
	 */
	public int getIDcentro(){return IDcentro; }

	/**
	 *Il metodo <em>getNome</em> &egrave; utilizzato per ottenere il nome di un centro.
	 * @return Un valore di tipo <i>String</i> che contiene il nome del centro.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 *
	 * Il metodo <em>getIndirizzo</em> &egrave; utilizzato per ottenere l'indirizzo di un centro.
	 * @return Un valore di tipo <i>String</i> che contiene l'indirizzo del centro.
	 * @return
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 *Il metodo <em>getTipologia</em> &egrave; utilizzato per ottenere la tipologia di un centro.
	 * @return Un valore di tipo <i>String</i> che contiene la tipologia del centro.
	 */
	public String getTipologia() {
		return tipologia;
	}
}
