package com.example.module03_basicgui_db_interface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AboutPageController {

    public TextArea aboutPage;
    public VBox aboutPane;

    /**
    * Public no-argument constructor required by FXMLLoader.
    */
    public AboutPageController() {
    }

    /**
     * Handle the Close button click event.
     * Closes the About page window.
     * @param event The ActionEvent that triggered this method.
     */
    @FXML
    private void handleClose(ActionEvent event) {
        /// Get a reference to the stage (window) and close it.
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}