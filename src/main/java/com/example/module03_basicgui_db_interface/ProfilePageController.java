package com.example.module03_basicgui_db_interface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class ProfilePageController {

    /**
     * Public no-arg constructor required by FXMLLoader.
     */
    public ProfilePageController() {
    }

    /**
     * Reference to the main controller.
     */
    private DB_GUI_Controller mainController;

    @FXML
    private ImageView profilePictureImageView;

    /**
     * Setter to pass the main controller reference.
     *
     * @param controller The main controller instance.
     */
    public void setMainController(DB_GUI_Controller controller) {
        this.mainController = controller;
    }

    /**
     * Handles a click event on the profile image.
     * Opens a FileChooser so the user can select an image. The selected image
     * will be displayed in the profilePictureImageView.
     */
    @FXML
    private void handleImageClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Profile Picture");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        Stage stage = (Stage) profilePictureImageView.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            profilePictureImageView.setImage(image);
        }
    }

    /**
     * Handles the save action. Saves the new profile picture by updating the
     * main controller's ImageView with the selected image and closes the
     * current window.
     *
     * @param event The ActionEvent triggered by the save button.
     */
    @FXML
    private void handleSave(ActionEvent event) {
        if (profilePictureImageView.getImage() != null && mainController != null) {
            mainController.updateProfileImage(profilePictureImageView.getImage());
        }

        Stage stage = (Stage) profilePictureImageView.getScene().getWindow();
        stage.close();
    }
}
