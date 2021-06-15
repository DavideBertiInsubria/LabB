package centrivaccinali;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerHome {
    public void clickRegistraCentro(ActionEvent event) {
        try {
            // CHIUSURA DELLA VECCHIA FINESTRA
            Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            oldStage.close();
            // APERTURA DELLA NUOVA FINESTRA
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/FormCentriVaccinali.fxml"));
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void clickRegistraVaccinato(ActionEvent event) {
        try {
            Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            oldStage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/FormCittadiniVaccinati.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
