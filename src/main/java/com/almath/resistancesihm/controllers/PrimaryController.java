package com.almath.resistancesihm.controllers;

import com.almath.resistancesihm.App;
import com.almath.resistancesihm.models.Anneau;
import com.almath.resistancesihm.utils.CalculResistance;
import com.almath.resistancesihm.utils.Constantes.ConvertData;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Objects;

public class PrimaryController {

    // https://github.com/joffrey-bion/javafx-themes/blob/master/css/modena_dark.css
    private static final String DARKSTYLE = "/styles/Modena_dark.css";

    private double valeurOhm;
    private boolean appThemeDark;

    @FXML
    private TextField labelCalculer;
    @FXML
    private ComboBox<String> comboxConvert;
    @FXML
    private Parent rootPane, colorSelect;
    @FXML
    public ColorSelectController colorSelectController;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void runCalculer(ActionEvent event) {
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

    @FXML
    private void switchThemeMode(ActionEvent event) {
        var darkStyle = Objects.requireNonNull(getClass().getResource(DARKSTYLE)).toExternalForm();
        boolean isDark = Objects.equals(((MenuItem) event.getSource()).getText(), "Sombre");
        if(isDark != appThemeDark) {
            appThemeDark = isDark;
            if(isDark) {
                rootPane.getStylesheets().add(darkStyle);
            } else {
                rootPane.getStylesheets().remove(darkStyle);
            }
        }
    }

    @FXML
    public void onChangeComboxConvertion(ActionEvent actionEvent) {
        var valeurCombox = comboxConvert.getValue(); // la valeur de la combox box après avoir été modifée (le texte)
        var valeurPuissance = ConvertData.CONVERT_DATA.get(valeurCombox); // la valeur de la puissance
        var newValue = valeurOhm * Math.pow(10, valeurPuissance);
        var newText = String.format("%e", newValue); // notation scientifique
        labelCalculer.setText(newText);
    }

    public void initialize() {
        valeurOhm = 0;
        appThemeDark = false;
        var lst = new ArrayList<>(ConvertData.CONVERT_DATA.keySet());
        lst.sort(Comparator.comparingInt(ConvertData.CONVERT_DATA::get));
        comboxConvert.setItems(FXCollections.observableArrayList(lst));
        comboxConvert.getSelectionModel().select(3);
    }

    public void exportAsPng(ActionEvent event) {
        Scene currentScene = rootPane.getScene();
        String date = new SimpleDateFormat("dd-MM-yy-h:mm").format(Calendar.getInstance().getTime());
        String filename = colorSelectController.getNomResistance() + date + ".png";
        File imageFile = getFileChooser(filename).showSaveDialog((Stage) rootPane.getScene().getWindow());
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

    private FileChooser getFileChooser(String filename) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir le fichier d'export");
        fileChooser.setInitialFileName(filename);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        return fileChooser;
    }

    public void quitterApp(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void visiterAide(ActionEvent actionEvent) {

    }
}
