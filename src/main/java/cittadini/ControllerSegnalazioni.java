package cittadini;

import common.Cittadino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;


public class ControllerSegnalazioni {

    @FXML
    ComboBox<Integer> comboMalDiTesta, comboFebbre, comboDoloriMuscArtic, comboLinfo, comboTachi, comboCrisi;
    @FXML
    TextArea textMalDiTesta, textFebbre, textDoloriMuscArtic, textLinfo, textTachi, textCrisi;
    private Cittadino User;

    @FXML
    public void initialize() {
        comboMalDiTesta.getItems().addAll(1,2,3,4,5);
        comboFebbre.getItems().addAll(1,2,3,4,5);
        comboDoloriMuscArtic.getItems().addAll(1,2,3,4,5);
        comboLinfo.getItems().addAll(1,2,3,4,5);
        comboTachi.getItems().addAll(1,2,3,4,5);
        comboCrisi.getItems().addAll(1,2,3,4,5);
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
            cc.setUser(User);
            schermata.setTitle("Vaccinazione cittadini");
            schermata.setScene(new Scene(root));
            schermata.show();
        } catch (IOException ignored){}
    }

    public void clickInvia(ActionEvent event)  {
        try {
            // CHIUSURA
            Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            thisWindow.close();

            // APERTURA NUOVA SCHERMATA
            Stage schermata = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeCittadini.fxml"));
            Parent root = loader.load();
            ControllerHome cc = loader.getController();
            cc.setUser(User);
            schermata.setTitle("Vaccinazione cittadini");
            schermata.setScene(new Scene(root));
            JOptionPane.showMessageDialog(null, "Segnalazione inviata con successo.");
            schermata.show();
        } catch (IOException ignored){}
    }

    public void setUser(Cittadino c){
        User = c;
    }
}
