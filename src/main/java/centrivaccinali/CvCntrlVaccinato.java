package centrivaccinali;

import common.ClientImpl;
import common.ClientInterface;
import common.Vaccinato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.ServerInterface;

import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;

public class CvCntrlVaccinato {
    @FXML
    private DatePicker date;
    @FXML
    private RadioButton btnAstraZeneca, btnJohnsonJohnson, btnModerna, btnPfizer;
    @FXML
    private TextField txtBld, txtFn, txtLn, txtSsn, txtIdn;

    public void backFromCittadinoVaccinato(ActionEvent event) {
        // CHIUSURA DELLA VECCHIA FINESTRA
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();
        // APERTURA DELLA NUOVA FINESTRA
        new CvHomePage();
    }
    public void confirm(ActionEvent event) {
        if(check()) {
            // NOME DEL CENTRO VACCINALE
            String cvName = txtBld.getText().trim().replaceAll("\\s+", " ");
            // NOME DEL VACCINATO
            String vxFn = txtFn.getText().trim().replaceAll("\\s+", " ");
            // COGNOME DEL VACCINATO
            String vxLn = txtLn.getText().trim().replaceAll("\\s+", " ");
            // CODICE FISCALE DEL VACCINATO
            String vxSsn = txtSsn.getText().trim().replaceAll("\\s+", " ");
            // DATA DI SOMMINISTRAZIONE
            LocalDate ddMMyyyy = date.getValue();
            // MARCA DI VACCINO
            String type = null;
            if(btnAstraZeneca.isSelected()) {
                type = btnAstraZeneca.getText();
            } else if(btnJohnsonJohnson.isSelected()) {
                type = btnJohnsonJohnson.getText();
            } else if(btnModerna.isSelected()) {
                type = btnModerna.getText();
            } else if(btnPfizer.isSelected()) {
                type = btnPfizer.getText();
            }
            // NUMERO UNIVOCO DI IDENTIFICAZIONE
            String vxIdn = txtIdn.getText();
            // COSTRUZIONE VACCINATO
            Vaccinato vax = new Vaccinato(cvName, vxFn, vxLn, vxSsn, type, vxIdn, ddMMyyyy);
            // COLLEGAMENTO A SERVER
            try {
                Registry reg = LocateRegistry.getRegistry("*", 1099);
                ServerInterface server = (ServerInterface) reg.lookup("Vaccino");
                ClientInterface client = new ClientImpl();
                server.registraVaccinato(vax, client);
            } catch (RemoteException | NotBoundException e) {
                JOptionPane.showMessageDialog(null, "Connessione al server fallita.", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            // CHIUSURA DELLA VECCHIA FINESTRA
            Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            oldStage.close();
            // APERTURA DELLA NUOVA FINESTRA
            new CvHomePage();
        }
    }
    private boolean check() {
        if(txtBld.getText().isBlank() || txtFn.getText().isBlank() || txtLn.getText().isBlank() || txtSsn.getText().isBlank() || date.getValue() == null || txtIdn.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Tutti i campi devono essere compilati.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}
