package cittadini;

import common.CentroVaccinale;
import common.Cittadino;
import common.ReportEventoAvverso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import server.ServerInterface;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/** @author Invanov Aleksandar Evgeniev, Mazza Serghej, Berti Davide, Rizzi Silvio
 * La classe <em>ControllerVisualizza</em> rappresenta il <i>controller</i> dell'interfaccia grafica della schermata <i>'Visualizza'</i> dell'applicazione 'cittadini', sviluppato con JavaFX.
 */
public class ControllerVisualizza {

    @FXML
    Label lbNome, lbIndirizzo, lbTipologia, lbT1, lbT2, lbT3, lbT4, lbT5, lbT6, lbM1, lbM2, lbM3, lbM4, lbM5, lbM6, lbN1, lbN2, lbN3, lbN4, lbN5, lbN6;

    /**
     * <code>CV</code> &egrave; il riferimento al centro vaccinale selezionato per la visualizzazione dei dati, ed è un oggetto di tipo <i>CentroVaccinale</i>.
     * @see CentroVaccinale
     */
    private CentroVaccinale CV;

    /**
     * <code>User</code> &egrave; il riferimento all'utente loggato, ed è un oggetto di tipo <i>Cittadino</i>.
     * Se l'utente non è loggato allora <i>User</i> &egrave; uguale a <b>null</b>.
     * @see Cittadino
     */
    private Cittadino User;

    /**
     * <code>server</code> &egrave; il riferimento al server.
     * @see ServerInterface
     */
    private ServerInterface server;

    private ArrayList<ReportEventoAvverso> ProspettoEventiAvversi = new ArrayList<>();

    /**
     * Il metodo <em>setDati</em> serve per fornire alla schermata <i>'Visualizza'</i> tutti i dati e le informazioni occorrenti dalle altre schermate di interfaccia grafica.
     * Come l'utente loggato, il collegamento al server e il centro vaccinale selezionato da visualizzare.
     */
    public void setDati(CentroVaccinale cv, Cittadino user, ServerInterface s) throws RemoteException {
        server = s;
        CV = cv;
        User = user;
        lbNome.setText(CV.getNome());
        lbIndirizzo.setText(CV.getIndirizzo());
        lbTipologia.setText(CV.getTipologia().toString());
        // SET DELLE SEVERITA' MEDIA E N. SEGNALAZIONI
        ProspettoEventiAvversi.clear();
        ProspettoEventiAvversi = server.visualizzaInfoCentroVaccinale(CV);
        lbT1.setText(ProspettoEventiAvversi.get(0).getEvento());
        lbM1.setText(ProspettoEventiAvversi.get(0).getSeveritaMedia()+"");
        lbN1.setText(ProspettoEventiAvversi.get(0).getNSegnalazioni()+"");
        lbT2.setText(ProspettoEventiAvversi.get(1).getEvento());
        lbM2.setText(ProspettoEventiAvversi.get(1).getSeveritaMedia()+"");
        lbN2.setText(ProspettoEventiAvversi.get(1).getNSegnalazioni()+"");
        lbT3.setText(ProspettoEventiAvversi.get(2).getEvento());
        lbM3.setText(ProspettoEventiAvversi.get(2).getSeveritaMedia()+"");
        lbN3.setText(ProspettoEventiAvversi.get(2).getNSegnalazioni()+"");
        lbT4.setText(ProspettoEventiAvversi.get(3).getEvento());
        lbM4.setText(ProspettoEventiAvversi.get(3).getSeveritaMedia()+"");
        lbN4.setText(ProspettoEventiAvversi.get(3).getNSegnalazioni()+"");
        lbT5.setText(ProspettoEventiAvversi.get(4).getEvento());
        lbM5.setText(ProspettoEventiAvversi.get(4).getSeveritaMedia()+"");
        lbN5.setText(ProspettoEventiAvversi.get(4).getNSegnalazioni()+"");
        lbT6.setText(ProspettoEventiAvversi.get(5).getEvento());
        lbM6.setText(ProspettoEventiAvversi.get(5).getSeveritaMedia()+"");
        lbN6.setText(ProspettoEventiAvversi.get(5).getNSegnalazioni()+"");
    }

    /**
     * Il metodo <em>clickIndietro</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Indietro</i> nella schermata <i>'Visualizza'</i> dell'applicazione 'cittadini'.
     * Viene riportato alla schermata <i>Cerca</i> (ControllerCerca).
     * @see ControllerCerca
     */
    public void clickIndietro(ActionEvent event){
        try {
            // CHIUSURA
            Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            thisWindow.close();

            // APERTURA NUOVA SCHERMATA
            Stage schermata = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CercaCittadini.fxml"));
            Parent root = loader.load();
            // ... SET DATI
            ControllerCerca cc = loader.getController();
            cc.setDati(User, server);

            schermata.setTitle("Cerca centro vaccinale");
            schermata.setScene(new Scene(root));
            schermata.show();
        } catch (IOException ignored){}
    }

}
