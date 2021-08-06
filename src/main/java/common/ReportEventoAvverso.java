package common;

import java.io.Serializable;

/**
 *La classe <em>ReportEventoAvverso</em> viene utilizzata per creare un oggetto che contenga il report di un evento avverso di un centro vaccinale.
 *@author Ivanov Aleksandar Evgeniev, Mazza Serghej, Berti Davide, Rizzi Silvio
 */
public class ReportEventoAvverso implements Serializable {
		
	private String Evento;
	private float severitaMedia;
	private int NSegnalazioni;

	/**
	 *Costruttore per creare un oggetto con tutte le variabili necessarie.
	 * @param Evento Parametro di tipo <i>String</i> per identificare il nome dell'evento avverso
	 * @param severitaMedia Parametro di tipo <i>float</i> per identificare la serverit&agrave; media di un evento avverso all'interno del centro vaccinale.
	 * @param NSegnalazioni Parametro di tipo <i>Int</i> per identificare il numero di segnalazioni di un evento avverso all'interno del centro vaccinale.
	 */
	public ReportEventoAvverso(String Evento,float severitaMedia, int NSegnalazioni){
		this.Evento = Evento;
		this.severitaMedia = severitaMedia;
		this.NSegnalazioni = NSegnalazioni;
	}

	/**
	 *Il metodo <em>getEvento</em> serve a ottenere il nome dell'evento avverso.
	 * @return Un valore di tipo <i>String</i> che idenfitica il nome dell'evento.
	 */
	public String getEvento() {
		return Evento;
	}

	/**
	 *Il metodo <em>getSeveritaMedia</em> serve a ottenere la severit&agrave; media dell'evento avverso.
	 * @return Un valore di tipo <i>float</i> che identifica il valore della severit&agrave; media di un evento avverso in un centro vaccinale.
	 */
	public float getSeveritaMedia() {
		return severitaMedia;
	}

	/**
	 *Il metodo <em>getNSegnalazioni</em> serve a ottenere il numero di segnalazioni effettuate dell'evento avverso.
	 * @return Un valore di tipo <i>int</i> che identifica il numero di segnalazioni effettuate in un centro vaccinale di un evento avverso.
	 */
	public int getNSegnalazioni() {
		return NSegnalazioni;
	}
	

}
