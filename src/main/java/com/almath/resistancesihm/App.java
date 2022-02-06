package com.almath.resistancesihm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 800, 600);
        setUserAgentStylesheet(STYLESHEET_MODENA);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(getLocaleResource().getString("window.title"));
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setResources(getLocaleResource());
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static ResourceBundle getLocaleResource() {
        return ResourceBundle.getBundle("locales.resistances", Locale.getDefault());
    }
}