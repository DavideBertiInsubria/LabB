package centrivaccinali;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CvCntrlVaccinato implements Initializable {
    @FXML
    private DatePicker dataSomministrazione;
    @FXML
    private RadioButton btnAstraZeneca, btnJohnsonJohnson, btnModerna, btnPfizer;
    @FXML
    private TextField txtCentroVaccinale, txtNomeVaccinato, txtCognomeVaccinato, txtCfVaccinato, txtIdVaccinato;



    public void backFromVaccinato(ActionEvent event) {
        // CHIUSURA DELLA VECCHIA FINESTRA
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();
        // APERTURA DELLA NUOVA FINESTRA
        new CvHomePage();
    }
    public void registraVaccinato(ActionEvent event) {
        if(controllaCampi()) {
            String campo1 = txtCentroVaccinale.getText();
            String campo2 = txtNomeVaccinato.getText();
            String campo3 = txtCognomeVaccinato.getText();
            String campo4 = txtCfVaccinato.getText();
            LocalDate date = dataSomministrazione.getValue();
            String myDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String campo5 = null;
            if(btnAstraZeneca.isSelected()) {
                campo5 = btnAstraZeneca.getText();
            } else if(btnJohnsonJohnson.isSelected()) {
                campo5 = btnJohnsonJohnson.getText();
            } else if(btnModerna.isSelected()) {
                campo5 = btnModerna.getText();
            } else if(btnPfizer.isSelected()) {
                campo5 = btnPfizer.getText();
            }
            String campo6 = txtIdVaccinato.getText();

            String vacc = campo1+campo2+campo3+campo4+date.toString()+campo5+campo6;
            System.out.println(vacc);
        }

    }
    private boolean controllaCampi() {
        if(txtCentroVaccinale.getText().equals("")||txtNomeVaccinato.getText().equals("")||txtCognomeVaccinato.getText().equals("")||txtCfVaccinato.getText().equals("")||txtIdVaccinato.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Compilare tutti i campi.");
            return false;
        }
        return true;
    }
    public void getDate(ActionEvent e) {
        LocalDate myDate = dataSomministrazione.getValue();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        dataSomministrazione.setValue(LocalDate.parse(formatter.format(LocalDate.now())));
        //dataSomministrazione.setValue(LocalDate.parse());
    }
}
