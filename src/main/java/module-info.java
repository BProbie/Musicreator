module com.probie.musicreator {

    requires javafx.fxml;
    requires javafx.controls;

    requires java.desktop;

    requires static lombok;
    requires EasyDB;
    requires TarsosDSP.core;
    requires TarsosDSP.jvm;

    exports com.probie.musicreator;

    opens com.probie.musicreator to javafx.fxml;
    opens com.probie.musicreator.Musicreator to javafx.graphics;

}