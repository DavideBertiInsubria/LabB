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

public class ClientGUI extends Application {

    private ServerInterface server;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // AVVIO APPLICAZIONE CITTADINI
        Stage schermata = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeCittadini.fxml"));
        Parent root = loader.load();

        // ...COLLEGAMENTO AL SERVER
        Registry registro = LocateRegistry.getRegistry("localhost", 1099); // *DA INSERIRE INDIRIZZO IP DEL SERVER
        server = null;
        try {
            server = (ServerInterface) registro.lookup("Vaccino");
        } catch (NotBoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore con il collegamento al server.");
            System.exit(0);
        }

        // ... SET DATI
        ControllerHome cc = loader.getController();
        cc.setDati(null, server);

        schermata.setTitle("Vaccinazione cittadini");
        schermata.setScene(new Scene(root));
        schermata.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}
