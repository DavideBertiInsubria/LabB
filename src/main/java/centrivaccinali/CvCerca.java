package centrivaccinali;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CvCerca {
        public CvCerca() {
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CvCerca.fxml"));
                Parent root = loader.load();
                CvCntrlCerca cc = loader.getController();
                cc.setDati();
                stage.setTitle("CENTRI VACCINALI - Ricerca Centri Vaccinali");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
