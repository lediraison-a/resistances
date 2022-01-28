module com.almath.resistancesihm {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.almath.resistancesihm to javafx.fxml;
    exports com.almath.resistancesihm;
}
