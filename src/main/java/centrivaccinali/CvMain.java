package centrivaccinali;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * <p>
 *     Il punto di ingresso dell'applicazione destinata all'uso da parte di centri vaccinali e cittadini vaccinati.
 * </p>
 * @author Berti Davide
 * @author Ivanov Aleksandar Evgeniev
 * @author Mazza Serghej
 * @author Rizzi Silvio 719638 VA
 */
public class CvMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * <p>
     *     Avvia l'applicazione aprendo una nuova finestra in cui &egrave visualizzata la pagina iniziale.
     * </p>
     * <p>
     *     <em>NOTA: questo metodo sovrascrive il metodo astratto <code>start</code> della classe <code>Application</code>.</em>
     * </p>
     * @see Application
     */
    @Override
    public void start(Stage primaryStage) {
        new CvHomePage();
    }
}
