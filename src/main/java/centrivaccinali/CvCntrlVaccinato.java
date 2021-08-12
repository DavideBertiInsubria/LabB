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

public class CvCntrlVaccinato {
    @FXML
    private DatePicker date;
    @FXML
    private RadioButton btnAstraZeneca, btnJohnsonJohnson, btnModerna, btnPfizer;
    @FXML
    private TextField txtCentreName, txtFirstName, txtLastName, txtFiscalCode, txtId;

    /**
     * <code>CV</code> &egrave; il riferimento al centro vaccinale selezionato sul quale verrà registrato il vaccinato, ed è un oggetto di tipo <i>CentroVaccinale</i>.
     * &egrave; null se non si è selezionato nulla
     * @see CentroVaccinale
     */
    private CentroVaccinale CV = null;

    public void search(ActionEvent event) {
        // CHIUSURA DELLA VECCHIA FINESTRA
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();
        // APERTURA DELLA NUOVA FINESTRA
        new CvCerca();
    }

    public void backToHomePage(ActionEvent event) {
        // CHIUSURA DELLA VECCHIA FINESTRA
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();
        // APERTURA DELLA NUOVA FINESTRA
        new CvHomePage();
    }

    public void confirm(ActionEvent event) {
        if(check()) {
            // ESTRAZIONE DI DATI DA TEXTFIELD
            TextField[] txt = { txtFirstName, txtLastName, txtFiscalCode, txtId };
            String[] buf = CvUtil.toStringArray(txt);
            // ESTRAZIONE DI DATI DA RADIOBUTTON
            RadioButton[] vaccine = { btnAstraZeneca, btnJohnsonJohnson, btnModerna, btnPfizer };
            String vaccineType = CvUtil.selectRadioButton(vaccine);
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

    private boolean check() {
        if(txtCentreName.getText().isBlank() || txtFirstName.getText().isBlank() || txtLastName.getText().isBlank() || txtFiscalCode.getText().isBlank() || date.getValue() == null || txtId.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Tutti i campi devono essere compilati.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void setDati(CentroVaccinale cv) throws RemoteException {
        CV = cv;
        txtCentreName.setText(CV.getNome());
    }

}
