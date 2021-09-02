package centrivaccinali;

import common.CentroVaccinale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import server.ServerInterface;

import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

/**
 * Controller associato a <code>CvCerca</code>.
 *
 * @author Berti Davide -
 * @author Ivanov Aleksandar Evgeniev - 742789 VA
 * @author Mazza Serghej - 740687 VA
 * @author Rizzi Silvio - 719638 VA
 * @see CvCerca
 */
public class CvCntrlCerca {
    @FXML
    private ListView<String> listCentriVaccinali;

    private ArrayList<CentroVaccinale> listaCentriVaccinaliVisualizzati = new ArrayList<>();

    private CvCntrlVaccinato CNTRLV;

    /**
     * Chiude la finestra corrente e carica il nome del centro vaccinale nel modulo per la registrazione di un cittadino
     * vaccinato. Questo metodo &egrave; associato al bottone di conferma dell'operazione.
     *
     * @param event il riferimento all'evento associato
     * @see ActionEvent
     * @see CvCntrlVaccinato
     */
    public void clickOK (ActionEvent event){
        if (listCentriVaccinali.getSelectionModel().getSelectedIndex() == -1 ){
            JOptionPane.showMessageDialog(null, "Selezionare un centro vaccinale.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                // CHIUSURA DELLA VECCHIA FINESTRA
                Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                thisWindow.close();
                CNTRLV.setDati(listaCentriVaccinaliVisualizzati.get(listCentriVaccinali.getSelectionModel().getSelectedIndex()));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDati(CvCntrlVaccinato cntrlV) throws RemoteException {
        compilaLista();
        CNTRLV = cntrlV;
    }

    private void compilaLista() {
        listCentriVaccinali.getItems().clear();
        listaCentriVaccinaliVisualizzati.clear();
        // COMPILAZIONE DELLA LISTA
        try {
            listaCentriVaccinaliVisualizzati = ServerConnection.SERVER.cercaCentroVaccinale("", "", "");
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Connessione al server fallita.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(0);
        }
        for (CentroVaccinale centroVaccinale : listaCentriVaccinaliVisualizzati) {
            listCentriVaccinali.getItems().add(centroVaccinale.getNome() + " - " + centroVaccinale.getIndirizzo());
        }
    }
}
