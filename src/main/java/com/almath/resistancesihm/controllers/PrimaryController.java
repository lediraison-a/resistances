package com.almath.resistancesihm.controllers;

import com.almath.resistancesihm.App;
import com.almath.resistancesihm.models.Anneau;
import com.almath.resistancesihm.utils.CalculResistance;
import com.almath.resistancesihm.utils.Constantes.ConvertData;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The type Primary controller.
 * Main controller of the application
 */
public class PrimaryController implements Initializable {

// https://github.com/joffrey-bion/javafx-themes/blob/master/css/modena_dark.css
    private static final String DARKSTYLE = "/styles/Modena_dark.css";

    private static String PAGE_AIDE;

    /**
     * The Valeur ohm.
     * Used to get the current value in Ohm at anytime
     */
    private double valeurOhm;

    private ResourceBundle resourceBundle;

    private Map<MenuItem, Locale> languages;

    @FXML
    private TextField labelCalculer;

    @FXML
    private ComboBox<String> comboxConvert;

    @FXML
    private Parent rootPane,
            colorSelect;

    @FXML
    public ColorSelectController colorSelectController;

    @FXML
    private MenuItem menuDark,
            menuLight,
            menuLangFr,
            menuLangEn;

    /**
     * Initialize.
     * Initialize the Scene by setting the language, the theme,
     * and the values of the resistor.
     *
     * @param url            the url
     * @param resourceBundle the resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        languages = new HashMap<>() {{
            put(menuLangFr, Locale.FRENCH);
            put(menuLangEn, Locale.US);
        }};
        this.resourceBundle = resourceBundle;
        valeurOhm = 0;
        setTheme(false);
        setLangMenu();
        var lst = new ArrayList<>(ConvertData.CONVERT_DATA.keySet());
        lst.sort(Comparator.comparingInt(ConvertData.CONVERT_DATA::get));
        comboxConvert.setItems(FXCollections.observableArrayList(lst));
        comboxConvert.getSelectionModel().select(3);
        PAGE_AIDE = resourceBundle.getString("menubar.menu_helpage_link");
    }

    /**
     * Run calculer.
     * Called when clicking on the "compute" button.
     * Sets the text of the result field to the calculated value of the resistor.
     *
     * @param event the event
     */
    public void runCalculer(ActionEvent event) {
        var n1 = colorSelectController.<Integer>getComboxValue(Anneau.N1);
        var n2 = colorSelectController.<Integer>getComboxValue(Anneau.N2);
        var n3 = colorSelectController.<Integer>getComboxValue(Anneau.N3);
        var multiplier = colorSelectController.<Integer>getComboxValue(Anneau.MULT);
        var resistance = CalculResistance.getResistance(n1, n2, n3, multiplier);

        comboxConvert.getSelectionModel().select(3);
        labelCalculer.setText(String.format("%.2f", resistance));
        valeurOhm = resistance;
    }

    /**
     * Switch theme mode between light and dark.
     *
     * @param event the event
     */
    public void switchThemeMode(ActionEvent event) {
        boolean isDark = Objects.equals(event.getSource(), menuDark);
        setTheme(isDark);
    }

    /**
     * On change combox convertion.
     * Called when choosing a value from the convertor combobox
     * Display the value in the chosen unit
     *
     * @param actionEvent the action event
     */
    public void onChangeComboxConvertion(ActionEvent actionEvent) {
        var valeurCombox = comboxConvert.getValue(); // la valeur de la combox box après avoir été modifée (le texte)
        var valeurPuissance = ConvertData.CONVERT_DATA.get(valeurCombox); // la valeur de la puissance
        var newValue = valeurOhm * Math.pow(10, valeurPuissance);
        var newText = String.format("%e", newValue); // notation scientifique
        labelCalculer.setText(newText);
    }

    /**
     * Export as png.
     * Called when selecting "export as png" in "File" in the menu bar.
     * Saves a screenshot of the current state of the application.
     * Opens a file chooser to select the name of the saved file and its location.
     * Default value for the filename is the current date and the abbreviation of the color used.
     *
     * @param event the event
     */
    public void exportAsPng(ActionEvent event) {
        // set the file
        Scene currentScene = rootPane.getScene();
        String date = new SimpleDateFormat("ddMMyy-h:mm").format(Calendar.getInstance().getTime());
        String filename = colorSelectController.getNomResistance() + "-" + date + ".png";
        File imageFile = getFileChooser(filename).showSaveDialog(rootPane.getScene().getWindow());
        // Create the preview and export
        if (imageFile != null) {
            try {
                WritableImage writableImage = currentScene.snapshot(null);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", imageFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Close app.
     * Close the app
     * Called when selecting "Exit" in the menu bar
     *
     * @param actionEvent the action event
     */
    public void closeApp(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * Open help page.
     * Opens a help page describing how to use the app.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void openHelpPage(ActionEvent actionEvent) throws IOException {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(PAGE_AIDE));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Change local.
     * Switch from a language to another by clicking on the language toolbar on the menu.
     * set default language.
     * Restart the app
     *
     * @param actionEvent the action event
     */
    public void changeLocal(ActionEvent actionEvent) {
        Locale.setDefault(languages.get((MenuItem) actionEvent.getSource()));
        // restart app
        ((Stage) rootPane.getScene().getWindow()).close();
        Platform.runLater(() -> {
            try {
                new App().start(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Set theme.
     * Set the current between light and dark
     *
     * @param isDark boolean value representing the current theme
     */
    private void setTheme(boolean isDark) {
        var darkStyle = Objects.requireNonNull(getClass().getResource(DARKSTYLE)).toExternalForm();
        // add or remove the dark stylesheet
        if (isDark) {
            rootPane.getStylesheets().add(darkStyle);
        } else {
            rootPane.getStylesheets().remove(darkStyle);
        }
        // set the menu disable on the selected theme
        menuLight.setDisable(!isDark);
        menuDark.setDisable(isDark);
    }

    /**
     * Sets lang menu.
     * Sets the current language according the locale default
     */
    private void setLangMenu() {
        languages.forEach((menuItem, locale) -> {
            if (Objects.equals(Locale.getDefault().getLanguage(), locale.getLanguage())) {
                menuItem.setDisable(true);
            }
        });
    }

    /**
     * Get file chooser.
     * <p>
     * Initialize the file chooser.
     * Sets the default filename and directory
     *
     * @param filename the name of the file to be saved
     * @return the file chooser being initialized
     */
    private FileChooser getFileChooser(String filename) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(resourceBundle.getString("file_export.title"));
        fileChooser.setInitialFileName(filename);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        return fileChooser;
    }

}
