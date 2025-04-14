package com.example.module03_basicgui_db_interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class DB_Application extends Application {

    /**
     * all elements for splash, transition, login, scene size, css styling, and .fxml files
     */
    private static final double SPLASH_VISIBLE_SECONDS = 2.0;
    private static final double FADE_TRANSITION_SECONDS = 1.0;
    private static final double LOGIN_SCENE_WIDTH = 393;
    private static final double LOGIN_SCENE_HEIGHT = 353;
    public static final double MAIN_SCENE_WIDTH = 965;
    public static final double MAIN_SCENE_HEIGHT = 560;
    private static final String SPLASH_FXML = "splash_screen.fxml";
    private static final String INITIAL_FXML = "login_page.fxml";
    private static final String CSS_PATH = "styling/style.css";
    private Stage primaryStage;
    private String s;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the JavaFX application.
     * @param primaryStage The primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        showSplashScreen();
    }

    /**
     * Displays the splash screen.
     */
    private void showSplashScreen() {
        try {
            FXMLLoader splashLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(SPLASH_FXML)));
            Parent splashRoot = splashLoader.load();
            // Use login dimensions for splash if desired, or keep separate ones
            Scene splashScene = new Scene(splashRoot, LOGIN_SCENE_WIDTH, LOGIN_SCENE_HEIGHT);
            applyStylesheet(splashScene);

            primaryStage.setScene(splashScene);
            primaryStage.setTitle("Loading Application...");
            primaryStage.show();

            PauseTransition splashPause = new PauseTransition(Duration.seconds(SPLASH_VISIBLE_SECONDS));
            /// Transition to the LOGIN screen after splash
            splashPause.setOnFinished(event -> loadAndFadeToLoginScreen(splashRoot));
            splashPause.play();

        } catch (IOException e) {
            handleFatalError("Error loading splash screen FXML (" + SPLASH_FXML + ")", e);
        } catch (NullPointerException e) {
            handleFatalError("Could not find splash screen FXML (" + SPLASH_FXML + ")", e);
        } catch (Exception e) {
            handleFatalError("An unexpected error occurred during splash screen setup", e);
        }
    }

    /**
     * Loads and transitions to the login screen with a fade effect.
     * @param splashRoot The root node of the splash screen.
     */
    private void loadAndFadeToLoginScreen(Parent splashRoot) {
        try {
            /// Load the LOGIN FXML
            FXMLLoader initialLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(INITIAL_FXML)));
            Parent initialRoot = initialLoader.load();
            initialRoot.setOpacity(0.0); // Start faded out

            /// Use LOGIN dimensions for the scene
            Scene initialScene = new Scene(initialRoot, LOGIN_SCENE_WIDTH, LOGIN_SCENE_HEIGHT);
            applyStylesheet(initialScene);

            /// Fade out splash screen
            FadeTransition fadeOut = getFadeTransition(splashRoot, initialScene, initialRoot);
            fadeOut.play();

        } catch (IOException e) {
            handleFatalError("Error loading initial screen FXML (" + INITIAL_FXML + ")", e);
        } catch (NullPointerException e) {
            handleFatalError("Could not find initial screen FXML (" + INITIAL_FXML + ")", e);
        } catch (Exception e) {
            handleFatalError("An unexpected error occurred during transition to initial screen", e);
        }
    }

    /**
     * Creates and configures a fade transition.
     * @param splashRoot The root node of the splash screen.
     * @param initialScene The scene of the initial screen.
     * @param initialRoot The root node of the initial screen.
     * @return The configured FadeTransition.
     */
    private FadeTransition getFadeTransition(Parent splashRoot, Scene initialScene, Parent initialRoot) {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(FADE_TRANSITION_SECONDS), splashRoot);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(e -> {
            /// Set the LOGIN scene
            primaryStage.setScene(initialScene);
            primaryStage.setTitle("Login"); // Set login title

            /// Fade in login screen
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(FADE_TRANSITION_SECONDS), initialRoot);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });
        return fadeOut;
    }

    /**
     * Applies the stylesheet to the given scene.
     * @param scene The scene to apply the stylesheet to.
     */
    private void applyStylesheet(Scene scene) {
        URL cssUrl = getClass().getResource(DB_Application.CSS_PATH);
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        } else {
            System.err.println("Warning: Could not find stylesheet at path: " + DB_Application.CSS_PATH);
        }
    }

    /**
     * Handles fatal errors by printing an error message, stack trace, and showing an alert.
     * @param message The error message.
     * @param e The exception that occurred.
     */
    private void handleFatalError(String message, Exception e) {
        System.err.println("FATAL: " + message + ": " + e.getMessage());
        e.printStackTrace();
        showAlert();
    }

    /**
     * Displays an alert indicating a fatal error and exits the application.
     */
    private void showAlert() {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Fatal Error");
        alert.setHeaderText(null);
        alert.setContentText("An Error Occurred - Application Will Exit");
        alert.showAndWait();
        System.exit(1);
    }
}