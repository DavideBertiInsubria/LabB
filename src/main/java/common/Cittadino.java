package common;

import java.io.Serializable;
import java.rmi.RemoteException;

public class Cittadino extends Vaccinato implements Serializable {
    private String email, password, userID;

    public Cittadino(String cf,String nome,String cognome, String userID, String email,String pwd,String idvaccinazione,String idcentro) {
       
        super.setIDCentro(idcentro);
        super.setNome(nome);
        super.setCognome(cognome);
        super.setCF(cf);
        this.userID=userID;
        this.email = email;
        this.password = pwd;
       
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
}
