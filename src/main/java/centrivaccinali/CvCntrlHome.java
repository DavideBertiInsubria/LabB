package centrivaccinali;

import cittadini.ControllerConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller associato a <code>CvHomePage</code>.
 *
 * @author Berti Davide - 740665 VA
 * @author Ivanov Aleksandar Evgeniev - 742789 VA
 * @author Mazza Serghej - 740687 VA
 * @author Rizzi Silvio - 719638 VA
 * @see CvHomePage
 */
public class CvCntrlHome {

    /**
     * Chiude la finestra corrente e crea una nuova istanza di <code>CvRegCentro</code>. Questo metodo &egrave; associato
     * al bottone per la registrazione di un centro vaccinale.
     *
     * @param event il riferimento all'evento associato
     * @see ActionEvent
     * @see CvRegCentro
     */
    public void clickRegistraCentro(ActionEvent event) {
        // CHIUSURA DELLA VECCHIA FINESTRA
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        // APERTURA DELLA NUOVA FINESTRA
        new CvRegCentro();
    }

    /**
     * Chiude la finestra corrente e crea una nuova istanza di <code>CvRegVaccinato</code>. Questo metodo &egrave; associato
     * al bottone per la registrazione di un cittadino vaccinato.
     *
     * @param event il riferimento all'evento associato
     * @see ActionEvent
     * @see CvRegVaccinato
     */
    public void clickRegistraVaccinato(ActionEvent event) {
        // CHIUSURA DELLA VECCHIA FINESTRA
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        // APERTURA DELLA NUOVA FINESTRA
        new CvRegVaccinato();
    }

    /**
     * Chiude la finestra corrente e crea una nuova finestra di gestione del server. Questo metodo &egrave; associato
     * al bottone per la configurazione e connessione al server.
     *
     * @param event il riferimento all'evento associato
     * @see CvCntrlConnection
     * @see ActionEvent
     */
    public void clickConnection(ActionEvent event) throws IOException {
        // AVVIO APPLICAZIONE CITTADINI
        Stage schermata = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CvConnection.fxml"));
        Parent root = loader.load();
        // ... SET DATI
        CvCntrlConnection cc = loader.getController();
        schermata.setTitle("Connection");
        schermata.setScene(new Scene(root));
        schermata.show();
    }
}
