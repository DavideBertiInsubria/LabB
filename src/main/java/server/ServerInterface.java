package server;

import common.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 */
public interface ServerInterface extends Remote {
    /**
     *
     * @param c
     * @throws RemoteException
     */
    void registraCittadino(Cittadino c) throws RemoteException;

    /**
     *
     * @param seg
     * @throws RemoteException
     */
    void registraSegnalazione(Segnalazione seg) throws RemoteException;

    /**
     *
     * @param v
     * @throws RemoteException
     */
    void registraVaccinato(Vaccinato v) throws RemoteException;

    /**
     *
     * @param CV
     * @throws RemoteException
     */
    void registraCentroVaccinale(CentroVaccinale CV) throws RemoteException;

    /**
     *
     * @param nome
     * @param comune
     * @param tipo
     * @return
     * @throws RemoteException
     */
    ArrayList<CentroVaccinale> cercaCentroVaccinale(String nome, String comune, String tipo) throws RemoteException;

    /**
     *
     * @param CV
     * @throws RemoteException
     */
    void visualizzaInfoCentroVaccinale(CentroVaccinale CV)  throws RemoteException;

    /**
     *
     * @param userID
     * @param password
     * @return
     * @throws RemoteException
     */
    Cittadino login(String userID, String password) throws RemoteException;
}
