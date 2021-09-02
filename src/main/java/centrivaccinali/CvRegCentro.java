package centrivaccinali;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * GUI del modulo per la registrazione di un centro vaccinale.
 *
 * @author Berti Davide -
 * @author Ivanov Aleksandar Evgeniev - 742789 VA
 * @author Mazza Serghej - 740687 VA
 * @author Rizzi Silvio - 719638 VA
 */
public class CvRegCentro {

    /**
     * Il costruttore della classe.
     */
    public CvRegCentro() {
        try {
            Stage stage = new Stage();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/CvRegCentro.fxml")));
            stage.setTitle("CENTRI VACCINALI - Registrazione Centri Vaccinali");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
