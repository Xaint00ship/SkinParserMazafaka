module com.example.skinsparsermazafaka {
    requires javafx.controls;
    requires javafx.fxml;
    requires poi;


    opens com.example.skinsparsermazafaka to javafx.fxml;
    exports com.example.skinsparsermazafaka;
    exports parser.module;
    opens parser.module to javafx.fxml;
}