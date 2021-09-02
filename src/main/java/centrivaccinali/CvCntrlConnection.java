package centrivaccinali;

import cittadini.ControllerHome;
import common.Cittadino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import server.ServerInterface;

import javax.swing.*;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Controller associato alla schermata di gestione alla connessione al server.
 *
 * @author Berti Davide -
 * @author Ivanov Aleksandar Evgeniev - 742789 VA
 * @author Mazza Serghej - 740687 VA
 * @author Rizzi Silvio - 719638 VA
 */
public class CvCntrlConnection {

    /**
     * <code>server</code> &egrave; il riferimento al server a cui si vuole collegare tramite i test (Non &egrave; ancora collegato effettivamente).
     * @see ServerInterface
     */
    private ServerInterface serverTest;

    @FXML
    TextField textIP;
    @FXML
    Label lbStato;

    /**
     * Il metodo <em>clickTest</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Test connessione</i> nella schermata <i>'Connection'</i> dell'applicazione 'cittadini'.
     * Viene effettuata una prova di connessione all'indirizzo IP inserito, mostrando alla destra il risultato di tale tentativo.
     * @param event &egrave; il riferimento all'evento eseguito.
     * @see ActionEvent
     */
    public void clickTest(ActionEvent event){
        // ...COLLEGAMENTO AL SERVER
        serverTest = null;
        Registry registro = null;
        try {
            registro = LocateRegistry.getRegistry(textIP.getText(), 1099);
            serverTest = null;
            serverTest = (ServerInterface) registro.lookup("Vaccino");
        } catch (NotBoundException | RemoteException e) {
            e.printStackTrace();
            lbStato.setText("Stato :  KO");
            lbStato.setStyle("-fx-background-color: red; -fx-text-fill:WHITE;");
            serverTest = null;
            return;
        }
        lbStato.setText("Stato :  OK");
        lbStato.setStyle("-fx-background-color: green; -fx-text-fill:WHITE;");

    }

    /**
     * Il metodo <em>clickDefoult</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Default</i> nella schermata <i>'Connection'</i> dell'applicazione 'cittadini'.
     * Viene inserito all'indirizzo IP di default nel campo di compilazione IP (in questo caso <i>localhost</i>).
     * @param event &egrave; il riferimento all'evento eseguito.
     * @see ActionEvent
     */
    public void clickDefoult(ActionEvent event){
        textIP.setText("localhost");
    }

    /**
     * Il metodo <em>clickApplica</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Applica</i> nella schermata <i>'Connection'</i> dell'applicazione 'cittadini'.
     * Viene effettuato un controllo sull'indirizzo IP inserito e in caso positivo viene applicato come server effettivo di lavoro.
     * @param event &egrave; il riferimento all'evento eseguito.
     * @see ActionEvent
     */
    public void clickApplica(ActionEvent event){
        // ...COLLEGAMENTO AL SERVER
        serverTest = null;
        Registry registro = null;
        try {
            registro = LocateRegistry.getRegistry(textIP.getText(), 1099);
            serverTest = null;
            serverTest = (ServerInterface) registro.lookup("Vaccino");
        } catch (NotBoundException | RemoteException e) {
            e.printStackTrace();
            lbStato.setText("Stato :  KO");
            lbStato.setStyle("-fx-background-color: red; -fx-text-fill:WHITE;");
            JOptionPane.showMessageDialog(null, "Impossibile collegarsi a questo indirizzo IP.");
            serverTest = null;
            return;
        }
        lbStato.setText("Stato :  OK");
        lbStato.setStyle("-fx-background-color: green; -fx-text-fill:WHITE;");
        JOptionPane.showMessageDialog(null, "Collegamento avvenuto con successo.");
        ServerConnection.SERVER = serverTest;

    }

    /**
     * Il metodo <em>clickChiudi</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Chiudi</i> nella schermata <i>'Connection'</i> dell'applicazione 'cittadini'.
     * Non fa altro che chiudere la schermata attuale ed aprire la schermata <i>'Home'</i>.
     * @param event &egrave; il riferimento all'evento eseguito.
     * @see ControllerHome
     * @see ActionEvent
     */
    public void clickChiudi(ActionEvent event) {
        if (ServerConnection.SERVER != null) {
            new CvHomePage();
        } else {
            JOptionPane.showMessageDialog(null, "Bisogna collegarsi ad un server per avviare l'applicazione.");
        }
    }
}
