package com.almath.resistancesihm.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.almath.resistancesihm.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void runCalculer(ActionEvent event) {
        System.out.println("test");
    }

}
