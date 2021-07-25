package cittadini;

import common.Cittadino;
import common.Segnalazione;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import server.ServerInterface;
import javax.swing.*;
import java.io.IOException;

public class ControllerSegnalazioni {

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
    ComboBox<Integer> comboMalDiTesta, comboFebbre, comboDoloriMuscArtic, comboLinfo, comboTachi, comboCrisi;
    @FXML
    TextArea textMalDiTesta, textFebbre, textDoloriMuscArtic, textLinfo, textTachi, textCrisi;

    @FXML
    public void initialize() {
        comboMalDiTesta.getItems().addAll(1,2,3,4,5);
        comboFebbre.getItems().addAll(1,2,3,4,5);
        comboDoloriMuscArtic.getItems().addAll(1,2,3,4,5);
        comboLinfo.getItems().addAll(1,2,3,4,5);
        comboTachi.getItems().addAll(1,2,3,4,5);
        comboCrisi.getItems().addAll(1,2,3,4,5);
    }

    /**
     * Il metodo <em>clickIndietro</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Indietro</i> nella schermata <i>'Segnalazioni'</i> dell'applicazione 'cittadini'.
     * Viene riportato alla schermata <i>Home</i> (ControllerHome).
     * @see ControllerHome
     */
    public void clickIndietro(ActionEvent event)  {
        try {

            // APERTURA NUOVA SCHERMATA
            Stage schermata = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeCittadini.fxml"));
            Parent root = loader.load();
            ControllerHome cc = loader.getController();
            cc.setDati(User, server);
            schermata.setTitle("Vaccinazione cittadini");
            schermata.setScene(new Scene(root));
            schermata.show();
        } catch (IOException ignored){}
    }

    /**
     * Il metodo <em>clickInvia</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Invia</i> nella schermata <i>'Segnalazioni'</i> dell'applicazione 'cittadini'.
     * Vengono inviate tutte le segnalazioni compilate al server e si viene riportati alla schermata <i>Home</i> con il proprio profilo loggato (ControllerHome).
     * <b>ATTENZIONE</b>: la segnalazione può essere inviata una sola volta per utente.
     * @see ControllerHome
     */
    public void clickInvia(ActionEvent event)  {
        try {
          
        	// CHIUSURA
            Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            thisWindow.close();
            
            // INVIO SEGNALAZIONE...
            if (comboMalDiTesta.getValue()>0 && comboMalDiTesta.getValue()<=5) {
                Segnalazione s = new Segnalazione(User.getIDVaccino(), "Mal di testa", comboMalDiTesta.getValue(), textMalDiTesta.getText());
                server.registraSegnalazione(s);
            }
            if (comboFebbre.getValue()>0 && comboFebbre.getValue()<=5) {
                Segnalazione s = new Segnalazione(User.getIDVaccino(), "Febbre", comboFebbre.getValue(), textFebbre.getText());
                server.registraSegnalazione(s);
            }
            if (comboDoloriMuscArtic.getValue()>0 && comboDoloriMuscArtic.getValue()>=5) {
                Segnalazione s = new Segnalazione(User.getIDVaccino(), "Dolori muscolari e articolari", comboDoloriMuscArtic.getValue(), textDoloriMuscArtic.getText());
                server.registraSegnalazione(s);
            }
            if (comboLinfo.getValue()>0 && comboLinfo.getValue()<=5) {
                Segnalazione s = new Segnalazione(User.getIDVaccino(), "Linfoadenopatia", comboLinfo.getValue(), textLinfo.getText());
                server.registraSegnalazione(s);
            }
            if (comboTachi.getValue()>0 && comboTachi.getValue()<=5) {
                Segnalazione s = new Segnalazione(User.getIDVaccino(), "Tachicardia", comboTachi.getValue(), textTachi.getText());
                server.registraSegnalazione(s);
            }
            if (comboCrisi.getValue()>0 && comboCrisi.getValue()<=5) {
                Segnalazione s = new Segnalazione(User.getIDVaccino(), "Crisi ipertensiva", comboCrisi.getValue(), textCrisi.getText());
                server.registraSegnalazione(s);
            }

            // APERTURA NUOVA SCHERMATA
            Stage schermata = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeCittadini.fxml"));
            Parent root = loader.load();
            ControllerHome cc = loader.getController();
            cc.setDati(User, server);
            schermata.setTitle("Vaccinazione cittadini");
            schermata.setScene(new Scene(root));
            JOptionPane.showMessageDialog(null, "Segnalazione inviata con successo.");
            schermata.show();
        } catch (IOException e){
        	e.printStackTrace();
        }
    }

    /**
     * Il metodo <em>setDati</em> serve per fornire alla schermata <i>'Segnalazioni'</i> tutti i dati e le informazioni occorrenti dalle altre schermate di interfaccia grafica.
     * Come l'utente loggato e il collegamento al server.
     */
    public void setDati(Cittadino c, ServerInterface s){
        server = s;
        User = c;
    }
}
