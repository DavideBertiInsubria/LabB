package common;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 *La classe <em>Cittadino</em> viene utilizzata per creare un oggetto che identifichi un cittadino all'interno del sistema.
 *@author Ivanov Aleksandar Evgeniev, Mazza Serghej, Berti Davide, Rizzi Silvio
 */
public class Cittadino extends Vaccinato implements Serializable {
    private String email, password, userID;
    private int idvaccinazione;

    /**
     *
     * @param cf
     * @param nome
     * @param cognome
     * @param userID
     * @param email
     * @param pwd
     * @param idvaccinazione
     * @param nomecentro
     */
    public Cittadino(String cf,String nome,String cognome, String userID, String email,String pwd,int idvaccinazione,String nomecentro) {
       
        super.setNomeCentro(nomecentro);
        super.setNome(nome);
        super.setCognome(cognome);
        super.setCF(cf);
        super.setIDVaccino(idvaccinazione);
        this.userID=userID;
        this.email = email;
        this.password = pwd;
       
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return
     */
    public String getUserID() {
    	return userID;
    }
    
}
