package cittadini;

import common.Cittadino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCerca {

    private Cittadino User;
    @FXML
    TextField textNome, textComune;
    @FXML
    ListView<String> listCentriVacc;
    @FXML
    ComboBox<String> comboTipo;

    @FXML
    public void initialize() {
        comboTipo.getItems().addAll("Qualsiasi","Ospedaliero","Aziendale","Hub");
        comboTipo.setValue("Qualsiasi");
        azzeraFiltro();
    }

    private void azzeraFiltro() {
        // CARICO TUTTI I CENTRI
        listCentriVacc.getItems().clear();
        listCentriVacc.getItems().addAll("prova1", "Prova2", "Prova3");
    }

    public void clickAzzeraFiltro(ActionEvent event) {
        azzeraFiltro();
    }

    public void clickCerca(ActionEvent event) {

    }

    public void clickVisualizza(ActionEvent event) {
        // listCentriVacc.getSelectionModel().selectedItemProperty().isNull();
    }

    public void setUser(Cittadino user) {
        User = user;
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

}
