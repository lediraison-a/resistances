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

    /**
     * The constant scene.
     */
    private static Scene scene;

    /**
     * Start the application.
     *
     * @param stage the stage
     * @throws IOException the io exception
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 800, 600);
        setUserAgentStylesheet(STYLESHEET_MODENA);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(getLocaleResource().getString("window.title"));
        stage.show();
    }

    /**
     * Load fxml parent.
     *
     * @param fxml the fxml
     * @return the parent
     * @throws IOException the io exception
     */
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setResources(getLocaleResource());
        return fxmlLoader.load();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Gets locale resource for default locale.
     *
     * @return the locale resource
     */
    public static ResourceBundle getLocaleResource() {
        // help from stackoverflow
        // https://stackoverflow.com/questions/41303795/javafx-changing-locale-in-whole-application
        try {
            return ResourceBundle.getBundle("locales.resistances", Locale.getDefault());
        } catch (NullPointerException e) {
            return ResourceBundle.getBundle("locales.resistances_en");
        }
    }
}