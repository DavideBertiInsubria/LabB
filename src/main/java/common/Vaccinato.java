package common;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Vaccinato implements Serializable {
    private String nome,cognome,CF,vaccino,NomeCentro;
    private int IDCentro,IDVaccino;
    private LocalDate data;
    private Date DataSV;


    public Vaccinato(String NomeCentro, String nome, String cognome, String CF, String vaccino, int IDVaccino, LocalDate data) {
        this.nome = nome;
        this.cognome = cognome;
        this.CF = CF;
        this.vaccino = vaccino;
        this.IDVaccino = IDVaccino;
        this.data = data;
        this.NomeCentro = NomeCentro;
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
    
    public String getNomeCentro() {
    	return NomeCentro;
    }
    
    public void setNomeCentro(String NomeCentro) {
    	this.NomeCentro = NomeCentro;
    }

}
