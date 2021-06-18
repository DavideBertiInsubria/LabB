package common;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    String msg(String messaggio) throws RemoteException;
    CentroVaccinale ritornaCentro(CentroVaccinale CV)throws RemoteException;
    CentroVaccinale[] ritornaCentri(CentroVaccinale[] CV)throws RemoteException;

}
