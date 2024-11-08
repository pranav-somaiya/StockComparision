module com.example.opl_grp9_proj {
    // JavaFX modules
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    // External libraries
    requires okhttp3;      // OkHttp3 for HTTP requests
    requires com.fasterxml.jackson.databind;     // JSON processing (if using this library)
    requires org.slf4j;
    requires java.desktop;    // SLF4J API for logging (optional)

    // Allow JavaFX to access your FXML and controller classes
    opens com.example.opl_grp9_proj to javafx.fxml;

    // Exports your main package for other modules (optional)
    exports com.example.opl_grp9_proj;
}
