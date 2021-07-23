package server;

import common.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface extends Remote {

    void registraCittadino(Cittadino c) throws RemoteException;
    void registraSegnalazione(Segnalazione seg) throws RemoteException;
    void registraVaccinato(Vaccinato v) throws RemoteException;
    void registraCentroVaccinale(CentroVaccinale CV) throws RemoteException;
    ArrayList<CentroVaccinale> cercaCentroVaccinale(String nome, String comune, String tipo) throws RemoteException;
    void visualizzaInfoCentroVaccinale(CentroVaccinale CV)  throws RemoteException;
    Cittadino login(String userID, String password) throws RemoteException;
}
