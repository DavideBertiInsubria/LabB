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
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/** @author Ivanov Aleksandar Evgeniev, Mazza Serghej, Berti Davide, Rizzi Silvio
 * La classe <em>ControllerRegistrazione</em> rappresenta il <i>controller</i> dell'interfaccia grafica della schermata <i>'Registrazione'</i> dell'applicazione 'cittadini', sviluppato con JavaFX.
 */
public class ControllerRegistrazione {

    /**
     * <code>server</code> &egrave; il riferimento al server.
     * @see ServerInterface
     */
    private ServerInterface server;

    @FXML
    TextField textNome, textCognome, textEmail, textUserID, textIDVacc, textCF;
    @FXML
    PasswordField textPax, textRPax;

    /**
     * Il metodo <em>clickRegistrati</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Registrati</i> nella schermata <i>'Registrazione'</i> dell'applicazione 'cittadini'.
     * Innanzitutto viene verificato se i campi sono stati compilati correttamente e in caso positivo si viene riportati alla schermata <i>Home</i> con il proprio profilo loggato (ControllerHome).
     * Per la verifica dei campi utilizza il metodo <i>checkCompilazione</i> della medesima classe.
     * @param event &egrave; il riferimento all'evento eseguito.
     * @see ControllerHome
     * @see ActionEvent
     */
    public void clickRegistrati(ActionEvent event) {

        // CHECK COMPILAZIONE
        if (checkCompilazione()) {    // SE TUTTO E' COMPILATO CORRETTAMENTE

            // CONTROLLO ESISTENZA DATI E CORREZIONE
            try {
                Cittadino cittadinoOK = new Cittadino(textCF.getText(), textNome.getText(), textCognome.getText(), textUserID.getText(), textEmail.getText(), textPax.getText(), Integer.valueOf(textIDVacc.getText()), "");
                ArrayList<String> listaErrori = server.registraCittadino(cittadinoOK);
                if ( listaErrori==null ){
                   JOptionPane.showMessageDialog(null, "Utente Registrato con successo.");
                   try {
                       // CHIUSURA
                       Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                       thisWindow.close();

                       // APERTURA NUOVA SCHERMATA
                       Stage schermata = new Stage();
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeCittadini.fxml"));
                       Parent root = loader.load();

                       // ...APERTURA HOME
                       ControllerHome cc = loader.getController();
                       cc.setDati(cittadinoOK, server);
                       schermata.setTitle("Vaccinazione cittadini");
                       schermata.setScene(new Scene(root));
                       schermata.show();
                   } catch (IOException e) {
                       JOptionPane.showMessageDialog(null, "Errore di tipo \"LOAD\".");
                       e.printStackTrace();
                   }
               } else {
                    for (String s : listaErrori) {
                        JOptionPane.showMessageDialog(null, s);
                    }
                }
            } catch (RemoteException e) {
                JOptionPane.showMessageDialog(null, "Errore con il collegamento al server.");
                e.printStackTrace();
                System.exit(0);
            }
        }

    }

    /**
     * Il metodo <em>checkCompilazione</em> si occupa di effettuare la verifica di compilazione dei vari campi necessari alla registrazione.
     * Viene utilizzato nel metodo <i>clickRegistrati</i>.
     * Utilizza i metodi: <i>checkEmail</i> e <i>checkValidPassword</i>.
     * @return <b>true</b>: se &egrave; stato compilato tutto correttamente; <b>false</b>: se non &egrave; stato compilato tutto correttamente.
     */
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

    /**
     * Il metodo <em>checkEmail</em> riceve una stringa e verifica che essa rispetti il format standard di una email, resituendo true o false.
     * Viene utilizzato nel metodo <i>checkCompilazione</i>.
     * @param email &egrave; la stringa su cui si vuole effettuare il controllo.
     * @return <b>true</b>: se &egrave; una email; <b>false</b>: se non &egrave; una email.
     * @see String
     */
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

    /**
     * Il metodo <em>checkValidPassword</em> riceve una stringa e verifica che essa rispetti le regole di composizione di una password, restituendo true o false.
     * Ovvero deve essere composta da:
     * Almeno 8 caratteri;
     * Almeno un numero;
     * Almeno un carattere maiuscolo;
     * Almeno un carattere speciale;
     * Non deve contenere spazi.
     * Viene utilizzato nel metodo <i>checkCompilazione</i>.
     * @param password &egrave; la stringa su cui si vuole effettuare il controllo.
     * @return <b>true</b>: se &egrave; una password valida; <b>false</b>: se non &egrave; una password valida.
     * @see String
     */
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

    /**
     * Il metodo <em>clickIndietro</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Indietro</i> nella schermata <i>'Registrazione'</i> dell'applicazione 'cittadini'.
     * Viene riportato alla schermata <i>Home</i> (ControllerHome).
     * @param event &egrave; il riferimento all'evento eseguito.
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
     * Il metodo <em>setDati</em> serve per fornire alla schermata <i>'Registrazione'</i> tutti i dati e le informazioni occorrenti dalle altre schermate di interfaccia grafica.
     * Come il collegamento al server.
     * @param s &egrave; il riferimento al server.
     * @see ServerInterface
     */
    public void setDati(ServerInterface s) {
        server = s;
    }

}
