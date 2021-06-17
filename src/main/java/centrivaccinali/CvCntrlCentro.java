package centrivaccinali;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CvCntrlCentro {
    @FXML
    private TextField txtNome, txtIndirizzoNome, txtIndirizzoNumero, txtIndirizzoComune, txtIndirizzoProvincia, txtIndirizzoCap;
    @FXML
    private RadioButton btnVia, btnViale, btnPiazza, btnAziendale, btnHub, btnOspedaliero;

    public void back(ActionEvent event) {
            // CHIUSURA DELLA VECCHIA FINESTRA
            Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            oldStage.close();
            // APERTURA DELLA NUOVA FINESTRA
            new CvHomePage();
    }
    public void registraCentroVaccinale(ActionEvent event) {
        String pattern = "(?<=\\s|^)[a-zA-Z]*(?=[.,;:]?\\s|$)";
        String str = txtNome.getText().trim().replaceAll("\\s+", " ");
    }
}
