package cittadini;

import common.CentroVaccinale;
import common.Cittadino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class ControllerVisualizza {

    @FXML
    Label lbNome,lbIndirizzo, lbTipologia, lbSMalDiTesta, lbNMalDiTesta, lbSFebbre, lbNFebbre, lbSDolMusc, lbNDolMusc, lbSLinfo, lbNLinfo, lbSTachi, lbNTachi, lbSCrisi, lbNCrisi;
    private CentroVaccinale CV;
    private Cittadino User;

    public void setDati(CentroVaccinale cv, Cittadino user){
        CV = cv;
        User = user;
        lbNome.setText(CV.getNome());
        lbIndirizzo.setText(CV.getIndirizzo());
        lbTipologia.setText(CV.getTipologia().toString());
        // SET DELLE SEVERITA'
    }

    public void clickIndietro(ActionEvent event){
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
            cc.setUser(User);

            schermata.setTitle("Cerca centro vaccinale");
            schermata.setScene(new Scene(root));
            schermata.show();
        } catch (IOException ignored){}
    }


}
