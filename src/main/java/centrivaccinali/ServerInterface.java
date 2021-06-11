package centrivaccinali;

import common.CentroVaccinale;
import common.Cittadino;
import common.TipologiaCentro;
import common.Vaccinato;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    void registraCittadino(Cittadino c) throws RemoteException;
    void registraVaccinato(Vaccinato v) throws RemoteException;
    void registraCentroVaccinale(CentroVaccinale CV) throws RemoteException;
    CentroVaccinale cercaCentroVaccinale(String nome, String comune, TipologiaCentro tipo) throws RemoteException;
    String visualizzaInfoCentroVaccinale(CentroVaccinale CV)  throws RemoteException;
}
