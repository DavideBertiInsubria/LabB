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

public class CvCntrlCerca {

    private ArrayList<CentroVaccinale> listaCentriVaccinaliVisualizzati = new ArrayList<CentroVaccinale>();
    @FXML
    ListView<String> listCentriVaccinali;

    public void setDati() throws RemoteException {
        compilaLista();
    }

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

    public void clickOK (ActionEvent event){
        if (listCentriVaccinali.getSelectionModel().getSelectedIndex() == -1 ){
            JOptionPane.showMessageDialog(null, "Selezionare un centro vaccinale dalla lista.");
        } else {
            try {
                // CHIUSURA
                Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                thisWindow.close();

                // APERTURA NUOVA SCHERMATA
                Stage schermata = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CvRegVaccinato.fxml"));
                Parent root = loader.load();
                CvCntrlVaccinato cc = loader.getController();
                cc.setDati(listaCentriVaccinaliVisualizzati.get(listCentriVaccinali.getSelectionModel().getSelectedIndex()));  // PASSO IL CENTRO VACCINALE SELEZIONATO
                schermata.setTitle("CENTRI VACCINALI - Registrazione Cittadini Vaccinati");
                schermata.setScene(new Scene(root));
                schermata.show();
            } catch (IOException e){
                JOptionPane.showMessageDialog(null, "Errore di tipo \"LOAD\".");
                e.printStackTrace();
            }
        }
    }
}
