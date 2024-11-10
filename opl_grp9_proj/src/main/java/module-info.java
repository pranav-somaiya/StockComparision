module com.example.opl_grp9_proj {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires okhttp3;
    requires com.fasterxml.jackson.databind;
    requires org.slf4j;
    requires java.desktop;


    opens com.example.opl_grp9_proj to javafx.fxml;


    exports com.example.opl_grp9_proj;
}
