package com.example.module03_basicgui_db_interface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.module03_basicgui_db_interface.db.DBConnDbOps;
import java.io.IOException;
import java.util.Objects;

/**
 * Controller for the login screen of the application.
 */
public class LoginScreenController {

    private static final String TEST_EMAIL = "example@gmail.com";
    private static final String TEST_PASSWORD = "password";

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;

    private final DBConnDbOps dbOps; // Instance for database operations

    /**
     * Constructor for the LoginScreenController.
     * Initializes the database operations and checks if the database is ready.
     */
    public LoginScreenController() {

        dbOps = new DBConnDbOps();
        boolean dbReady = dbOps.connectToDatabase();

        if (!dbReady) {
            System.err.println("Warning: Database might not be ready or contain users.");
        }
    }

    /**
     * Handles the login action when the user clicks the login button.
     * @param event The ActionEvent triggered by the login button.
     */
    @FXML
    private void handleLoginAction(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email == null || email.trim().isEmpty() || password == null || password.isEmpty()) {
            showStatus("Please enter both email and password.", true);
            return;
        }

        boolean isValid = false;

        /// --- START: Hardcoded Test Login Check --- (Remove or comment out for production)
        if (TEST_EMAIL.equalsIgnoreCase(email.trim()) && TEST_PASSWORD.equals(password)) {
            System.out.println("--- Using hardcoded test login ---");
            isValid = true;
        }
        /// --- END: Hardcoded Test Login Check ---

        if (!isValid) {
            isValid = dbOps.verifyUserLogin(email.trim(), password);
        }

        /// --- Process login result ---
        if (isValid) {
            showStatus("Login Successful!", false);
            System.out.println("Login successful for: " + email.trim());
            loadMainApplication();
            closeLoginWindow();
        } else {
            showStatus("Invalid email or password.", true);
            System.out.println("Login failed for: " + email.trim());
            /// Clear password field on failure for security
            passwordField.clear();
        }
    }

    /**
     * Displays a status message to the user.
     * @param message   The message to display.
     * @param isError   True if the message is an error, false otherwise.
     * This affects the styling if CSS is set up.
     */
    private void showStatus(String message, boolean isError) {
        statusLabel.setText(message);
        statusLabel.setVisible(true);
        if (isError) {
            statusLabel.getStyleClass().remove("success-message");
            statusLabel.getStyleClass().add("error-message");
        } else {
            statusLabel.getStyleClass().remove("error-message");
            statusLabel.getStyleClass().add("success-message");
        }
    }

    /**
     * Loads the main application window.
     * This method is called after a successful login.
     */
    private void loadMainApplication() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("db_interface_gui.fxml")));
            Parent mainRoot = loader.load();
            Stage mainStage = new Stage();
            mainStage.setTitle("Database Interface");
            mainStage.setScene(new Scene(mainRoot, 965, 560));
            mainStage.show();
        } catch (IOException e) {
            showStatus("Error loading main application.", true);
            System.err.println("Failed to load db_interface_gui.fxml: " + e.getMessage());
            e.printStackTrace();
        } catch (NullPointerException e) {
            showStatus("Error: Main application file not found.", true);
            System.err.println("Failed to find db_interface_gui.fxml. Check the file path/name.");
            e.printStackTrace();
        }
    }

    /**
     * Closes the current login window.
     * This method is called after the main application window is loaded.
     */
    private void closeLoginWindow() {
        /// Get the current stage (window) from any control on the scene
        Stage stage = (Stage) emailField.getScene().getWindow();
        if (stage != null) {
            stage.close();
        } else {
            System.err.println("Error: Could not get the stage to close the login window.");
        }
    }
}