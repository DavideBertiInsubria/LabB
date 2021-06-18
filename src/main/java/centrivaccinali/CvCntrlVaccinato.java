package centrivaccinali;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CvCntrlVaccinato {
    @FXML
    private DatePicker selectDate;
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
        /* */
    }
}
