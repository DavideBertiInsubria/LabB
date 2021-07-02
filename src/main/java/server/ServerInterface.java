package server;

import common.ClientInterface;
import common.CentroVaccinale;
import common.Cittadino;
import common.TipologiaCentro;
import common.Vaccinato;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface extends Remote {

    void registraCittadino(Cittadino c, ClientInterface utente) throws RemoteException;
    void registraVaccinato(Vaccinato v, ClientInterface operatore) throws RemoteException;
    void registraCentroVaccinale(CentroVaccinale CV,ClientInterface operatore) throws RemoteException;
    ArrayList<CentroVaccinale> cercaCentroVaccinale(String nome, String comune, String tipo, ClientInterface utente) throws RemoteException;
    void visualizzaInfoCentroVaccinale(CentroVaccinale CV, ClientInterface utente)  throws RemoteException;
    Cittadino login(String userID, String password) throws RemoteException;
}
