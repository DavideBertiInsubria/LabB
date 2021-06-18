package server;

import common.ClientInterface;
import common.CentroVaccinale;
import common.Cittadino;
import common.TipologiaCentro;
import common.Vaccinato;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

    //private Database DBconnetion;

    protected ServerImpl() throws RemoteException {
    }

    public void registraCittadino (Cittadino c, ClientInterface utente){
        //QUERY PER LA REGISTRAZIONE DEL CITTADINO
        boolean successfull = false;
        if(successfull){
            try {
                utente.msg("Registrazione effettuata");
            } catch (RemoteException e) {
                try {
                    utente.msg("Qualcosa è andato storto");
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
            }
        }
        else{
            try {
                utente.msg("La registrazione non è andata a buon fine riprovare");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return;
    }

    @Override
    public void registraVaccinato (Vaccinato v, ClientInterface utente) {
        //query per la registrazione del vaccinato
        boolean successfull = false;
        if(successfull){
            try {
                utente.msg("Registrazione effettuata");
                return;
            } catch (RemoteException e) {
                try {
                    utente.msg("Qualcosa è andato storto");
                    return;
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
            }
        }
        else{
            try {
                utente.msg("La registrazione non è andata a buon fine riprovare");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return;
        }
    }

    @Override
    public void registraCentroVaccinale (CentroVaccinale CV, ClientInterface utente) {
        //Query per la registrazione del centro vaccinale
        boolean successfull = false;
        if(successfull){
            try {
                utente.msg("Registrazione effettuata");
                return;
            } catch (RemoteException e) {
                try {
                    utente.msg("Qualcosa è andato storto");
                    return;
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
            }
        }
        else{
            try {
                utente.msg("La registrazione non è andata a buon fine riprovare");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return;
        }
    }

    public void cercaCentroVaccinale (String nome, String comune, TipologiaCentro tipo, ClientInterface utente) {
        //Query di ricerca
        CentroVaccinale[] CV = null;//= risultato della query
        try {
            utente.ritornaCentri(CV);
        }catch (RemoteException e)
        {
            try {
               utente.msg("Qualcosa è andato storto");
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
        }
        return;

    }

    public void visualizzaInfoCentroVaccinale (CentroVaccinale CV, ClientInterface utente) {
        //Query per le informazione del centro
        String info = null;
        try {
            utente.msg(info);
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    public void login (String email, String password) throws RemoteException {

    }

    private void exec(){
        try {
            ServerImpl serverR= new ServerImpl ();
            Registry registro = LocateRegistry.createRegistry (1099);
            registro.rebind ("Vaccino", serverR);

        } catch (
                IOException e) {
            e.printStackTrace ();
        }
    }
    public static void main(String args[])throws Exception {//Main per inizializzare il server

        ServerImpl server = new ServerImpl ();
        server.exec();
    }

}
