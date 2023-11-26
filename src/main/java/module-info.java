module java1_2023_mel0102 {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.controls;

    opens me.mel0102;
    opens me.mel0102.Route.Routes to javafx.fxml;
    opens me.mel0102.Route;

    exports me.mel0102.Route.Routes;
}