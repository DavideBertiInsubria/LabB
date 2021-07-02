package common;

import java.io.Serializable;

public class Cittadino extends Vaccinato implements Serializable {
    private String email, password, userID;

    public Cittadino(String cf,String nome,String cognome, String email,String pwd,String idvaccinazione,String idcentro) {
        super();
        this.setNome (nome);
        this.setCognome (cognome);
        this.email = email;
        this.password = pwd;
        this.setIDVaccino (idvaccinazione);
        this.setCF (cf);
        this.setIDCentro (idcentro);
    }

    public Cittadino(){
     super();
    }

    public String getCF() {
        return super.getCF ();
    }
    public String getNome() {
        return super.getNome ();
    }

    public String getCognome() {
        return super.getCognome ();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getIDVaccinazione() {
        return super.getIDVaccino ();
    }

    public String getIDCentro() {
        return super.getIDCentro ();
    }
}
