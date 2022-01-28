package com.almath.resistancesihm.controllers;

import java.io.IOException;

import com.almath.resistancesihm.App;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}