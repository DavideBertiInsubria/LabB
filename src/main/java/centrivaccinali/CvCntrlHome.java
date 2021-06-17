package centrivaccinali;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class CvCntrlHome {
    public void clickRegistraCentro(ActionEvent event) {
        // CHIUSURA DELLA VECCHIA FINESTRA
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        // APERTURA DELLA NUOVA FINESTRA
        new CvRegCentro();
    }
    public void clickRegistraVaccinato(ActionEvent event) {
        // CHIUSURA DELLA VECCHIA FINESTRA
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        // APERTURA DELLA NUOVA FINESTRA
        new CvRegVaccinato();
    }
}
