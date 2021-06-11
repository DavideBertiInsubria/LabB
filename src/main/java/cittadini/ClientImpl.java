package cittadini;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements ClientInterface {

    protected ClientImpl() throws RemoteException {
    }

    @Override
    public String msg(String messaggio) throws RemoteException {
        return messaggio;
    }
}
