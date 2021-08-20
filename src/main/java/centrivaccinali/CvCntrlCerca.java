package centrivaccinali;

import common.CentroVaccinale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import server.ServerInterface;

import javax.swing.*;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

/**
 * Controller associato a <code>CvCerca</code>.
 * @author Berti Davide
 * @author Ivanov Aleksandar Evgeniev
 * @author Mazza Serghej
 * @author Rizzi Silvio 719638 VA
 * @see CvCerca
 */
public class CvCntrlCerca {
    @FXML
    private ListView<String> listCentriVaccinali;

    private ArrayList<CentroVaccinale> listaCentriVaccinaliVisualizzati = new ArrayList<CentroVaccinale>();

    private CvCntrlVaccinato CNTRLV;

    /**
     * Invoca il metodo interno <code>compilaLista</code>.
     * @throws RemoteException se la connessione al server fallisce
     */
    public void setDati(CvCntrlVaccinato cntrlV) throws RemoteException {
        compilaLista();
        CNTRLV = cntrlV;
    }

    /**
     * Popola la lista con i nominativi di centri vaccinali registrati in database.
     */
    private void compilaLista() {
        // COLLEGAMENTO A SERVER
        ServerInterface server = null;
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            server = (ServerInterface) reg.lookup("Vaccino");
        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Connessione al server fallita.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        listCentriVaccinali.getItems().clear();
        listaCentriVaccinaliVisualizzati.clear();
        // SET LISTA
        try {
            listaCentriVaccinaliVisualizzati = server.cercaCentroVaccinale("", "", "");
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Errore con il collegamento al server.");
            e.printStackTrace();
            System.exit(0);
        }
        for (CentroVaccinale centroVaccinale : listaCentriVaccinaliVisualizzati) {
            listCentriVaccinali.getItems().add(centroVaccinale.getNome() + " - " + centroVaccinale.getIndirizzo());
        }
    }

    /**
     * Chiude la finestra corrente e crea una nuova istanza di <code>CvRegVaccinato</code> in cui &egrave; caricato
     * il nome del centro vaccinale selezionato. L'applicazione richiede che il nominativo di un centro vaccinale sia
     * obbligatoriamente selezionato. &Egrave; associato al bottone di conferma.
     * @param event il riferimento all'evento associato
     * @see CvRegVaccinato
     * @see ActionEvent
     */
    public void clickOK (ActionEvent event){
        if (listCentriVaccinali.getSelectionModel().getSelectedIndex() == -1 ){
            JOptionPane.showMessageDialog(null, "Selezionare un centro vaccinale dalla lista.");
        } else {
            try {
                // CHIUSURA
                Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                thisWindow.close();
                CNTRLV.setDati(listaCentriVaccinaliVisualizzati.get(listCentriVaccinali.getSelectionModel().getSelectedIndex()));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
