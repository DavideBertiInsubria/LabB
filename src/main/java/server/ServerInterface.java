package server;

import common.ClientInterface;
import common.CentroVaccinale;
import common.Cittadino;
import common.TipologiaCentro;
import common.Vaccinato;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    void registraCittadino(Cittadino c, ClientInterface utente) throws RemoteException;
    void registraVaccinato(Vaccinato v, ClientInterface operatore) throws RemoteException;
    void registraCentroVaccinale(CentroVaccinale CV,ClientInterface operatore) throws RemoteException;
    void cercaCentroVaccinale(String nome, String comune, TipologiaCentro tipo, ClientInterface utente) throws RemoteException;
    void visualizzaInfoCentroVaccinale(CentroVaccinale CV, ClientInterface utente)  throws RemoteException;
}
