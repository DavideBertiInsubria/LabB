package centrivaccinali;

import common.CentroVaccinale;
import common.Cittadino;
import common.TipologiaCentro;
import common.Vaccinato;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

    @Override
    public void registraCittadino (Cittadino c){

    }

    @Override
    public void registraVaccinato (Vaccinato v) {

    }

    @Override
    public void registraCentroVaccinale (CentroVaccinale CV) {

    }

    @Override
    public CentroVaccinale cercaCentroVaccinale (String nome, String comune, TipologiaCentro tipo) {
        return null;
    }

    @Override
    public String visualizzaInfoCentroVaccinale (CentroVaccinale CV) {
        return null;
    }
}
