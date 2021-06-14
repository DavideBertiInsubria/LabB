package cittadini;

import common.Cittadino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerRegistrazione {
    @FXML
    Button btnProva;

    public void clickProva(ActionEvent event)  {
        try {
            // CHIUSURA
            Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            thisWindow.close();

            // APERTURA NUOVA SCHERMATA
            Stage schermataHome = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeCittadini.fxml"));
            Parent root = loader.load();

                // ...CREAZIONE CITTADINO DI PROVA e SET
            Cittadino cittadinoProva = new Cittadino();
            cittadinoProva.setNome("Paolo");
            cittadinoProva.setCognome("Rossi");
            ControllerHome cc = loader.getController();
            cc.setUser(cittadinoProva);

            schermataHome.setTitle("Vaccinazione cittadini");
            schermataHome.setScene(new Scene(root));
            schermataHome.show();
        } catch (IOException ignored){}
    }
}
