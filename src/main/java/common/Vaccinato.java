package common;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
/**
 * La classe <em>Vaccinato</em> viene utilizzata per creare un oggetto che identifichi un vaccinato all'interno dell'applicazione.
 *
 * @author Berti Davide -
 * @author Ivanov Aleksandar Evgeniev - 742789 VA
 * @author Mazza Serghej - 740687 VA
 * @author Rizzi Silvio - 719638 VA
 */
public class Vaccinato implements Serializable {
    private CentroVaccinale Centro;
    private String cognome,CF,vaccino,nome;
    private int IDCentro,IDVaccino;
    private LocalDate data;

    /**
     * Costruttore utilizzato per creare un oggetto di tipo <i>Vaccinato</i>.
     * @param Centro Parametro di tipo <i>CentroVaccinale</i> che identifica il centro dove &egrave; stata effettuata la vaccinazione dell'utente in consinderazione.
     * @param nome Parametro di tipo <i>String</i> utilizzato per identificare il nome del vaccinato.
     * @param cognome Parametro di tipo <i>String</i> utilizzato per identificare il cognome del vaccinato.
     * @param CF Parametro di tipo <i>String</i> utilizzato per identificare il codice fiscale del vaccinato.
     * @param vaccino Parametro di tipo <i>String</i> utilizzato per identificare il tipo di vaccino iniettato.
     * @param IDVaccino Parametro di tipo <i>int</i> utilizzato per salvare l'ID vaccinazione comunicata al momento della vaccinazione.
     * @param data Parametro di tipo <i>LocalDate</i> utilizzato per identificare la data in cui &egrave; stato effettuato il vaccino.
     * @see CentroVaccinale
     */
    public Vaccinato(CentroVaccinale Centro, String nome, String cognome, String CF, String vaccino, int IDVaccino, LocalDate data) {
        this.nome = nome;
        this.cognome = cognome;
        this.CF = CF;
        this.vaccino = vaccino;
        this.IDVaccino = IDVaccino;
        this.data = data;
        this.Centro = Centro;
    }

    /**
     * Costruttore utilizzato per creare un oggetto di tipo <I>Vaccinato</I> senza dover inizializzare i parametri.
     */
    public Vaccinato() {

    }

    /**
     * Il metodo <em>getIDVaccino</em> serve a ottenere l'ID vaccinazione dell'utente.
     * @return Un valore di tipo <i>int</i> che identifica l'ID vaccinazione dell'utente.
     */
    public int getIDVaccino () {
        return IDVaccino;
    }

    /**
     * Il metodo <em>setIDVaccino</em> serve a impostare l'ID vaccinazione dell'utente.
     * @param IDVaccino Un valore di tipo <i>int</i> che identifica l'ID vaccinazione dell'utente.
     */
    public void setIDVaccino (int IDVaccino) {
        this.IDVaccino = IDVaccino;
    }

    /**
     * Il metodo <em>getVaccino</em> serve a ottenere il tipo di vaccino somministrato all'utente.
     * @return Un valore di tipo <i>String</i> che contiene il tipo di vaccino somministrato.
     */
    public String getVaccino () {
        return vaccino;
    }

    /**
     *  Il metodo <em>setVaccino</em> serve a impostare il tipo di vaccino somministrato all'utente.
     * @param vaccino Un valore di tipo <i>String</i> che contiene il tipo di vaccino somministrato.
     */
    public void setVaccino (String vaccino) {
        this.vaccino = vaccino;
    }

    /**
     * Il metodo <em>getCF</em> serve a ottenere il codice fiscale dell'utente.
     * @return Un valore di tipo <i>String</i> che contiene il codice fiscale.
     */
    public String getCF () {
        return CF;
    }

    /**
     * Il metodo <em>setCF</em> serve a impostare il codice fiscale dell'utente.
     * @param CF Un valore di tipo <i>String</i> che contiene il codice fiscale.
     */
    public void setCF (String CF) {
        this.CF = CF;
    }

    /**
     * Il metodo <em>getCognome</em> serve a ottenere il cognome dell'utente.
     * @return Un valore di tipo <i>String</i> che contiene il cognome.
     */
    public String getCognome () {
        return cognome;
    }

    /**
     * Il metodo <em>setCognome</em> serve a impostare il cognome dell'utente.
     * @param cognome Un valore di tipo <i>String</i> che contiene il cognome.
     */
    public void setCognome (String cognome) {
        this.cognome = cognome;
    }

    /**
     * Il metodo <em>getNome</em> serve a ottenere il nome dell'utente.
     * @return Un valore di tipo <i>String</i> che contiene il nome.
     */
    public String getNome () {
        return nome;
    }

    /**
     * Il metodo <em>setNome</em> serve a impostare il nome dell'utente.
     * @param nome Un valore di tipo <i>String</i> che contiene il nome.
     */
    public void setNome (String nome) {
        this.nome = nome;
    }

    /**
     * Il metodo <em>getIDcentro</em> serve a ottenere l'ID del centro.
     * @return Un valore di tipo <i>int</i> che contiene l'ID del centro.
     */
    public int getIDCentro () {
        return IDCentro;
    }

    /**
     * Il metodo <em>setIDcentro</em> serve a impostare l'ID del centro.
     * @param IDCentro Un valore di tipo <i>int</i> che contiene l'ID del centro.
     */
    public void setIDCentro (int IDCentro) {
        this.IDCentro = IDCentro;
    }

    /**
     * Il metodo <em>getCentro</em> serve a ottenere il centro vaccinale presso cui è stato registrato.
     * @return Un riferimento ad un oggetto di tipo <i>CentroVaccinale</i>.
     * @see CentroVaccinale
     */
    public CentroVaccinale getCentro() {
    	return Centro;
    }

    /**
     * Il metodo <em>setCentro</em> serve a impostare il centro vaccinale presso cui è stato registrato.
     * @param Centro Un riferimento ad un oggetto di tipo <i>CentroVaccinale</i>.
     * @see CentroVaccinale
     */
    public void setCentro(CentroVaccinale Centro) {
    	this.Centro = Centro;
    }

    /**
     * Il metodo <em>getDatasomm</em> serve a ottenere la data di somministrazione del vaccino.
     * @return Un riferimento ad un oggetto di tipo <i>LocalDate</i> contente la data.
     * @see LocalDate
     */
    public LocalDate getDatasomm(){ return data;}

}
