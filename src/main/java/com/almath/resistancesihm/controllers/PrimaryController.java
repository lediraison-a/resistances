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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
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

public class PrimaryController implements Initializable {

    // https://github.com/joffrey-bion/javafx-themes/blob/master/css/modena_dark.css
    private static final String DARKSTYLE = "/styles/Modena_dark.css";
    private static final String PAGE_AIDE = "https://git.alediraison.com/firnen/resistances/src/branch/master/HELP.md";

    private double valeurOhm;
    private ResourceBundle resourceBundle;
    private Map<MenuItem, Locale> languages;

    @FXML
    private TextField labelCalculer;
    @FXML
    private ComboBox<String> comboxConvert;
    @FXML
    private Parent rootPane, colorSelect;
    @FXML
    public ColorSelectController colorSelectController;
    @FXML
    private MenuItem menuDark, menuLight, menuLangFr, menuLangEn;

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
    }

    public void runCalculer(ActionEvent event) {
        var n1 = colorSelectController.<Integer>getComboxValue(Anneau.N1);
        var n2 = colorSelectController.<Integer>getComboxValue(Anneau.N2);
        var n3 = colorSelectController.<Integer>getComboxValue(Anneau.N3);
        var multiplier = colorSelectController.<Integer>getComboxValue(Anneau.MULT);
        var resistance = CalculResistance.getResistance(n1, n2, n3, multiplier);
        // var tolerance = colorSelectController.<Double>getComboxValue(Anneau.TOLER);
        // var temp = colorSelectController.<Integer>getComboxValue(Anneau.TEMP);

        // System.out.printf("\n resistance : %f", resistance);
        // System.out.printf("\n tolerance : %f", tolerance);

        comboxConvert.getSelectionModel().select(3);
        labelCalculer.setText(String.format("%.2f", resistance));
        valeurOhm = resistance;
    }

    public void switchThemeMode(ActionEvent event) {
        boolean isDark = Objects.equals(event.getSource(), menuDark);
        setTheme(isDark);
    }

    public void onChangeComboxConvertion(ActionEvent actionEvent) {
        var valeurCombox = comboxConvert.getValue(); // la valeur de la combox box après avoir été modifée (le texte)
        var valeurPuissance = ConvertData.CONVERT_DATA.get(valeurCombox); // la valeur de la puissance
        var newValue = valeurOhm * Math.pow(10, valeurPuissance);
        var newText = String.format("%e", newValue); // notation scientifique
        labelCalculer.setText(newText);
    }

    public void exportAsPng(ActionEvent event) {
        Scene currentScene = rootPane.getScene();
        String date = new SimpleDateFormat("ddMMyy-h:mm").format(Calendar.getInstance().getTime());
        String filename = colorSelectController.getNomResistance() + "-" + date + ".png";
        File imageFile = getFileChooser(filename).showSaveDialog(rootPane.getScene().getWindow());
        if(imageFile != null) {
            try {
                WritableImage writableImage = currentScene.snapshot(null);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", imageFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void quitterApp(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void visiterAide(ActionEvent actionEvent) throws IOException {

//        Stage popupwindow=new Stage();
//
//        popupwindow.initModality(Modality.WINDOW_MODAL);
//        popupwindow.setTitle("This is a pop up window");
//
//        Label label1= new Label("Pop up window now displayed");
//
//        Button button1= new Button("Close this pop up window");
//
//
//        VBox layout= new VBox(10);
//
//        // layout.getChildren().add(label1, button1);
//        layout.setAlignment(Pos.CENTER);
//        Scene scene1= new Scene(App.loadFXML("helpView"), 800, 600);
//        popupwindow.setScene(scene1);
//        popupwindow.setResizable(false);
//        popupwindow.showAndWait();
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(PAGE_AIDE));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeLocal(ActionEvent actionEvent) {
        Locale.setDefault(languages.get((MenuItem) actionEvent.getSource()));
        ((Stage) rootPane.getScene().getWindow()).close();
        Platform.runLater(() -> {
            try {
                new App().start(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setTheme(boolean isDark) {
        var darkStyle = Objects.requireNonNull(getClass().getResource(DARKSTYLE)).toExternalForm();
        if(isDark) {
            rootPane.getStylesheets().add(darkStyle);
        } else {
            rootPane.getStylesheets().remove(darkStyle);
        }
        menuLight.setDisable(!isDark);
        menuDark.setDisable(isDark);
    }

    private void setLangMenu() {
        languages.forEach((menuItem, locale) -> {
            if(Objects.equals(Locale.getDefault().getLanguage(), locale.getLanguage())) {
                menuItem.setDisable(true);
            }
        });
    }

    private FileChooser getFileChooser(String filename) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(resourceBundle.getString("file_export.title"));
        fileChooser.setInitialFileName(filename);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        return fileChooser;
    }

}
