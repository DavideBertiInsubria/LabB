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

import java.io.IOException;

public class ControllerHome {
    @FXML
    Button btnCerca,btnRegistrazione, btnSegnalazione, btnLogin, btnLogout;

    @FXML
    Label lbTitle, lbUser;

    private static Cittadino User;


    public void clickCerca(ActionEvent event){
    }

    public void clickRegistrazione(ActionEvent event)  {
        try {
            // CHIUSURA
            Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            thisWindow.close();

            // APERTURA NUOVA SCHERMATA
            Stage schermataRegistrazione = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegistrazioneCittadini.fxml"));
            Parent root = loader.load();
            schermataRegistrazione.setTitle("Registrazione");
            schermataRegistrazione.setScene(new Scene(root));
            schermataRegistrazione.show();
        } catch (IOException ignored){}
    }

    public void clickSegnalazione(ActionEvent event){
    }

    public void clickLogin(ActionEvent event){
    }

    public void clickLogout(ActionEvent event){
        try {
            // CHIUSURA
            Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            thisWindow.close();

            // APERTURA NUOVA SCHERMATA
            Stage schermataRegistrazione = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeCittadini.fxml"));
            Parent root = loader.load();
                // ... SET DATI
            ControllerHome cc = loader.getController();
            cc.setUser(null);

            schermataRegistrazione.setTitle("Vaccinazione cittadini");
            schermataRegistrazione.setScene(new Scene(root));
            schermataRegistrazione.show();
        } catch (IOException ignored){}
    }

    public void setUser(Cittadino user){
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
