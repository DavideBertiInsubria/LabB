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
     *
     * @param c
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
     *
     * @param seg
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
     *
     * @param v
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
     *
     * @param CV
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
     *
     * @param nome
     * @param comune
     * @param tipo
     * @return
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
     *
     * @param CV
     */
    public synchronized void visualizzaInfoCentroVaccinale (CentroVaccinale CV) {
        //Query per le informazione del centro
        String info = null;

    }

    /**
     *
     * @param userID
     * @param password
     * @return
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
     *
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
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[])throws Exception {//Main per inizializzare il server

        ServerImpl server = new ServerImpl ();
        server.exec();
    }

}
