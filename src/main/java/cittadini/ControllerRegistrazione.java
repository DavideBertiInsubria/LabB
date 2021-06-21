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
import javax.swing.*;
import java.io.IOException;


public class ControllerRegistrazione {

    @FXML
    TextField textNome, textCognome, textEmail, textUserID, textIDCentroVacc;
    @FXML
    PasswordField textPax, textRPax;

    public void clickProva(ActionEvent event)  {

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

                    // ...CREAZIONE CITTADINO DI PROVA e SET
                Cittadino cittadinoProva = new Cittadino();
                cittadinoProva.setNome("Paolo");
                cittadinoProva.setCognome("Rossi");
                ControllerHome cc = loader.getController();
                cc.setUser(cittadinoProva);

                schermata.setTitle("Vaccinazione cittadini");
                schermata.setScene(new Scene(root));
                schermata.show();
            } catch (IOException ignored){}
        }

    }

    private boolean checkCompilazione() {
        if (textNome.getText().equals("") || textCognome.getText().equals("") || textEmail.getText().equals("") || textUserID.getText().equals("") || textPax.getText().equals("") || textRPax.getText().equals("") || textIDCentroVacc.getText().equals("") ) {
            JOptionPane.showMessageDialog(null, "Compilare tutti i campi.");
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
            cc.setUser(null);
            schermata.setTitle("Vaccinazione cittadini");
            schermata.setScene(new Scene(root));
            schermata.show();
        } catch (IOException ignored){}
    }
}
