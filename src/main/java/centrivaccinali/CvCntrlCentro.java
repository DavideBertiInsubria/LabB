package centrivaccinali;

import common.CentroVaccinale;
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

/**
 * <code>CvCntrlCentro</code> implementa il controller associato all'interfaccia grafica <code>CvRegCentro</code>
 * appartenente all'applicazione destinata all'utilizzo da parte di centri vaccinali e cittadini vaccinati.
 * @author Berti Davide
 * @author Ivanov Aleksandar Evgeniev
 * @author Mazza Serghej
 * @author Rizzi Silvio 719638 VA
 * @version 1.0
 * @see CvRegCentro
 * @since 1.0
 */
public class CvCntrlCentro implements Initializable {
    @FXML
    private TextField txtName, txtAddrName, txtAddrNumb, txtAddrZipc, txtAddrCity;
    @FXML
    private RadioButton btnVia, btnViale, btnPiazza, btnAziendale, btnHub, btnOspedaliero;
    @FXML
    private ComboBox<String> boxAddressProvince;

    private final ObservableList<String> province = FXCollections.observableArrayList(
            "AG","AL","AN","AO","AP","AQ","AR","AT","AV","BA","BG","BI","BL","BN","BO","BR","BS","BT","BZ","CA",
            "CB","CE","CH","CI","CL","CN","CO","CR","CS","CT","CZ","EN","FC","FE","FG","FI","FM","FR","KR","GE",
            "GO","GR","IM","IS","LC","LE","LI","LO","LT","LU","MB","MC","ME","MI","MN","MO","MS","MT","NA","NO",
            "NU","OG","OR","OT","PA","PC","PD","PE","PG","PI","PN","PO","PR","PT","PU","PV","PZ","RA","RG","RC",
            "RE","RI","RM","RN","RO","SA","SI","SO","SP","SR","SS","SV","TA","TE","TN","TO","TP","TR","TS","TV",
            "UD","VA","VB","VC","VE","VI","VR","VS","VT","VV");

    /**
     * Determina la chiusura della finestra corrente e l'apertura di una finestra nuova, in cui &egrave visualizzata
     * l'interfaccia grafica <code>CvHomePage</code>, nel momento in cui si interagisce con il componente al quale
     * &egrave associato questo metodo.
     * @param event l'evento associato che determina l'invocazione del metodo
     * @see ActionEvent
     * @see CvHomePage
     * @since 1.0
     */
    public void backToHomePage(ActionEvent event) {
        // CHIUSURA DELLA VECCHIA FINESTRA
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();
        // APERTURA DELLA NUOVA FINESTRA
        new CvHomePage();
    }

    /**
     * <p>
     *     Chiude la finestra corrente inviando i dati inseriti nel modulo a un server collegato e apre una nuova finestra
     *     in cui &egrave caricata la pagina iniziale.
     * </p>
     * <p>
     *     L'invio di dati &egrave vincolato alla corretta compilazione del modulo presente nella pagina; in caso contrario,
     *     saranno visualizzati opportuni messaggi di avvertimento in cui &egrave descritta la tipologia di errore avvenuto.
     * </p>
     * <p>
     *     Nel caso in cui l'applicazione non riuscisse a connettersi al server desiderato in modo corretto, sar&agrave
     *     visualizzato un opportuno messaggio di errore.
     * </p>
     *
     * @param event l'evento associato che determina l'invocazione del metodo
     */
    public void confirm(ActionEvent event) {
        // CONTROLLO DELLA COMPILAZIONE
        if(check()) {
            // ESTRAZIONE DI DATI DA TEXTFIELD
            TextField[] txt = { txtName, txtAddrName, txtAddrNumb, txtAddrZipc, txtAddrCity };
            String[] buf = CvUtil.toStringArray(txt);
            // ESTRAZIONE DI DATI DA RADIOBUTTON
            RadioButton[] street = { btnVia, btnViale, btnPiazza };
            String streetType = CvUtil.selectRadioButton(street);
            RadioButton[] centre = { btnAziendale, btnHub, btnOspedaliero };
            String centreType = CvUtil.selectRadioButton(centre);
            // ESTRAZIONE DI DATI DA COMBOBOX
            String prov = boxAddressProvince.getValue();
            // COSTRUZIONE INDIRIZZO
            String addr = streetType + " " + buf[1] + " " + buf[2] + " " + buf[3] + " " + buf[4] + " " + prov;
            // COSTRUZIONE CENTRO VACCINALE
            CentroVaccinale cv = new CentroVaccinale(buf[0], addr, centreType);
            // COLLEGAMENTO A SERVER
            try {
                Registry reg = LocateRegistry.getRegistry("localhost", 1099);
                ServerInterface server = (ServerInterface) reg.lookup("Vaccino");
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

            //System.out.println(buf[0] + " " + addr + " " + centreType);

        }
    }

    /**
     * Controlla se i campi sono stati compilati in ottemperanza ai seguenti vincoli imposti dall'applicazione:
     * <ul>
     *     <li>
     *         tutti i campi devono essere obbligatoriamente compilati a eccezione dei pulsanti associati alla scelta
     *         del qualificatore (<strong>via</strong>, viale, piazza) dell'indirizzo e alla scelta della tipologia
     *         (<strong>aziendale</strong>, hub, ospedaliero) di centro vaccinale i cui valori di default sono indicati
     *         in <strong>grassetto</strong>.
     *     </li>
     *     <li>
     *         il codice di avviamento postale deve essere obbligatoriamente composto da cinque caratteri numerici
     *     </li>
     * </ul>
     * @return <code>true</code> se tutti i campi sono stati correttamente compilati, altrimenti <code>false</code>
     */
    private boolean check() {
        // MESSAGGI DI ERRORE
        String errOnLvl1 = "Tutti i campi devono essere obbligatoriamente compilati.";
        String errOnLvl2 = "Il codice di avviamento postale e' composto da cinque caratteri numerici.";
        String errOnLvl3 = "La provincia deve essere obbligatoriamente selezionata.";
        // CONTROLLO DELLA CORRETTEZZA DI COMPILAZIONE PER TEXTFIELD
        if(txtName.getText().isBlank() || txtAddrName.getText().isBlank() || txtAddrNumb.getText().isBlank() || txtAddrZipc.getText().isBlank() || txtAddrCity.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, errOnLvl1, "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(txtAddrZipc.getText().length() != 5 || !CvUtil.isNumerical(txtAddrZipc.getText())) {
            JOptionPane.showMessageDialog(null, errOnLvl2, "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // CONTROLLO DELLA CORRETTEZZA DI COMPILAZIONE PER COMBOBOX
        if(boxAddressProvince.getValue() == null || boxAddressProvince.getValue().isBlank()){
            JOptionPane.showMessageDialog(null, errOnLvl3, "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Inizializza la <code>ComboBox</code> con l'elenco delle sigle delle provincie vigenti.
     * @param url the location used to resolve relative paths for the root object, or <code>null</code> if the location
     *            is not known (dalla documentazione per JavaFX 16)
     * @param resourceBundle the resources used to localize the root object, or <code>null</code> if the root object was
     *                       not localized (dalla documentazione per JavaFX 16)
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // INIZIALIZZAZIONE DELLA COMBOBOX
        boxAddressProvince.setItems(province);
        boxAddressProvince.setVisibleRowCount(10);
        boxAddressProvince.setEditable(false);
        boxAddressProvince.setPromptText("Provincia");
    }

}