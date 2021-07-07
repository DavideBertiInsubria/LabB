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
import server.ServerInterface;

import javax.swing.*;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ControllerVisualizza {

    @FXML
    Label lbNome,lbIndirizzo, lbTipologia, lbSMalDiTesta, lbNMalDiTesta, lbSFebbre, lbNFebbre, lbSDolMusc, lbNDolMusc, lbSLinfo, lbNLinfo, lbSTachi, lbNTachi, lbSCrisi, lbNCrisi;
    private CentroVaccinale CV;
    private Cittadino User;
    private ServerInterface server;

    @FXML
    public void initialize() throws RemoteException {
        // ...COLLEGAMENTO AL SERVER
        Registry registro = LocateRegistry.getRegistry("localhost", 1099); // *DA INSERIRE INDIRIZZO IP DEL SERVER
        server = null;
        try {
            server = (ServerInterface) registro.lookup("Vaccino");
        } catch (NotBoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore con il collegamento al server.");
            System.exit(0);
        }
    }

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
