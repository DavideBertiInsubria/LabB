package server;

import common.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * La classe <em>ServerInterface</em> &egrave; un'interfaccia usata per rendere disponibili i servizi offerti dal server alle applicazioni esterne.
 */
public interface ServerInterface extends Remote {
    /**
     *
     * @param c
     * @throws RemoteException
     */
    void registraCittadino(Cittadino c) throws RemoteException;

    boolean checkSegnalazione(int ID, String evento)throws RemoteException, SQLException;

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
    ArrayList<ReportEventoAvverso> visualizzaInfoCentroVaccinale(CentroVaccinale CV)  throws RemoteException;

    /**
     *
     * @param userID
     * @param password
     * @return
     * @throws RemoteException
     */
    Cittadino login(String userID, String password) throws RemoteException;
}
