package centrivaccinali;

import common.TipologiaCentro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CvCntrlCentro {
    @FXML
    private TextField txtNome, txtIndirizzoNome, txtIndirizzoNumero, txtIndirizzoComune, txtIndirizzoProvincia, txtIndirizzoCap;
    @FXML
    private RadioButton btnVia, btnViale, btnPiazza, btnAziendale, btnHub, btnOspedaliero;

    public void backFromCentroVaccinale(ActionEvent event) {
        // CHIUSURA DELLA VECCHIA FINESTRA
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();
        // APERTURA DELLA NUOVA FINESTRA
        new CvHomePage();
    }
    public void registraCentroVaccinale(ActionEvent event) {
        // NOME
        String name = txtNome.getText();
        // INDIRIZZO
        String addrTp = null;
        if(btnVia.isSelected()) {
            addrTp = btnVia.getText();
        } else if(btnViale.isSelected()) {
            addrTp = btnViale.getText();
        } else if (btnPiazza.isSelected()) {
            addrTp = btnPiazza.getText();
        }
        String addrNm = txtIndirizzoNome.getText();
        String addrNo = txtIndirizzoNumero.getText();
        String addrCm = txtIndirizzoComune.getText();
        String addrPr = txtIndirizzoProvincia.getText();
        String addrZc = txtIndirizzoCap.getText();
        StringBuilder sb = null;
        sb.append(addrTp);
        sb.append(addrNm);
        sb.append(addrNo);
        sb.append(addrCm);
        sb.append(addrPr);
        sb.append(addrZc);
        String addr = sb.toString();
        // TIPOLOGIA
        TipologiaCentro type = null;
        if(btnAziendale.isSelected()) {
            type = TipologiaCentro.AZIENDALE;
        } else if(btnHub.isSelected()) {
            type = TipologiaCentro.HUB;
        } else if(btnOspedaliero.isSelected()) {
            type = TipologiaCentro.OSPEDALIERO;
        }
        // System.out.println(name + addr + type.toString());

        /* COSTRUTTORE CENTRO VACCINALE: CentroVaccinale cv = new CentroVaccinale(String nome, String addr, TipologiaCentro type); */
        /* INVIO DATI A DATABASE */
    }
}
