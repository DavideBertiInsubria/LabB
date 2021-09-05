package cittadini;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.ServerInterface;

import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/**
 * La classe <em>ClientGUI</em> rappresenta il punto di inizio dell'applicazione 'cittadini', ovvero contiene il main di avvio.
 * L'interfaccia grafica è implementata tramite <em>JavaFX</em>.
 *
 *  * @author Berti Davide - 740665 VA
 *  * @author Ivanov Aleksandar Evgeniev - 742789 VA
 *  * @author Mazza Serghej - 740687 VA
 *  * @author Rizzi Silvio - 719638 VA
 * @see Application
 */
public class ClientGUI extends Application {

    /**
     * Il metodo <em>start</em> si occupa di avviare l'applicazione 'cittadini' aprendo innanzitutto la schermata di connessione al server.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // AVVIO APPLICAZIONE CITTADINI
        Stage schermata = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ConnectionCittadini.fxml"));
        Parent root = loader.load();
        // ... SET DATI
        ControllerConnection cc = loader.getController();
        cc.setDati(null, null);
        schermata.setTitle("Connection");
        schermata.setScene(new Scene(root));
        schermata.show();
    }

    public static void main(String[] args){
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());
        launch(args);
    }

}
