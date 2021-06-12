package cittadini;

import common.CentroVaccinale;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    String msg(String messaggio) throws RemoteException;
    CentroVaccinale ritornaCentro(CentroVaccinale CV)throws RemoteException;
    void ritornaCentri(CentroVaccinale[] CV)throws RemoteException;

}
