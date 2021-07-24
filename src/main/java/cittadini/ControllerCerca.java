package cittadini;

import common.CentroVaccinale;
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
import server.ServerInterface;
import javax.swing.*;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/** @author Invanov Aleksandar Evgeniev, Mazza Serghej, Berti Davide, Rizzi Silvio
 * La classe <em>ControllerCerca</em> rappresenta il <i>controller</i> della schermata <i>'cerca'</i> dell'applicazione 'cittadini'.
 */
public class ControllerCerca {

    /**
     * <code>User</code> &egrave; il riferimento all'utente loggato, ed è un oggetto di tipo <i>Cittadino</i>.
     * Se l'utente non è loggato allora <i>User</i> &egrave; uguale a <b>null</b>.
     * @see Cittadino
     */
    private Cittadino User;
    /**
     * <code>listaCentriVaccinaliVisualizzati</code> &egrave; la lista di oggetti di tipo CentroVaccinali.
     * Rappresenta la lista di centri vaccinali che vengono visualizzati nella schermata di ricerca.
     * @see CentroVaccinale
     */
    private ArrayList<CentroVaccinale> listaCentriVaccinaliVisualizzati = new ArrayList<CentroVaccinale>();
    /**
     * <code>server</code> &egrave; il riferimento al server.
     * @see ServerInterface
     */
    private ServerInterface server;

    @FXML
    TextField textNome, textComune;
    @FXML
    ListView<String> listCentriVacc;
    @FXML
    ComboBox<String> comboTipo;

    @FXML
    public void initialize() {
        comboTipo.getItems().addAll("qualsiasi","ospedaliero","aziendale","hub");
        comboTipo.setValue("qualsiasi");
    }

    private void azzeraFiltro() throws RemoteException {
        listCentriVacc.getItems().clear();
        listaCentriVaccinaliVisualizzati.clear();
        // SET LISTA
        listaCentriVaccinaliVisualizzati = server.cercaCentroVaccinale("", "", "");
        for (CentroVaccinale centroVaccinale : listaCentriVaccinaliVisualizzati) {
            listCentriVacc.getItems().add(centroVaccinale.getNome() + " - " + centroVaccinale.getIndirizzo());
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
        if (comboTipo.getValue().equals("qualsiasi"))
            Tipo = "";
        else
            Tipo = comboTipo.getValue();
        // SET LISTA
        listaCentriVaccinaliVisualizzati = server.cercaCentroVaccinale(Nome, Comune, Tipo);
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
        azzeraFiltro();
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