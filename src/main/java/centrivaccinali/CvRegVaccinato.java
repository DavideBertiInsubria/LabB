package centrivaccinali;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * GUI del modulo per la registrazione di un cittadino vaccinato.
 * @author Berti Davide
 * @author Ivanov Aleksandar Evgeniev
 * @author Mazza Serghej
 * @author Rizzi Silvio 719638 VA
 */
public class CvRegVaccinato {

    /**
     * Costruttore della classe. Utilizza il file <code>CvRegVaccinato.fxml</code> per generare correttamente la GUI.
     */
    public CvRegVaccinato() {
        try {
            Stage stage = new Stage();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/CvRegVaccinato.fxml")));
            stage.setTitle("CENTRI VACCINALI - Registrazione Cittadini Vaccinati");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
