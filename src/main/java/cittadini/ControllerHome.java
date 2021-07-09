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

public class ControllerHome {
    @FXML
    Button btnCerca,btnRegistrazione, btnSegnalazione, btnLogin, btnLogout;

    @FXML
    Label lbTitle, lbUser;

    private static Cittadino User;
    private ServerInterface server;

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
        } catch (IOException ignored){ }
    }

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
            } catch (IOException ignored) {
            }
        }
    }

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
            } catch (IOException ignored){}
        }
    }

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
        } catch (IOException ignored){}
    }

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
        } catch (IOException ignored){}
    }

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
