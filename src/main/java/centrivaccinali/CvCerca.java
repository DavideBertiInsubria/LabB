package centrivaccinali;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CvCerca {
        public CvCerca() {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/CvCerca.fxml")));
                stage.setTitle("CENTRI VACCINALI - Ricerca Centri Vaccinali");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
