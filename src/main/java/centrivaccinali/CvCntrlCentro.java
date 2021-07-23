package centrivaccinali;

import common.CentroVaccinale;
import common.ClientImpl;
import common.ClientInterface;
import common.TipologiaCentro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.ServerInterface;

import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CvCntrlCentro {
    @FXML
    private TextField txtName, txtAddrName, txtAddrNumb, txtAddrCity, txtAddrProv, txtAddrZipc;
    @FXML
    private RadioButton btnVia, btnViale, btnPiazza, btnAziendale, btnHub, btnOspedaliero;

    public void backFromCentroVaccinale(ActionEvent event) {
        // CHIUSURA DELLA VECCHIA FINESTRA
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();
        // APERTURA DELLA NUOVA FINESTRA
        new CvHomePage();
    }
    public void confirm(ActionEvent event) {
        if(check()) {
            // NOME del centro vaccinale
            String name = txtName.getText().trim().replaceAll("\\s+", " ");
            // INDIRIZZO del centro vaccinale
            // CAMPO N.1: QUALIFICATORE
            String field1 = null;
            if(btnVia.isSelected()) {
                field1 = btnVia.getText();
            } else if(btnViale.isSelected()) {
                field1 = btnViale.getText();
            } else if (btnPiazza.isSelected()) {
                field1 = btnPiazza.getText();
            }
            // CAMPO N.2: NOME
            String field2 = txtAddrName.getText().trim().replaceAll("\\s+", " ");
            // CAMPO N.3: NUMERO CIVICO
            String field3 = txtAddrNumb.getText().trim().replaceAll("\\s+", " ");
            // CAMPO N.4: COMUNE
            String field4 = txtAddrCity.getText().trim().replaceAll("\\s+", " ");
            // CAMPO N.5: PROVINCIA
            String field5 = txtAddrProv.getText().trim().replaceAll("\\s+", " ");
            // CAMPO N.6: CODICE DI AVVIAMENTO POSTALE
            String field6 = txtAddrZipc.getText().trim().replaceAll("\\s+", " ");
            // TIPOLOGIA del centro vaccinale
            TipologiaCentro type = null;
            if(btnAziendale.isSelected()) {
                type = TipologiaCentro.aziendale;
            } else if(btnHub.isSelected()) {
                type = TipologiaCentro.hub;
            } else if(btnOspedaliero.isSelected()) {
                type = TipologiaCentro.ospedaliero;
            }
            // COSTRUZIONE INDIRIZZO
            String addr = field1 + " " + field2 + " " + field3 + ", " + field4 + " (" + field5 + "), " + field6;
            // COSTRUZIONE CENTRO VACCINALE
            CentroVaccinale cv = new CentroVaccinale(name, addr, type.toString());
            // COLLEGAMENTO A SERVER
            try {
                Registry reg = LocateRegistry.getRegistry("*", 1099);
                ServerInterface server = (ServerInterface) reg.lookup("Vaccino");
            //    ClientInterface client = new ClientImpl();
                server.registraCentroVaccinale(cv);
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
        if(txtName.getText().isBlank() || txtAddrName.getText().isBlank() || txtAddrNumb.getText().isBlank() || txtAddrCity.getText().isBlank() || txtAddrProv.getText().isBlank() || txtAddrZipc.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Tutti i campi devono essere compilati.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(txtAddrProv.getText().length() != 2) {
            JOptionPane.showMessageDialog(null, "La sigla di una provincia contiene un numero massimo di due caratteri.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    /*
    private static boolean isAlphabetical(String s) {
        String[] arr = s.split(" ");
        boolean alphabetical;
        for (String value : arr) {
            alphabetical = value.chars().allMatch(Character::isLetter);
            if (!alphabetical) {
                return false;
            }
        }
        return true;
    }
    */

    /*
    private static boolean isNumerical(String s) {
        try {
            int i = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    */

}
