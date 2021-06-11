module LabB {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    opens cittadini to javafx.fxml;
    exports cittadini;
    exports common;
}