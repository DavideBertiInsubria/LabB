package cittadini;

import common.CentroVaccinale;
import common.Cittadino;
import common.ClientImpl;
import common.TipologiaCentro;
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
import server.ServerInterface;

import javax.swing.*;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ControllerCerca {

    private Cittadino User;
    private ArrayList<CentroVaccinale> listaCentriVaccinaliVisualizzati = new ArrayList<CentroVaccinale>();
    private ServerInterface server;
    @FXML
    TextField textNome, textComune;
    @FXML
    ListView<String> listCentriVacc;
    @FXML
    ComboBox<String> comboTipo;

    @FXML
    public void initialize() throws RemoteException {
        comboTipo.getItems().addAll("Qualsiasi","Ospedaliero","Aziendale","Hub");
        comboTipo.setValue("Qualsiasi");
        azzeraFiltro();
    }

    private void azzeraFiltro() throws RemoteException {
        listCentriVacc.getItems().clear();
        listaCentriVaccinaliVisualizzati.clear();
        // SET LISTA
        ClientImpl obj = new ClientImpl(); // TOGLIERE OBJ
        listaCentriVaccinaliVisualizzati = server.cercaCentroVaccinale("", "", "", obj);
        for (int i=0; i<listaCentriVaccinaliVisualizzati.size(); i++){
            listCentriVacc.getItems().add( listaCentriVaccinaliVisualizzati.get(i).getNome() + " - " + listaCentriVaccinaliVisualizzati.get(i).getIndirizzo() );
        }
    }

    public void clickAzzeraFiltro(ActionEvent event) throws RemoteException {
        azzeraFiltro();
    }

    public void clickCerca(ActionEvent event) throws RemoteException {

        listCentriVacc.getItems().clear();
        listaCentriVaccinaliVisualizzati.clear();
        String Nome, Comune, Tipo;
        //RIELABORAZIONE DATI
        if (textNome.getText() == null)
            Nome = "";
        else
            Nome = textNome.getText();
        if (textNome.getText() == null)
            Comune = "";
        else
            Comune = textComune.getText();
        if (comboTipo.getValue().equals("Qualsiasi"))
            Tipo = "";
        else
            Tipo = comboTipo.getValue();
        // SET LISTA
        ClientImpl obj = new ClientImpl();
        listaCentriVaccinaliVisualizzati = server.cercaCentroVaccinale(Nome, Comune, Tipo, obj);
        for (int i=0; i<listaCentriVaccinaliVisualizzati.size(); i++){
            listCentriVacc.getItems().add( listaCentriVaccinaliVisualizzati.get(i).getNome() + " - " + listaCentriVaccinaliVisualizzati.get(i).getIndirizzo() );
        }
    }

    public void clickVisualizza(ActionEvent event) {
        if (listCentriVacc.getSelectionModel().getSelectedIndex() == -1 ){
            JOptionPane.showMessageDialog(null, "Selezionare un centro vaccinale dalla lista.");
            return;
        }
        try {
            // CHIUSURA
            Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            thisWindow.close();

            // APERTURA NUOVA SCHERMATA
            Stage schermata = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/VisualizzaCittadini.fxml"));
            Parent root = loader.load();
            ControllerVisualizza cc = loader.getController();
            cc.setDati(listaCentriVaccinaliVisualizzati.get(listCentriVacc.getSelectionModel().getSelectedIndex()), User, server);  // PASSO IL CENTRO VACCINALE SELEZIONATO
            schermata.setTitle("Visualizza centro vaccinale");
            schermata.setScene(new Scene(root));
            schermata.show();
        } catch (IOException ignored){ }
    }

    public void setDati(Cittadino user, ServerInterface s) throws RemoteException {
        server = s;
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
            cc.setDati(User, server);
            schermata.setTitle("Vaccinazione cittadini");
            schermata.setScene(new Scene(root));
            schermata.show();
        } catch (IOException ignored){}
    }

}