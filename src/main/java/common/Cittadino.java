package common;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * La classe <em>Cittadino</em> viene utilizzata per creare un oggetto che identifichi un cittadino all'interno del sistema.
 *
 * @author Berti Davide -
 * @author Ivanov Aleksandar Evgeniev - 742789 VA
 * @author Mazza Serghej - 740687 VA
 * @author Rizzi Silvio - 719638 VA
 */
public class Cittadino extends Vaccinato implements Serializable {
    private String email, password, userID;
    private int idvaccinazione;

    /**
     *Costruttore per creare un oggetto di tipo <i>Cittadino</i> che richiama i metodi della superclasse vaccinato per assegnargli i valori dei parametri.
     * @param cf Parametro di tipo <i>String</i> per identificare il codice fiscale.
     * @param nome Parametro di tipo <i>String</i> per identificare il nome.
     * @param cognome Parametro di tipo <i>String</i> per identificare il cognome.
     * @param userID Parametro di tipo <i>String</i> per identificare il nickname.
     * @param email Parametro di tipo <i>String</i> per identificare la email.
     * @param pwd Parametro di tipo <i>String</i> per identificare la password.
     * @param idvaccinazione Parametro di tipo <i>Int</i> per identificare l'ID vaccinazione.
     */
    public Cittadino(String cf,String nome,String cognome, String userID, String email,String pwd,int idvaccinazione) {

        super.setNome(nome);
        super.setCognome(cognome);
        super.setCF(cf);
        super.setIDVaccino(idvaccinazione);
        this.userID=userID;
        this.email = email;
        this.password = pwd;
       
    }

    /**
     *Il metodo <em>getEmail</em> serve a ottenere l'email del cittadino.
     * @return Il valore dell'email.
     */
    public String getEmail() {return email;}

    /**
     *Il metodo <em>getPassword</em> serve a ottenere la password del cittadino.
     * @return Il valore della password.
     */
    public String getPassword() {return password;}

    /**
     *Il metodo <em>getUserID</em> serve a ottenere lo UserID del cittadino.
     * @return Il valore dello UserID.
     */
    public String getUserID() {return userID;}
    
}
