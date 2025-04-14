package com.example.module03_basicgui_db_interface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class SplashScreenController {

    /**
     * Public no-argument constructor required by FXMLLoader.
     */
    public SplashScreenController() {
    }

    /**
     * Handles the splash screen by getting imageView for the main application
     * @param event handles the image for the main scene
     */
    @FXML
    void clickMe(MouseEvent event) {
        Parent newRoot;
        Scene scene= ((ImageView)event.getSource()).getParent().getScene();

        try {
            newRoot = FXMLLoader.load(getClass().getResource("db_interface_gui.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        scene.setRoot(newRoot);
    }
}