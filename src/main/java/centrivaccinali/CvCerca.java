package centrivaccinali;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * GUI del modulo per la selezione di un centro vaccinale registrato.
 * @author Berti Davide
 * @author Ivanov Aleksandar Evgeniev
 * @author Mazza Serghej
 * @author Rizzi Silvio 719638 VA
 */
public class CvCerca {

    /**
     * Costruttore della classe. Utilizza il file <code>CvCerca.fxml</code> per generare correttamente la GUI.
     */
    public CvCerca(CvCntrlVaccinato cntrlV) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CvCerca.fxml"));
            Parent root = loader.load();
            CvCntrlCerca cc = loader.getController();
            cc.setDati(cntrlV);
            stage.setTitle("CENTRI VACCINALI - Ricerca Centri Vaccinali");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
