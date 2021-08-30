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
import java.rmi.RemoteException;

/** @author Ivanov Aleksandar Evgeniev, Mazza Serghej, Berti Davide, Rizzi Silvio
 * La classe <em>ControllerSegnalazioni</em> rappresenta il <i>controller</i> dell'interfaccia grafica della schermata <i>'Segnalazioni'</i> dell'applicazione 'cittadini', sviluppato con JavaFX.
 */
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
        comboMalDiTesta.getItems().addAll(0,1,2,3,4,5);
        comboFebbre.getItems().addAll(0,1,2,3,4,5);
        comboDoloriMuscArtic.getItems().addAll(0,1,2,3,4,5);
        comboLinfo.getItems().addAll(0,1,2,3,4,5);
        comboTachi.getItems().addAll(0,1,2,3,4,5);
        comboCrisi.getItems().addAll(0,1,2,3,4,5);
        comboMalDiTesta.getSelectionModel().select(0);
        comboFebbre.getSelectionModel().select(0);
        comboDoloriMuscArtic.getSelectionModel().select(0);
        comboLinfo.getSelectionModel().select(0);
        comboTachi.getSelectionModel().select(0);
        comboCrisi.getSelectionModel().select(0);
    }

    /**
     * Il metodo <em>clickIndietro</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Indietro</i> nella schermata <i>'Segnalazioni'</i> dell'applicazione 'cittadini'.
     * Viene riportato alla schermata <i>Home</i> (ControllerHome).
     * @param event &egrave; il riferimento all'evento eseguito.
     * @see ControllerHome
     * @see ActionEvent
     */
    public void clickIndietro(ActionEvent event)  {

        // CHIUSURA
        Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        thisWindow.close();

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
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Errore di tipo \"LOAD\".");
            e.printStackTrace();
        }
    }

    /**
     * Il metodo <em>clickInvia</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Invia</i> nella schermata <i>'Segnalazioni'</i> dell'applicazione 'cittadini'.
     * Vengono inviate tutte le segnalazioni compilate al server e si viene riportati alla schermata <i>Home</i> con il proprio profilo loggato (ControllerHome).
     * @param event &egrave; il riferimento all'evento eseguito.
     * @see ControllerHome
     * @see ActionEvent
     */
    public void clickInvia(ActionEvent event)  {

        try {
            // INVIO SEGNALAZIONE...
            if (comboMalDiTesta.getValue()!=null && comboMalDiTesta.getValue()>0) { // e' stata compilata la severità?   -    comboMalDiTesta.getValue()>0 && comboMalDiTesta.getValue()<=5 ??
                Segnalazione s = new Segnalazione(User.getIDVaccino(), "Mal di testa", comboMalDiTesta.getValue(), textMalDiTesta.getText());
                server.registraSegnalazione(s);
            }
            if (comboFebbre.getValue()!=null  && comboFebbre.getValue()>0) {
                Segnalazione s = new Segnalazione(User.getIDVaccino(), "Febbre", comboFebbre.getValue(), textFebbre.getText());
                server.registraSegnalazione(s);
            }
            if (comboDoloriMuscArtic.getValue()!=null  && comboDoloriMuscArtic.getValue()>0) {
                Segnalazione s = new Segnalazione(User.getIDVaccino(), "Dolori muscolari e articolari", comboDoloriMuscArtic.getValue(), textDoloriMuscArtic.getText());
                server.registraSegnalazione(s);
            }
            if (comboLinfo.getValue()!=null  && comboLinfo.getValue()>0) {
                Segnalazione s = new Segnalazione(User.getIDVaccino(), "Linfoadenopatia", comboLinfo.getValue(), textLinfo.getText());
                server.registraSegnalazione(s);
            }
            if (comboTachi.getValue()!=null  && comboTachi.getValue()>0) {
                Segnalazione s = new Segnalazione(User.getIDVaccino(), "Tachicardia", comboTachi.getValue(), textTachi.getText());
                server.registraSegnalazione(s);
            }
            if (comboCrisi.getValue()!=null  && comboCrisi.getValue()>0) {
                Segnalazione s = new Segnalazione(User.getIDVaccino(), "Crisi ipertensiva", comboCrisi.getValue(), textCrisi.getText());
                    server.registraSegnalazione(s);
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Errore con il collegamento al server.");
            e.printStackTrace();
            System.exit(0);
        }

        // CHIUSURA
        Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        thisWindow.close();

        try {
            // APERTURA NUOVA SCHERMATA
            Stage schermata = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeCittadini.fxml"));
            Parent root = loader.load();
            ControllerHome cc = loader.getController();
            cc.setDati(User, server);
            schermata.setTitle("Vaccinazione cittadini");
            schermata.setScene(new Scene(root));
            JOptionPane.showMessageDialog(null, "Segnalazione\\i inviata con successo.");
            schermata.show();
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Errore di tipo \"LOAD\".");
            e.printStackTrace();
        }
    }

    /**
     * Il metodo <em>setDati</em> serve per fornire alla schermata <i>'Segnalazioni'</i> tutti i dati e le informazioni occorrenti dalle altre schermate di interfaccia grafica.
     * Come l'utente loggato e il collegamento al server.
     * Inoltre si collega al server e controlla se sono gia' state inviate segnalazioni di un certo tipo, e in tal caso blocca la loro compilazione.
     * @param c &egrave; il riferimento all'utente loggato.
     * @param s &egrave; il riferimento al server.
     * @see Cittadino
     * @see ServerInterface
     */
    public void setDati(Cittadino c, ServerInterface s){
        server = s;
        User = c;
        // CHECK SULL'ESISTENZA DI UNA SEGNALAZIONE DI OGNI EVENTO
        try {
            if (server.checkSegnalazione(User.getIDVaccino(), "Mal di testa")){
                comboMalDiTesta.setDisable(true);
                textMalDiTesta.setEditable(false);
                textMalDiTesta.setPromptText("E' già stata inviata una segnalazione per questo evento.");
            }
            if (server.checkSegnalazione(User.getIDVaccino(), "Febbre")){
                comboFebbre.setDisable(true);
                textFebbre.setEditable(false);
                textFebbre.setPromptText("E' già stata inviata una segnalazione per questo evento.");
            }
            if (server.checkSegnalazione(User.getIDVaccino(), "Dolori muscolari e articolari")){
                comboDoloriMuscArtic.setDisable(true);
                textDoloriMuscArtic.setEditable(false);
                textDoloriMuscArtic.setPromptText("E' già stata inviata una segnalazione per questo evento.");
            }
            if (server.checkSegnalazione(User.getIDVaccino(), "Linfoadenopatia")){
                comboLinfo.setDisable(true);
                textLinfo.setEditable(false);
                textLinfo.setPromptText("E' già stata inviata una segnalazione per questo evento.");
            }
            if (server.checkSegnalazione(User.getIDVaccino(), "Tachicardia")){
                comboTachi.setDisable(true);
                textTachi.setEditable(false);
                textTachi.setPromptText("E' già stata inviata una segnalazione per questo evento.");
            }
            if (server.checkSegnalazione(User.getIDVaccino(), "Crisi ipertensiva")){
                comboCrisi.setDisable(true);
                textCrisi.setEditable(false);
                textCrisi.setPromptText("E' già stata inviata una segnalazione per questo evento.");
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Errore con il collegamento al server.");
            e.printStackTrace();
            System.exit(0);
        }
    }
}
