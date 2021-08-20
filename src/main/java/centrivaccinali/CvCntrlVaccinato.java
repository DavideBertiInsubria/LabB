package centrivaccinali;

import common.CentroVaccinale;
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

/**
 * Controller associato a <code>CvRegVaccinato</code>.
 * @author Berti Davide
 * @author Ivanov Aleksandar Evgeniev
 * @author Mazza Serghej
 * @author Rizzi Silvio 719638 VA
 * @see CvRegVaccinato
 */
public class CvCntrlVaccinato {
    @FXML
    private DatePicker date;
    @FXML
    private RadioButton btnAstraZeneca, btnJohnsonJohnson, btnModerna, btnPfizer;
    @FXML
    private TextField txtCentreName, txtFirstName, txtLastName, txtFiscalCode, txtId;

    private CentroVaccinale CV = null;

    /**
     * Chiude la finestra corrente e crea una nuova istanza di <code>CvCerca</code>. &Egrave; associato al bottone
     * per la selezione di un centro vaccinale registrato.
     * @param event il riferimento all'evento associato
     * @see CvCerca
     * @see ActionEvent
     */
    public void search(ActionEvent event) {
        // APERTURA DELLA NUOVA FINESTRA
        new CvCerca(this);
    }

    /**
     * Chiude la finestra corrente e crea una nuova istanza di <code>CvHomePage</code>. &Egrave; associato al bottone
     * per il ritorno alla pagina iniziale.
     * @param event il riferimento all'evento associato
     * @see CvHomePage
     * @see ActionEvent
     */
    public void backToHomePage(ActionEvent event) {
        // CHIUSURA DELLA VECCHIA FINESTRA
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();
        // APERTURA DELLA NUOVA FINESTRA
        new CvHomePage();
    }

    /**
     * Chiude la finestra corrente inviando i dati inseriti a un server opportunamente collegato e crea una nuova
     * istanza di <code>CvHomePage</code>. &Egrave; associato al bottone di conferma.
     * @param event il riferimento all'evento associato
     * @see CvHomePage
     * @see ActionEvent
     */
    public void confirm(ActionEvent event) {
        if(check()) {
            // ESTRAZIONE DI DATI DA TEXTFIELD
            TextField[] txt = { txtFirstName, txtLastName, txtFiscalCode, txtId };
            String[] buf = CvUtil.toStringArray(txt);
            // ESTRAZIONE DI DATI DA RADIOBUTTON
            RadioButton[] vaccine = { btnAstraZeneca, btnJohnsonJohnson, btnModerna, btnPfizer };
            String vaccineType = CvUtil.selectRadioButton(vaccine);
            if (vaccineType.contains("Johnson"))
                vaccineType = "JJ";
            // ESTRAZIONE DI DATI DA DATEPICKER
            LocalDate ddMMyyyy = date.getValue();
            // COSTRUZIONE VACCINATO
            Vaccinato vax = new Vaccinato(CV, buf[0], buf[1], buf[2], vaccineType, Integer.parseInt(buf[3]), ddMMyyyy);
            // COLLEGAMENTO A SERVER
            try {
                Registry reg = LocateRegistry.getRegistry("localhost", 1099);
                ServerInterface server = (ServerInterface) reg.lookup("Vaccino");
                server.registraVaccinato(vax);
            } catch (RemoteException | NotBoundException e) {
                JOptionPane.showMessageDialog(null, "Connessione al server fallita.", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            // CHIUSURA DELLA VECCHIA FINESTRA
            Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            oldStage.close();
            // APERTURA DELLA NUOVA FINESTRA
            new CvHomePage();

            // System.out.println(txtCentreName.getText()+" "+buf[0]+" "+buf[1]+" "+buf[2]+" "+vaccineType+" "+Integer.parseInt(buf[3])+" "+ddMMyyyy);

        }
    }

    /**
     * Controlla che i dati richiesti dal modulo di registrazione siano stati inseriti correttamente. L'applicazione
     * richiede che tutti i campi siano obbligatoriamente compilati.
     * @return <code>true</code> se tutti i campi sono stati compilati correttamente, <code>false</code> altrimenti.
     */
    private boolean check() {
        if(txtCentreName.getText().isBlank() || txtFirstName.getText().isBlank() || txtLastName.getText().isBlank() || txtFiscalCode.getText().isBlank() || date.getValue() == null || txtId.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Tutti i campi devono essere compilati.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Visualizza il nome del centro vaccinale selezionato nel campo corrispondente del modulo di registrazione.
     * @param cv il riferimento al centro vaccinale selezionato
     * @throws RemoteException
     * @see CentroVaccinale
     */
    public void setDati(CentroVaccinale cv) throws RemoteException {
        CV = cv;
        txtCentreName.setText(CV.getNome());
    }

}
