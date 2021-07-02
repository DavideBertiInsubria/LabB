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

        /* TEST MANUALE
        // CARICO TUTTI I CENTRI
        listCentriVacc.getItems().clear();
        CentroVaccinale prova1 = new CentroVaccinale("CentroVaccinaleDiPaolo", "Via Qualcosa, 78", TipologiaCentro.AZIENDALE);
        CentroVaccinale prova2 = new CentroVaccinale("CentroVaccinaleDiDavide", "Viale Random, 34", TipologiaCentro.HUB);
        CentroVaccinale prova3 = new CentroVaccinale("CentroVaccinaleDiGiorgio", "Via Pozzi, 5", TipologiaCentro.HUB);
        CentroVaccinale prova4 = new CentroVaccinale("CentroVaccinaleDiMarco", "Via milano, 5", TipologiaCentro.OSPEDALIERO);
        CentroVaccinale prova5 = new CentroVaccinale("CentroVaccinaleDiAndrea", "Via nonsodove, 11", TipologiaCentro.AZIENDALE);
        CentroVaccinale prova6 = new CentroVaccinale("CentroVaccinaleDiStefano", "Via chilosa, 22", TipologiaCentro.OSPEDALIERO);
        CentroVaccinale prova7 = new CentroVaccinale("CentroVaccinaleDiManuel", "Via ovunquesivuole, 90", TipologiaCentro.AZIENDALE);
        CentroVaccinale prova8 = new CentroVaccinale("CentroVaccinaleDiFrancesco", "Via rossifuoco, 1", TipologiaCentro.AZIENDALE);
        CentroVaccinale prova9 = new CentroVaccinale("CentroVaccinaleDiFabio", "Via verdefoglia, 13", TipologiaCentro.HUB);
        CentroVaccinale prova10= new CentroVaccinale("CentroVaccinaleDiAlex", "Piazzale longobardo, 19", TipologiaCentro.OSPEDALIERO);
        CentroVaccinale prova11= new CentroVaccinale("CentroVaccinaleDiAlice", "Viale sassimoltoduri, 1", TipologiaCentro.OSPEDALIERO);
        CentroVaccinale prova12= new CentroVaccinale("CentroVaccinaleDiGiulia", "Via nonsocosainventarmi, 3", TipologiaCentro.AZIENDALE);
        CentroVaccinale prova13= new CentroVaccinale("CentroVaccinaleDiMartina", "Viale finitoleprove, 2", TipologiaCentro.HUB);
        listaCentriVaccinaliVisualizzati.add(prova1);
        listaCentriVaccinaliVisualizzati.add(prova2);
        listaCentriVaccinaliVisualizzati.add(prova3);
        listaCentriVaccinaliVisualizzati.add(prova4);
        listaCentriVaccinaliVisualizzati.add(prova5);
        listaCentriVaccinaliVisualizzati.add(prova6);
        listaCentriVaccinaliVisualizzati.add(prova7);
        listaCentriVaccinaliVisualizzati.add(prova8);
        listaCentriVaccinaliVisualizzati.add(prova9);
        listaCentriVaccinaliVisualizzati.add(prova10);
        listaCentriVaccinaliVisualizzati.add(prova11);
        listaCentriVaccinaliVisualizzati.add(prova12);
        listaCentriVaccinaliVisualizzati.add(prova13);
        listCentriVacc.getItems().addAll(prova1.getNome()+ " - " + prova1.getIndirizzo(),
                prova2.getNome()+ " - " + prova2.getIndirizzo(),
                prova3.getNome()+ " - " + prova3.getIndirizzo(),
                prova4.getNome()+ " - " + prova4.getIndirizzo(),
                prova5.getNome()+ " - " + prova5.getIndirizzo(),
                prova6.getNome()+ " - " + prova6.getIndirizzo(),
                prova7.getNome()+ " - " + prova7.getIndirizzo(),
                prova8.getNome()+ " - " + prova8.getIndirizzo(),
                prova9.getNome()+ " - " + prova9.getIndirizzo(),
                prova10.getNome()+ " - " + prova10.getIndirizzo(),
                prova11.getNome()+ " - " + prova11.getIndirizzo(),
                prova12.getNome()+ " - " + prova12.getIndirizzo(),
                prova13.getNome()+ " - " + prova13.getIndirizzo() );
        */


        // SERVER

        // ...COLLEGAMENTO AL SERVER
        Registry registro = LocateRegistry.getRegistry("localhost", 1099); // *DA INSERIRE INDIRIZZO IP DEL SERVER
        ServerInterface server = null;
        try {
            server = (ServerInterface) registro.lookup("Vaccino");
        } catch (NotBoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore con il collegamento al server.");
            System.exit(0);
        }
        ClientImpl obj = new ClientImpl();
        ClientImpl stub = (ClientImpl) UnicastRemoteObject.exportObject(obj, 3939);
        listaCentriVaccinaliVisualizzati = server.cercaCentroVaccinale("", "", null, stub);
        for (int i=0; i<listaCentriVaccinaliVisualizzati.size(); i++){
            listCentriVacc.getItems().add( listaCentriVaccinaliVisualizzati.get(i).getNome() + " - " + listaCentriVaccinaliVisualizzati.get(i).getIndirizzo() );
        }
    }

    public void clickAzzeraFiltro(ActionEvent event) throws RemoteException {
        azzeraFiltro();
    }

    public void clickCerca(ActionEvent event) {

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
            cc.setDati(listaCentriVaccinaliVisualizzati.get(listCentriVacc.getSelectionModel().getSelectedIndex()), User);  // PASSO IL CENTRO VACCINALE SELEZIONATO
            schermata.setTitle("Visualizza centro vaccinale");
            schermata.setScene(new Scene(root));
            schermata.show();
        } catch (IOException ignored){            System.out.print(ignored);
        }
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