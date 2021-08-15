package centrivaccinali;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * <p>
 *     La pagina iniziale dell'applicazione.
 * </p>
 * @author Berti Davide
 * @author Ivanov Aleksandar Evgeniev
 * @author Mazza Serghej
 * @author Rizzi Silvio 719638 VA
 */
public class CvHomePage {

    /**
     * <p>
     *     Il costruttore della classe.
     * </p>
     * <p>
     *     <em>NOTA: i dati necessari alla costruzione della GUI sono recuperati nel file <code>.fxml</code> associato alla classe.</em>
     * </p>
     */
    public CvHomePage() {
        try {
            Stage stage = new Stage();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/CvHome.fxml")));
            stage.setTitle("CENTRI VACCINALI - Home Page");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
