package common;

import java.util.Date;

public class Vaccinato {
    private String nomeCentro,nome,cognome,CF,vaccino,IDVaccino;
    Date DataSV;

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

    public String getNomeCentro () {
        return nomeCentro;
    }

    public void setNomeCentro (String nomeCentro) {
        this.nomeCentro = nomeCentro;
    }
}
