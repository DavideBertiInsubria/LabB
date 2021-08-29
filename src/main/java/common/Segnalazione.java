package common;

import java.io.Serializable;
/**
 *La classe <em>Segnalazione</em> viene utilizzata per creare un oggetto che contenga una segnalazione di un evento avverso.
 *@author Ivanov Aleksandar Evgeniev, Mazza Serghej, Berti Davide, Rizzi Silvio
 */
public class Segnalazione implements Serializable {
	
	private int IDVaccinazione;
	private String Evento;
	private int Severita;
	private String Nota;

	/**
	 * Costruttore utilizzato per creare un oggetto di tipo <i>Segnalazione</i> con tutti i parametri inizializzati.
	 * @param IDVaccinazione Parametro di tipo <i>int</i> che identifica l'ID vaccinazione di chi ha effetuato la segnalazione.
	 * @param Evento Parametro di tipo <i>String</i> per identificare il nome dell'evento avverso
	 * @param Severita
	 * @param Nota Parametro di tipo <i>String</i> per identificare la nota opzionale inserita dall'utente.
	 */
	public Segnalazione(int IDVaccinazione,String Evento,int Severita,String Nota) {
		this.IDVaccinazione = IDVaccinazione;
		this.Evento = Evento;
		this.Severita = Severita;
		this.Nota = Nota;
	}

	/**
	 * Il metodo <em>getEvento</em> serve a ottenere il nome dell'evento avverso.
	 * @return Un valore di tipo <i>String</i> che idenfitica il nome dell'evento.
	 */
	public String getEvento() {
		return Evento;
	}

	/**
	 * Il metodo <em>getIDVaccinazione</em> serve a ottenere l'ID vaccinazione dell'utente.
	 * @return Un valore di tipo <i>int</i>.
	 */
	public int getIDVaccinazione() {
		return IDVaccinazione;
	}

	/**
	 * Il metodo <em>getSeverita</em> serve a ottenere la severità dell'evento segnalato.
	 * @return Un valore di tipo <i>int</i>.
	 */
	public int getSeverita() {
		return Severita;
	}

	/**
	 * Il metodo <em>getNota</em> serve a ottenere la nota opzionale inserita dall'utente.
	 * @return Un valore di tipo <i>String</i> contente la nota.
	 */
	public String getNota() {
		return Nota;
	}

	/**
	 * Il metodo <em>setNota</em> serve a impostare la nota opzionale inserita dall'utente.
	 * @param Nota Un valore di tipo <i>String</i> contente la nota.
	 */
	public void setNota(String Nota){
		this.Nota=Nota;
	}

}
