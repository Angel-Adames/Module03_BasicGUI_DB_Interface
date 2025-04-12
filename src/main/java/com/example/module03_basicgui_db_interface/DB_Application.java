package com.example.module03_basicgui_db_interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import java.io.IOException;

public class DB_Application extends Application {

    public static void main(String[] args) {
        launch();
    }


    private Stage primaryStage;

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        showScene1();

    }

    private void showScene1() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("splash_screen.fxml"));
            Scene scene = new Scene(root, 960, 560);
            scene.getStylesheets().add(getClass().getResource("styling/style.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
            changeScene();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    FadeTransition fadeIn;
    public void changeScene() {
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("db_interface_gui.fxml"));

            Scene currentScene = primaryStage.getScene();
            Parent currentRoot = currentScene.getRoot();
            currentScene.getStylesheets().add(getClass().getResource("styling/style.css").toExternalForm());
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), currentRoot);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(e -> {
                newRoot.setOpacity(0);
                Scene newScene = new Scene(newRoot,960, 560);
                primaryStage.setScene(newScene);
                fadeIn.play();
            });
            fadeOut.play();
            fadeIn = new FadeTransition(Duration.seconds(2), newRoot);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}