module org.example.rmiexample {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.rmiexample to javafx.fxml;
    exports org.example.rmiexample;
}