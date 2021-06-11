package centrivaccinali;

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
    protected ServerImpl () throws RemoteException {
    }

    //private Database DBconnetion;

    @Override
    public void registraCittadino (Cittadino c){

    }

    @Override
    public void registraVaccinato (Vaccinato v) {

    }

    @Override
    public void registraCentroVaccinale (CentroVaccinale CV) {

    }

    @Override
    public CentroVaccinale cercaCentroVaccinale (String nome, String comune, TipologiaCentro tipo) {
        return null;
    }

    @Override
    public String visualizzaInfoCentroVaccinale (CentroVaccinale CV) {
        return null;
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
