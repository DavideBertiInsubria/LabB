package cittadini;

import common.Cittadino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import server.ServerInterface;
import javax.swing.*;
import java.io.IOException;

/**
 * La classe <em>ControllerHome</em> rappresenta il <i>controller</i> dell'interfaccia grafica della schermata <i>'Home'</i> dell'applicazione 'cittadini', sviluppato con JavaFX.
 *
 * @author Berti Davide -
 * @author Ivanov Aleksandar Evgeniev - 742789 VA
 * @author Mazza Serghej - 740687 VA
 * @author Rizzi Silvio - 719638 VA
 */
public class ControllerHome {
    @FXML
    Button btnCerca,btnRegistrazione, btnSegnalazione, btnLogin, btnLogout;

    @FXML
    Label lbTitle, lbUser;

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

    /**
     * Il metodo <em>clickVisualizza</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Cerca</i> nella schermata <i>'Home'</i> dell'applicazione 'cittadini'.
     * Viene aperta la schermata di ricerca dei vari centro vaccinale (ControllerCerca).
     * @param event &egrave; il riferimento all'evento eseguito.
     * @see ControllerCerca
     * @see ActionEvent
     */
    public void clickCerca(ActionEvent event){
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
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Errore di tipo \"LOAD\".");
            e.printStackTrace();
        }
    }

    /**
     * Il metodo <em>clickRegistrazione</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Registrazione</i> nella schermata <i>'Home'</i> dell'applicazione 'cittadini'.
     * Viene aperta la schermata di registrazione degli utenti (ControllerRegistrazione).
     * @param event &egrave; il riferimento all'evento eseguito.
     * @see ControllerRegistrazione
     * @see ActionEvent
     */
    public void clickRegistrazione(ActionEvent event)  {
        if (User!=null){
            JOptionPane.showMessageDialog(null, "Per registrare un nuovo utente devi prima effettuare il logout.");
        } else {
            try {
                // CHIUSURA
                Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                thisWindow.close();

                // APERTURA NUOVA SCHERMATA
                Stage schermata = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegistrazioneCittadini.fxml"));
                Parent root = loader.load();
                // ... SET DATI
                ControllerRegistrazione cc = loader.getController();
                cc.setDati(server);
                schermata.setTitle("Registrazione");
                schermata.setScene(new Scene(root));
                schermata.show();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Errore di tipo \"LOAD\".");
                e.printStackTrace();
            }
        }
    }

    /**
     * Il metodo <em>clickSegnalazioni</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Segnalazione</i> nella schermata <i>'Home'</i> dell'applicazione 'cittadini'.
     * Viene aperta la schermata di segnalazione degli eventi avversi al vaccino (ControllerSegnalazioni).
     * @param event &egrave; il riferimento all'evento eseguito.
     * @see ControllerSegnalazioni
     * @see ActionEvent
     */
    public void clickSegnalazioni(ActionEvent event){
        if (User==null){
            clickLogin(event);
        } else {
            try {
                // CHIUSURA
                Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                thisWindow.close();

                // APERTURA NUOVA SCHERMATA
                Stage schermata = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/SegnalazioniCittadini.fxml"));
                Parent root = loader.load();
                // ... SET DATI
                ControllerSegnalazioni cc = loader.getController();
                cc.setDati(User, server);
                schermata.setTitle("Segnalazioni eventi avversi");
                schermata.setScene(new Scene(root));
                schermata.show();
            } catch (IOException e){
                JOptionPane.showMessageDialog(null, "Errore di tipo \"LOAD\".");
                e.printStackTrace();
            }
        }
    }

    /**
     * Il metodo <em>clickLogin</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Login</i> nella schermata <i>'Home'</i> dell'applicazione 'cittadini'.
     * Viene aperta la schermata di login nella quale vi &egrave; la possibilita' di accedere al proprio profilo (ControllerLogin).
     * @param event &egrave; il riferimento all'evento eseguito.
     * @see ControllerLogin
     * @see ActionEvent
     */
    public void clickLogin(ActionEvent event){
        try {
            // CHIUSURA
            Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            thisWindow.close();

            // APERTURA NUOVA SCHERMATA
            Stage schermata = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginCittadini.fxml"));
            Parent root = loader.load();
            // ... SET DATI
            ControllerLogin cc = loader.getController();
            cc.setDati(server);
            schermata.setTitle("Login");
            schermata.setScene(new Scene(root));
            schermata.show();
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Errore di tipo \"LOAD\".");
            e.printStackTrace();
        }
    }

    /**
     * Il metodo <em>clickLogout</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Logout</i> nella schermata <i>'Home'</i> dell'applicazione 'cittadini'.
     * Viene effettuato il logout e ricaricata la schermata Home (ControllerHome).
     * @param event &egrave; il riferimento all'evento eseguito.
     * @see ControllerHome
     * @see ActionEvent
     */
    public void clickLogout(ActionEvent event){
        try {
            // CHIUSURA
            Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            thisWindow.close();

            // APERTURA NUOVA SCHERMATA
            Stage schermata = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeCittadini.fxml"));
            Parent root = loader.load();
                // ... SET DATI
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
     * Il metodo <em>clickConnessione</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Connessione</i> nella schermata <i>'Home'</i> dell'applicazione 'cittadini'.
     * Viene aperta la schermata di gestione del collegamento al server (ControllerConnection).
     * @param event &egrave; il riferimento all'evento eseguito.
     * @see ControllerConnection
     * @see ActionEvent
     */
    public void clickConnessione(ActionEvent event){
        try {
            // CHIUSURA
            Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            thisWindow.close();

            // APERTURA NUOVA SCHERMATA
            Stage schermata = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ConnectionCittadini.fxml"));
            Parent root = loader.load();
            // ... SET DATI
            ControllerConnection cc = loader.getController();
            cc.setDati(User, server);

            schermata.setTitle("Connection");
            schermata.setScene(new Scene(root));
            schermata.show();
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Errore di tipo \"LOAD\".");
            e.printStackTrace();
        }
    }

    /**
     * Il metodo <em>setDati</em> serve per fornire alla schermata <i>'Home'</i> tutti i dati e le informazioni occorrenti dalle altre schermate di interfaccia grafica.
     * Come l'utente loggato e il collegamento al server.
     * Inoltre si occupa di settare opportunamente il bottone login o logout e la stringa di benvenuto in base alla situazione.
     * @param user &egrave; il riferimento all'utente loggato se esiste, altrimenti &egrave; null.
     * @param s &egrave; il riferimento al server.
     * @see Cittadino
     * @see ServerInterface
     */
    public void setDati(Cittadino user, ServerInterface s){
        server = s;
        User = user;
        if (user == null){
            lbUser.setVisible(false);
            lbUser.setText("User");
            btnLogout.setVisible(false);
            btnLogin.setVisible(true);
        }else {
            lbUser.setVisible(true);
            lbUser.setText("Benvenuto " + user.getNome() + " " + user.getCognome() + "!");
            btnLogout.setVisible(true);
            btnLogin.setVisible(false);
        }
    }

}
