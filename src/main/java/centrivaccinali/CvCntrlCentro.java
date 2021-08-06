package centrivaccinali;

import common.CentroVaccinale;
import common.TipologiaCentro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.ServerInterface;

import javax.swing.*;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

public class CvCntrlCentro implements Initializable {
    @FXML
    private TextField txtName, txtAddrName, txtAddrNumb, txtAddrCity, txtAddrProv, txtAddrZipc;
    @FXML
    private RadioButton btnVia, btnViale, btnPiazza, btnAziendale, btnHub, btnOspedaliero;
    @FXML
    private ComboBox<String> boxAddressProvince;

    private ObservableList<String> province = FXCollections.observableArrayList(
            "AG","AL","AN","AO","AP","AQ","AR","AT","AV","BA","BG","BI","BL","BN","BO","BR","BS","BT","BZ","CA",
            "CB","CE","CH","CI","CL","CN","CO","CR","CS","CT","CZ","EN","FC","FE","FG","FI","FM","FR","KR","GE",
            "GO","GR","IM","IS","LC","LE","LI","LO","LT","LU","MB","MC","ME","MI","MN","MO","MS","MT","NA","NO",
            "NU","OG","OR","OT","PA","PC","PD","PE","PG","PI","PN","PO","PR","PT","PU","PV","PZ","RA","RG","RC",
            "RE","RI","RM","RN","RO","SA","SI","SO","SP","SR","SS","SV","TA","TE","TN","TO","TP","TR","TS","TV",
            "UD","VA","VB","VC","VE","VI","VR","VS","VT","VV");

    public void backFromCentroVaccinale(ActionEvent event) {
        // CHIUSURA DELLA VECCHIA FINESTRA
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();
        // APERTURA DELLA NUOVA FINESTRA
        new CvHomePage();
    }
    public void confirm(ActionEvent event) {
        // CONTROLLO DELLA CORRETTEZZA DI COMPILAZIONE
        if(check()) {
            // NOME
            String name = CvUtil.snip(txtName.getText());
            // INDIRIZZO
            RadioButton[] street = { btnPiazza, btnVia, btnViale };
            String addressPartOne = "";
            for(RadioButton rb: street) {
                if(rb.isSelected())
                    addressPartOne = rb.getText() + " ";
            }
            TextField[] centreAddress = { txtAddrName, txtAddrNumb, txtAddrCity, txtAddrProv, txtAddrZipc };
            String addressPartTwo = "";
            for(TextField tf: centreAddress) {
                addressPartTwo += CvUtil.snip(tf.getText()) + " ";
            }
            String address = addressPartOne + addressPartTwo;
            // CENTRO VACCINALE : TIPOLOGIA
            TipologiaCentro type = null;
            if(btnAziendale.isSelected()) {
                type = TipologiaCentro.aziendale;
            } else if(btnHub.isSelected()) {
                type = TipologiaCentro.hub;
            } else if(btnOspedaliero.isSelected()) {
                type = TipologiaCentro.ospedaliero;
            }

            String s = boxAddressProvince.getValue();
            String finale = addressPartOne+" "+centreAddress[0].getText()+centreAddress[1].getText()+centreAddress[2].getText()+s+centreAddress[4].getText();



            // COSTRUZIONE CENTRO VACCINALE
            CentroVaccinale cv = new CentroVaccinale(name, address, type.toString());
            // COLLEGAMENTO A SERVER
            try {
                Registry reg = LocateRegistry.getRegistry("localhost", 1099);
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



            //System.out.println(name + address + type.toString());
            //System.out.println(finale);

        }
    }
    private boolean check() {
        if(txtName.getText().isBlank() || txtAddrName.getText().isBlank() || txtAddrNumb.getText().isBlank() || txtAddrCity.getText().isBlank() || txtAddrProv.getText().isBlank() || txtAddrZipc.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Tutti i campi devono essere compilati.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(txtAddrProv.getText().length() != 2 || CvUtil.isNumerical((txtAddrProv.getText()))) {
            JOptionPane.showMessageDialog(null, "La sigla di una provincia e' composta da due caratteri alfabetici.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(txtAddrZipc.getText().length() != 5 || !CvUtil.isNumerical(txtAddrZipc.getText())) {
            JOptionPane.showMessageDialog(null, "Il codice di avviamento postale e' composto da cinque caratteri numerici.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(boxAddressProvince.getValue() == null || boxAddressProvince.getValue().isBlank()){
            JOptionPane.showMessageDialog(null, "errore con provincia");
            return false;
        }

        return true;
    }

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

    private String isType(RadioButton[] arr) {
        for(RadioButton rb: arr) {
            if(rb.isSelected()){
                return rb.getText() + " ";
            }
        }
        return arr[0].getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boxAddressProvince.setItems(province);
        boxAddressProvince.setVisibleRowCount(10);
        boxAddressProvince.setEditable(false);
    }

}
