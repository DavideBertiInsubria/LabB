package centrivaccinali;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Il punto di ingresso dell'applicazione destinata all'uso da parte di centri vaccinali e cittadini vaccinati.
 *
 * @author Berti Davide
 * @author Ivanov Aleksandar Evgeniev
 * @author Mazza Serghej
 * @author Rizzi Silvio 719638 VA
 * @see Application
 */
public class CvMain extends Application {

    /**
     * Il metodo <code>main</code>.
     * @param args non richiesto
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new CvHomePage();
    }
}
