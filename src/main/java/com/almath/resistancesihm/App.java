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

    private Locale lang;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary", lang), 800, 600);
        setUserAgentStylesheet(STYLESHEET_MODENA);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(getLocaleResource(lang).getString("window.title"));
        stage.show();
    }

    public void startLang(Stage stage, Locale lang) throws IOException {
        this.lang = lang;
        start(stage);
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml, null));
    }

    private static Parent loadFXML(String fxml, Locale lang) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setResources(getLocaleResource(lang));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static ResourceBundle getLocaleResource(Locale lang) {
        return ResourceBundle.getBundle("locales.app", lang == null ? Locale.getDefault() : lang);
    }
}