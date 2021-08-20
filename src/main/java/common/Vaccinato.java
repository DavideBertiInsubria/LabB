package common;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
/**
 *La classe <em>Vaccinato</em> viene utilizzata per creare un oggetto che identifichi un vaccinato all'interno dell'applicazione.
 *@author Ivanov Aleksandar Evgeniev, Mazza Serghej, Berti Davide, Rizzi Silvio
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


    public int getIDVaccino () {
        return IDVaccino;
    }

    public void setIDVaccino (int IDVaccino) {
        this.IDVaccino = IDVaccino;
    }

    public String getVaccino () {
        return vaccino;
    }

    public void setVaccino (String vaccino) {
        this.vaccino = vaccino;
    }

    public String getCF () {
        return CF;
    }

    public void setCF (String CF) {
        this.CF = CF;
    }

    public String getCognome () {
        return cognome;
    }

    public void setCognome (String cognome) {
        this.cognome = cognome;
    }

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public int getIDCentro () {
        return IDCentro;
    }

    public void setIDCentro (int IDCentro) {
        this.IDCentro = IDCentro;
    }
    
    public CentroVaccinale getCentro() {
    	return Centro;
    }
    
    public void setCentro(CentroVaccinale Centro) {
    	this.Centro = Centro;
    }

    public LocalDate getDatasomm(){ return data;}

}
