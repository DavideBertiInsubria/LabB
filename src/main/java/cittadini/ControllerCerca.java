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
 * La classe <em>ControllerCerca</em> rappresenta il <i>controller</i> dell'interfaccia grafica della schermata <i>'Cerca'</i> dell'applicazione 'cittadini', sviluppato con JavaFX.
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

    /**
     * Il metodo <em>azzeraFiltro</em> si occupa di aggiungere tutti i centri esistenti nella lista di visualizzazione nella schermata <i>'Cerca'</i> dell'applicazione 'cittadini' (e quindi di azzerare i filtri applicati).
     */
    private void azzeraFiltro() throws RemoteException {
        listCentriVacc.getItems().clear();
        listaCentriVaccinaliVisualizzati.clear();
        // SET LISTA
        listaCentriVaccinaliVisualizzati = server.cercaCentroVaccinale("", "", "");
        for (CentroVaccinale centroVaccinale : listaCentriVaccinaliVisualizzati) {
            listCentriVacc.getItems().add(centroVaccinale.getNome() + " - " + centroVaccinale.getIndirizzo());
        }
    }

    /**
     * Il metodo <em>clickAzzeraFiltro</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Azzera filtro</i> nella schermata <i>'Cerca'</i> dell'applicazione 'cittadini'.
     * Richiama il metodo <i>azzeraFiltro</i>
     */
    public void clickAzzeraFiltro(ActionEvent event) throws RemoteException {
        azzeraFiltro();
    }
    /**
     * Il metodo <em>clickCerca</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Cerca</i> nella schermata <i>'Cerca'</i> dell'applicazione 'cittadini'.
     * Vengono applicati tutti i filtri compilati ed inseriti nei campi di ricerca dell'interfaccia grafica e viene aggiornata la lista <i>listaCentriVaccinaliVisualizzati</i>.
     */
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

    /**
     * Il metodo <em>clickVisualizza</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Visualizza</i> nella schermata <i>'Cerca'</i> dell'applicazione 'cittadini'.
     * Viene aperta la schermata di visualizzazione delle informazioni del centro vaccinale che è stato selezionato della lista (ControllerVisualizza).
     * @see ControllerVisualizza
     */
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

    /**
     * Il metodo <em>setDati</em> serve per fornire alla schermata <i>'cerca'</i> tutti i dati e le informazioni occorrenti dalle altre schermate di interfaccia grafica.
     * Come l'utente loggato e il collegamento al server.
     */
    public void setDati(Cittadino user, ServerInterface s) throws RemoteException {
        server = s;
        User = user;
        azzeraFiltro();
    }

    /**
     * Il metodo <em>clickIndietro</em> &egrave; l'evento che si verifica nel momento in cui viene schiacciato il bottone <i>Indietro</i> nella schermata <i>'Cerca'</i> dell'applicazione 'cittadini'.
     * Viene riportato alla schermata <i>Home</i> (ControllerHome).
     * @see ControllerHome
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
            cc.setDati(User, server);
            schermata.setTitle("Vaccinazione cittadini");
            schermata.setScene(new Scene(root));
            schermata.show();
        } catch (IOException ignored){}
    }

}