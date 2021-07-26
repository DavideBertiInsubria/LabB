package server;

import DBManagement.*;
import common.*;
import javafx.scene.chart.PieChart;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/** @author Invanov Aleksandar Evgeniev, Mazza Serghej, Berti Davide, Rizzi Silvio
 * La classe <em>ServerImpl</em> viene utilizzata per implementare i metodi resi disponibili ai client tramite l'interfaccia ServerInterface.
 */

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
    /**
     * <code>Database</code> &egrave; il riferimento al database utilizzato per inserire e reperire i dati ed è un oggetto di tipo <i>DBVaccinazioniManagement</i>.
     * @see DBManagement.DBVaccinazioniManagement
     */
    private final DBVaccinazioniManagement Database;

    /**
     * Il costruttore <em>ServerImpl</em> serve ad associare al server il suo database.
     * @throws RemoteException
     * @throws SQLException
     */
    protected ServerImpl() throws RemoteException, SQLException {
        Database = new DBVaccinazioniManagement ();
    }

    /**
     *Il metodo <em>registraCittadino</em> serve a registrare i dati di un cittadino sul database.
     * @param c Riferimento ad un oggetto di tipo <i>Cittadino</i> che contiene le informazioni da aggiungere al database
     * @see common.Cittadino
     */
    public synchronized void registraCittadino (Cittadino c){
        System.out.println ("Registrazione cittadino");
        try {
            Database.registraCittadino (c);
            System.out.println ("La registrazione del cittadino è andata a buon fine");
        } catch (SQLException e) {
            e.printStackTrace ();
            System.out.println("La registrazione del cittadino non è andata a buon fine riprovare");
        }
    }

    /**
     *Il metodo <em>registraCittadino</em> serve a registrare i dati di una segnalazione sul database da parte di un cittadino registrato.
     * @param seg Riferimento ad un oggetto di tipo <i>Segnalazione</i> che contiene i dati riguardo ad una segnalazione da effettuare.
     * @see common.Segnalazione
     */
    public synchronized void registraSegnalazione (Segnalazione seg){
        System.out.println ("Registrazione segnalazione");
        if(seg.getNota()==null){
            seg.setNota("");
        }
        try {
            Database.registraSegnalazione (seg);
            System.out.println ("La registrazione della segnalazione è andata a buon fine");
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
            System.out.println("La registrazione della segnalazione non è andata a buon fine riprovare");
        }
    }

    /**
     *Il metodo <em>registraVaccinato</em> serve a registrare i dati di una persona vaccinata da parte di un operatore sanitario tramite l'apposita applicazione su database.
     * @param v Riferimento ad un oggetto di tipo <i>Vaccinato</i> che contiene i dati di un vaccinato da inserire nel database.
     * @see common.Vaccinato
     */
    @Override
    public synchronized void registraVaccinato (Vaccinato v) {
        System.out.println ("Registrazione Vaccinato");
       /* try {
            //Database.registraVaccinato ();
        }catch (SQLException throwables){
            throwables.printStackTrace ();
            System.out.println("La registrazione del vaccinato non è andata a buon fine riprovare");
        }*/
        //query per la registrazione del vaccinato
    }

    /**
     *Il metodo <em>registraCentroVaccinale</em> serve a registrare i dati di un nuovo centro vaccinale sul database.
     * @param CV Riferimento ad un oggetto di tipo CentroVaccinale.
     * @see common.CentroVaccinale
     */
    @Override
    public synchronized void registraCentroVaccinale (CentroVaccinale CV) {
        System.out.println("Registrazione centro vaccinale");
        //gesu bastardo
        try {
            Database.registraCentroVaccinale (CV);
            System.out.println("Registrazione effettuata");
        } catch (SQLException e) {
            e.printStackTrace ();
        }

    }

    /**
     *Il metodo cercaCentroVaccinale serve a cercare un centro vaccinale nel database
     * @param nome Riferimento di tipo String che contiene il nome del centro da ricercare.
     * @param comune Riferimento di tipo String che contiene il nome del comune del centro da ricercare.
     * @param tipo Riferimento di tipo String che contiene il nome del tipo del centro da ricercare.
     * @return Il metodo ritorna un arraylist di centri vaccinali che corrispondono ai parametri inseriti.
     */
    public ArrayList<CentroVaccinale> cercaCentroVaccinale (String nome, String comune, String tipo) {
        System.out.println ("Cerca centro vaccinale");
        ArrayList<CentroVaccinale> CV = new ArrayList<CentroVaccinale>();
        ResultSet centri=null;
        try {
            centri = Database.cercaCentroVaccinale (nome,comune,tipo);
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        if(centri != null) {
	        try {
	            while(centri.next()){
	                CV.add(new CentroVaccinale (centri.getString(1), centri.getString (2).replace(";"," "), centri.getString (3)));
	            }
	            System.out.println ("Ritorno centri vaccinale");
	            return CV;
	        } catch (SQLException throwables) {
	            throwables.printStackTrace ();
	        }
        }
        return null;
    }

    /**
     *Il metodo <em>visualizzaInfoCentroVaccinale</em>  serve a visualizzare le informazioni di un centro vaccinale selezionato dall'utente sull'applicazione.
     * @param CV Riferimento di tipo CentroVaccinale che contiene un oggetto contente i dati del centro da visualizzare.
     * @see common.CentroVaccinale
     */
    public synchronized void visualizzaInfoCentroVaccinale (CentroVaccinale CV) {
        //Query per le informazione del centro
        String info = null;

    }

    /**
     *Il metodo <em>login</em> serve ad autenticare un cittadino registrato dall'applicazione.
     * @param userID Riferimento di tipo String che contiene lo userID inserito dall'utente per l'autenticazione.
     * @param password Riferimento di tipo String che contiene la password inserita dall'utente per l'autenticazione.
     * @return Il metodo ritorna un oggetto di tipo <i>Cittadino</i> che contiene i dati relativi all'utente autenticato se l'autenticazione &egrave; andata a buon fine altrimenti ritorna <i>NULL</i>.
     * @throws RemoteException
     */
    @Override
    public Cittadino login (String userID, String password) throws RemoteException {
        ResultSet cittadini = null;
        try {
            cittadini=Database.loginCittadino (userID,password);
            return new Cittadino (cittadini.getString ("cf"),cittadini.getString ("nome"),cittadini.getString ("cognome"),cittadini.getString ("nick"),cittadini.getString ("email"),cittadini.getString ("password"),cittadini.getInt ("idvaccinazione"),"");
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
            return null;
        }
    }

    /**
     *Il metodo <em>exec</em> &egrave; usato per inizializzare il server e renderne disponibili i servizi.
     * @throws RemoteException
     */
    private void exec() throws  RemoteException {
        try {
            ServerImpl serverR= new ServerImpl ();
            Registry registro = LocateRegistry.createRegistry (1099);
            registro.rebind ("Vaccino", serverR);
            System.out.println("Server Acceso");

        } catch (
                IOException | SQLException e ) {
            e.printStackTrace ();
        }
    }

    /**
     * Il main &egrave; utilizzato per eseguire il server.
     * @param args
     * @throws Exception
     */
    public static void main(String args[])throws Exception {

        ServerImpl server = new ServerImpl ();
        server.exec();
    }

}
