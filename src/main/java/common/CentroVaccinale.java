package common;

import java.io.Serializable;

public class CentroVaccinale implements Serializable {
    private String nome, Indirizzo;
    private TipologiaCentro tipologia;

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public String getIndirizzo () {
        return Indirizzo;
    }

    public void setIndirizzo (String indirizzo) {
        Indirizzo = indirizzo;
    }
}
