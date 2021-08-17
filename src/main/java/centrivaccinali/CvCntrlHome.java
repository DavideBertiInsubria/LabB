package centrivaccinali;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * Controller associato a <code>CvHomePage</code>.
 * @author Berti Davide
 * @author Ivanov Aleksandar Evgeniev
 * @author Mazza Serghej
 * @author Rizzi Silvio 719638 VA
 * @see CvHomePage
 */
public class CvCntrlHome {

    /**
     * Chiude la finestra corrente e crea una nuova istanza di <code>CvRegCentro</code>. &Egrave; associato al bottone
     * per la registrazione di un centro vaccinale.
     * @param event il riferimento all'evento associato
     * @see CvRegCentro
     * @see ActionEvent
     */
    public void clickRegistraCentro(ActionEvent event) {
        // CHIUSURA DELLA VECCHIA FINESTRA
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        // APERTURA DELLA NUOVA FINESTRA
        new CvRegCentro();
    }

    /**
     * Chiude la finestra corrente e crea una nuova istanza di <code>CvRegVaccinato</code>. &Egrave; associato al bottone
     * per la registrazione di un cittadino vaccinato.
     * @param event il riferimento all'evento associato
     * @see CvRegVaccinato
     * @see ActionEvent
     */
    public void clickRegistraVaccinato(ActionEvent event) {
        // CHIUSURA DELLA VECCHIA FINESTRA
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        // APERTURA DELLA NUOVA FINESTRA
        new CvRegVaccinato();
    }
}
