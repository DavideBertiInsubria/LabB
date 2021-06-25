package cittadini;

import common.Cittadino;
import common.ClientImpl;
import common.ClientInterface;
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
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class ControllerRegistrazione {

    @FXML
    TextField textNome, textCognome, textEmail, textUserID, textIDCentroVacc, textCF;
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
                Cittadino cittadinoOK = new Cittadino(textCF.getText(), textNome.getText(), textCognome.getText(), textEmail.getText(), textPax.getText(), textIDCentroVacc.getText(), null);

                    // ...COLLEGAMENTO AL SERVER
                Registry registro = LocateRegistry.getRegistry("*", 1099); // *DA INSERIRE INDIRIZZO IP DEL SERVER
                ServerInterface server = (ServerInterface) registro.lookup("Vaccino");
                ClientImpl obj = new ClientImpl();
                ClientImpl stub = (ClientImpl) UnicastRemoteObject.exportObject(obj, 3939);
                server.registraCittadino(cittadinoOK, stub);

                    // ...APERTURA HOME
                ControllerHome cc = loader.getController();
                cc.setUser(cittadinoOK);
                schermata.setTitle("Vaccinazione cittadini");
                schermata.setScene(new Scene(root));
                schermata.show();
            } catch (IOException | NotBoundException ignored){}
        }

    }

    private boolean checkCompilazione() {
        if (textNome.getText().equals("") || textCognome.getText().equals("") || textEmail.getText().equals("") || textUserID.getText().equals("") || textPax.getText().equals("") || textRPax.getText().equals("") || textIDCentroVacc.getText().equals("") || textCF.getText().equals("") ) {
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
