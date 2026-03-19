module com.probie.musicreator {

    requires EasyDB;
    requires okhttp3;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.httpserver;
    requires javafx.controls;

    requires static lombok;

    exports com.probie.musicreator;

    opens com.probie.musicreator to javafx.fxml;
    opens com.probie.musicreator.Musicreator to javafx.graphics;

}