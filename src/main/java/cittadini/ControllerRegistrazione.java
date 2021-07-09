package cittadini;

import common.Cittadino;
import common.ClientImpl;
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
import java.rmi.server.UnicastRemoteObject;
import java.util.regex.Pattern;

public class ControllerRegistrazione {

    private ServerInterface server;
    private Cittadino User;

    @FXML
    TextField textNome, textCognome, textEmail, textUserID, textIDVacc, textCF;
    @FXML
    PasswordField textPax, textRPax;

    public void clickRegistrati(ActionEvent event)  {

        // CHECK COMPILAZIONE
        if (checkCompilazione()) {
            // SE TUTTO E' COMPILATO CORRETTAMENTE
            try {
                // CHIUSURA
                Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                thisWindow.close();

                // APERTURA NUOVA SCHERMATA
                Stage schermata = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeCittadini.fxml"));
                Parent root = loader.load();

                // CONTROLLO USERID

                    // ...CREAZIONE CITTADINO DI PROVA e SET
                Cittadino cittadinoOK = new Cittadino(textCF.getText(), textNome.getText(), textCognome.getText(),textUserID.getText(), textEmail.getText(), textPax.getText(), textIDVacc.getText(), null);
                server.registraCittadino(cittadinoOK);
                User = cittadinoOK;

                    // ...APERTURA HOME
                ControllerHome cc = loader.getController();
                cc.setDati(User, server);
                schermata.setTitle("Vaccinazione cittadini");
                schermata.setScene(new Scene(root));
                schermata.show();
            } catch (IOException  ignored){}
        }

    }

    private boolean checkCompilazione() {
        if (textNome.getText().equals("") || textCognome.getText().equals("") || textEmail.getText().equals("") || textUserID.getText().equals("") || textPax.getText().equals("") || textRPax.getText().equals("") || textIDVacc.getText().equals("") || textCF.getText().equals("") ) {
            JOptionPane.showMessageDialog(null, "Compilare tutti i campi.");
            return false;
        }
        if ( !checkEmail(textEmail.getText()) ){
            JOptionPane.showMessageDialog(null, "L'indirizzo email inserito non e' valido.");
            return false;
        }
        if ( !checkValidPassword(textPax.getText()) ){
            JOptionPane.showMessageDialog(null, "La password deve contenere almeno 8 caratteri di cui almeno una maiuscola, almeno un numero e almeno un carattere speciale.");
            return false;
        }
        if ( !textPax.getText().equals(textRPax.getText()) ){
            JOptionPane.showMessageDialog(null, "Le due password non combaciano.");
            return false;
        }
        return true;
    }

    private boolean checkEmail(String email) {
        String emailFormat = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailFormat);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    private boolean checkValidPassword(String password) {
        boolean number=false, upper=false, special=false;
        if (password.length()>=8) {
            for(char c : password.toCharArray()){
                if (Character.isDigit(c)) number = true;
                if (Character.isUpperCase(c)) upper = true;
                if (!Character.isLetterOrDigit(c)) special = true;
                if (Character.isSpaceChar(c)){ return false; }
            }
        }
        return number && upper && special;
    }

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
        } catch (IOException ignored){}
    }

    public void setDati(ServerInterface s) {
        server = s;
    }

}
