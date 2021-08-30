package cittadini;

import common.Cittadino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import server.ServerInterface;

import javax.swing.*;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ControllerConnection  {

    /**
     * <code>server</code> &egrave; il riferimento al server attualmente collegato.
     * @see ServerInterface
     */
    private ServerInterface server;
    /**
     * <code>server</code> &egrave; il riferimento al server a cui si vuole collegare tramite i test (Non &egrave; ancora collegato effettivamente).
     * @see ServerInterface
     */
    private ServerInterface serverTest;
    /**
     * <code>User</code> &egrave; il riferimento all'utente loggato, ed è un oggetto di tipo <i>Cittadino</i>.
     * Se l'utente non è loggato allora <i>User</i> &egrave; uguale a <b>null</b>.
     * @see Cittadino
     */
    private Cittadino User;

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
            lbStato.setStyle("-fx-background-color: red;");
            serverTest = null;
            return;
        }
        lbStato.setText("Stato :  OK");
        lbStato.setStyle("-fx-background-color: green;");

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
            lbStato.setStyle("-fx-background-color: red;");
            JOptionPane.showMessageDialog(null, "Impossibile collegarsi a questo indirizzo IP.");
            serverTest = null;
            return;
        }
        lbStato.setText("Stato :  OK");
        lbStato.setStyle("-fx-background-color: green;");
        JOptionPane.showMessageDialog(null, "Collegamento avvenuto con successo.");
        server = serverTest;

    }

    /**
     * Il metodo <em>clickChiudi</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Chiudi</i> nella schermata <i>'Connection'</i> dell'applicazione 'cittadini'.
     * Non fa altro che chiudere la schermata attuale ed aprire la schermata <i>'Home'</i>.
     * @param event &egrave; il riferimento all'evento eseguito.
     * @see ControllerHome
     * @see ActionEvent
     */
    public void clickChiudi(ActionEvent event) throws IOException {
        if (server != null) {
            // AVVIO APPLICAZIONE CITTADINI
            Stage schermata = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeCittadini.fxml"));
            Parent root = loader.load();

            // ... SET DATI
            ControllerHome cc = loader.getController();
            cc.setDati(User, server);

            schermata.setTitle("Vaccinazione cittadini");
            schermata.setScene(new Scene(root));
            schermata.show();
        } else {
            JOptionPane.showMessageDialog(null, "Bisogna collegarsi ad un server per avviare l'applicazione.");
        }
    }

    /**
     * Il metodo <em>setDati</em> serve per fornire alla schermata <i>'Connection'</i> tutti i dati e le informazioni occorrenti dalle altre schermate di interfaccia grafica.
     * Come l'utente loggato e il collegamento al server.
     * @param user &egrave; il riferimento all'utente loggato se esiste, altrimenti &egrave; null.
     * @param s &egrave; il riferimento al server.
     * @see Cittadino
     * @see ServerInterface
     */
    public void setDati(Cittadino user, ServerInterface s) {
        server = s;
        User = user;
    }
}
