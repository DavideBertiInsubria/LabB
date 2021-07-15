package common;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Vaccinato implements Serializable {
    private String IDCentro,nome,cognome,CF,vaccino,IDVaccino;
    private LocalDate data;
    private Date DataSV;


    public Vaccinato(String IDCentro, String nome, String cognome, String CF, String vaccino, String IDVaccino, LocalDate data) {
        this.IDCentro = IDCentro;
        this.nome = nome;
        this.cognome = cognome;
        this.CF = CF;
        this.vaccino = vaccino;
        this.IDVaccino = IDVaccino;
        this.data = data;
    }

    public Vaccinato() {

    }


    public String getIDVaccino () {
        return IDVaccino;
    }

    public void setIDVaccino (String IDVaccino) {
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

    public String getIDCentro () {
        return IDCentro;
    }

    public void setIDCentro (String IDCentro) {
        this.IDCentro = IDCentro;
    }

}
