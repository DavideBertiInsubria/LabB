package cittadini;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements ClientInterface {

    @Override
    public String msg(String messaggio) throws RemoteException {
        return messaggio;
    }
}
