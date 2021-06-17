package centrivaccinali;

import javafx.application.Application;
import javafx.stage.Stage;

public class CvMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new CvHomePage();
    }
}
