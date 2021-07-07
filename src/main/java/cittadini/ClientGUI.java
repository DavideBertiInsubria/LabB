package cittadini;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientGUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // AVVIO APPLICAZIONE CITTADINI
        Parent root = FXMLLoader.load(getClass().getResource("/HomeCittadini.fxml"));
        primaryStage.setTitle("Vaccinazione cittadini");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}
