module com.generatore.progettocalcio {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;
    requires java.management;

    opens com.generatore.progettocalcio to javafx.fxml;
    opens com.generatore.progettocalcio.model to javafx.fxml, com.google.gson;
    opens com.generatore.progettocalcio.controller to javafx.fxml;

    exports com.generatore.progettocalcio;
    exports com.generatore.progettocalcio.model;
    exports com.generatore.progettocalcio.controller;
}
