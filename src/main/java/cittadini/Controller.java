package cittadini;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    @FXML
    Button buttonProva;
    @FXML
    Label lb;

    private int i=0;
    public void clickButton(){
        if (i==0) {
            lb.setText("P I N G .");
            i++;
        } else{
            lb.setText("P O N G .");
            i--;
        }
    }

}