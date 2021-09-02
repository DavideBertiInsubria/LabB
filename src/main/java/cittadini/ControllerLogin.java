package cittadini;

import common.Cittadino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.ServerInterface;
import javax.swing.*;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * La classe <em>ControllerLogin</em> rappresenta il <i>controller</i> dell'interfaccia grafica della schermata <i>'Login'</i> dell'applicazione 'cittadini', sviluppato con JavaFX.
 *
 * @author Berti Davide - 740665 VA
 * @author Ivanov Aleksandar Evgeniev - 742789 VA
 * @author Mazza Serghej - 740687 VA
 * @author Rizzi Silvio - 719638 VA
 */
public class ControllerLogin {

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
    @FXML
    TextField textUserID;
    @FXML
    PasswordField textPax;

    /**
     * Il metodo <em>clickIndietro</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Indietro</i> nella schermata <i>'Login'</i> dell'applicazione 'cittadini'.
     * Viene riportato alla schermata <i>Home</i> (ControllerHome).
     * * @param event &egrave; il riferimento all'evento eseguito.
     * @see ControllerHome
     * @see ActionEvent
     */
    public void clickIndietro(ActionEvent event)  {
        try {
            // CHIUSURA
            Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            thisWindow.close();

            // APERTURA NUOVA SCHERMATA
            Stage schermata = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeCittadini.fxml"));
            Parent root = loader.load();
            ControllerHome cc = loader.getController();
            cc.setDati(null, server);
            schermata.setTitle("Vaccinazione cittadini");
            schermata.setScene(new Scene(root));
            schermata.show();
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Errore di tipo \"LOAD\".");
            e.printStackTrace();
        }
    }

    /**
     * Il metodo <em>clickAccedi</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Accedi</i> nella schermata <i>'Login'</i> dell'applicazione 'cittadini'.
     * Viene verificato se i dati sono corretti e in caso positivo si viene riportati alla schermata <i>Home</i> con il proprio profilo loggato (ControllerHome).
     * @param event &egrave; il riferimento all'evento eseguito.
     * @see ControllerHome
     * @see ActionEvent
     */
    public void clickAccedi(ActionEvent event) {

        // CHECK COMPILAZIONE
        if (textPax.getText().equals("") || textUserID.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Compilare i campi.");
            return;
        }

        // CONTROLLO ESISTENZA UTENTE
        Cittadino utente = null;
        try {
            utente = server.login(textUserID.getText(), textPax.getText());
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Errore con il collegamento al server.");
            e.printStackTrace();
            System.exit(0);
        }
        //... non trovato
        if (utente == null) {
            JOptionPane.showMessageDialog(null, "Utente non trovato. Ricontrollare i dati inseriti. ");
        } else {
            //... trovato
            User = utente;
            try {
                // CHIUSURA
                Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                thisWindow.close();
                // APERTURA NUOVA SCHERMATA
                Stage schermata = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeCittadini.fxml"));
                Parent root = loader.load();
                ControllerHome cc = loader.getController();
                cc.setDati(User, server);
                schermata.setTitle("Vaccinazione cittadini");
                schermata.setScene(new Scene(root));
                schermata.show();
            } catch (IOException e){
                JOptionPane.showMessageDialog(null, "Errore di tipo \"LOAD\".");
                e.printStackTrace();
            }
        }
    }

    /**
     * Il metodo <em>setDati</em> serve per fornire alla schermata <i>'Login'</i> tutti i dati e le informazioni occorrenti dalle altre schermate di interfaccia grafica.
     * Come il collegamento al server.
     * @param s &egrave; il riferimento al server.
     * @see ServerInterface
     */
    public void setDati(ServerInterface s) {
        server = s;
    }

}
