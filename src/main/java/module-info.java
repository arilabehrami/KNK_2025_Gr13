module com.example.knk_2025_gr {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.compiler;


    opens com.example.knk_2025_gr13 to javafx.fxml;
    exports com.example.knk_2025_gr13;


    opens Main to javafx.graphics, javafx.fxml;
    opens controllers to javafx.fxml;
    opens Views to javafx.fxml;

    exports Main;
    exports models.domain;

}


