package server;

import DBManagement.*;
import common.*;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

    private final DBVaccinazioniManagement Database;

    protected ServerImpl() throws RemoteException, SQLException {
        Database = new DBVaccinazioniManagement ();
    }

    public synchronized void registraCittadino (Cittadino c, ClientInterface utente){
        try {
            Database.registraCittadino (c);
            utente.msg("La registrazione è andata a buon fine");
        } catch (SQLException | RemoteException e) {
            e.printStackTrace ();
            try {
                utente.msg("La registrazione non è andata a buon fine riprovare");
            } catch (RemoteException ee) {
                ee.printStackTrace ();
            }
        }
    }

    @Override
    public synchronized void registraVaccinato (Vaccinato v, ClientInterface utente) {
        //query per la registrazione del vaccinato
        try {
            utente.msg("La registrazione non è andata a buon fine riprovare");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void registraCentroVaccinale (CentroVaccinale CV, ClientInterface utente) {

        try {
            Database.registraCentroVaccinale (CV);
            utente.msg("Registrazione effettuata");
        } catch (SQLException | RemoteException e) {
            e.printStackTrace ();
            try {
                utente.msg("Qualcosa è andato storto");
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace ();
            }
        }

    }

    public ArrayList<CentroVaccinale> cercaCentroVaccinale (String nome, String comune, TipologiaCentro tipo, ClientInterface utente) {
        //Query di ricerca
        ArrayList CV = null;//= risultato della query
        return CV;
    }

    public synchronized void visualizzaInfoCentroVaccinale (CentroVaccinale CV, ClientInterface utente) {
        //Query per le informazione del centro
        String info = null;
        try {
            utente.msg(info);
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }


    @Override
    public Cittadino login (String userID, String password) throws RemoteException {
        //Database.loginCittadino (userID,password);
        return null; // null se non è stato trovato
    }

    private void exec() throws  RemoteException {
        try {
            ServerImpl serverR= new ServerImpl ();
            Registry registro = LocateRegistry.createRegistry (1099);
            registro.rebind ("Vaccino", serverR);

        } catch (
                IOException | SQLException e ) {
            e.printStackTrace ();
        }
    }

    public static void main(String args[])throws Exception {//Main per inizializzare il server

        ServerImpl server = new ServerImpl ();
        server.exec();
    }

}
