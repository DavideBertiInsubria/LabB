package common;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Vaccinato implements Serializable {
    private CentroVaccinale Centro;
    private String cognome,CF,vaccino,nome;
    private int IDCentro,IDVaccino;
    private LocalDate data;
    private Date DataSV;


    public Vaccinato(CentroVaccinale Centro, String nome, String cognome, String CF, String vaccino, int IDVaccino, LocalDate data) {
        this.nome = nome;
        this.cognome = cognome;
        this.CF = CF;
        this.vaccino = vaccino;
        this.IDVaccino = IDVaccino;
        this.data = data;
        this.Centro = Centro;
    }

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

}
