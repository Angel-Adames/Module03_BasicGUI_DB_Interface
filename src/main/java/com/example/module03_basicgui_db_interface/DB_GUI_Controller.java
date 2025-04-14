package com.example.module03_basicgui_db_interface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DB_GUI_Controller implements Initializable {

    /**
     * Observable list to store the Person data.
     */
    private final ObservableList<Person> data =
            FXCollections.observableArrayList();

    @FXML
    javafx.scene.control.Button addBtn, profileBtn;
    @FXML
    javafx.scene.control.MenuItem aboutMenuItem, profileMenuItem;
    @FXML
    TextField first_name, last_name, department, major, course, studentYear;
    @FXML
    private TableView<Person> tv;
    @FXML
    private TableColumn<Person, Integer> tv_id = new TableColumn<>();
    @FXML
    private TableColumn<Person, String> tv_fn = new TableColumn<>(), tv_ln, tv_dept, tv_major, tv_course, tv_sy;

    @FXML
    ImageView img_view;

    /**
     * Default constructor for DB_GUI_Controller.
     */
    public DB_GUI_Controller() {}

    /**
     * Constructor for DB_GUI_Controller with a theme toggle button.
     *
     * @param themeToggleBtn The button used to toggle the theme.
     */
    public DB_GUI_Controller(Button themeToggleBtn) {
        super();
        this.themeToggleBtn = themeToggleBtn;
        DB_GUI_Controller instance = this;
    }

    @FXML
    private Button themeToggleBtn;

    private static final KeyCombination SHORTCUT_ADD = new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN);
    private static final KeyCombination SHORTCUT_EDIT = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);
    private static final KeyCombination SHORTCUT_DELETE = new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN);
    private static final KeyCombination SHORTCUT_CLEAR = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);

    /**
     * Flag to keep track of the current theme.
     */
    private boolean darkThemeEnabled = false;

    /**
     * Handles the toggle button to change the theme.
     */
    @FXML
    private void handleThemeToggle() {
        /// Get a reference to the current scene
        Scene scene = themeToggleBtn.getScene();

        if (scene == null) {
            return;
        }

        /// Clear all the stylesheets for simplicity,
        /// then add either the default or dark theme stylesheet.
        scene.getStylesheets().clear();

        if (darkThemeEnabled) {
            /// Switch to default theme. For example, if your default stylesheet is style.css:
            scene.getStylesheets().add(getClass().getResource("styling/style.css").toExternalForm());
            themeToggleBtn.setText("☾");
        } else {
            /// Switch to dark theme
            scene.getStylesheets().add(getClass().getResource("styling/dark_theme.css").toExternalForm());
            themeToggleBtn.setText("☼");
        }
        /// Toggle the flag
        darkThemeEnabled = !darkThemeEnabled;
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tv_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tv_fn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tv_ln.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tv_dept.setCellValueFactory(new PropertyValueFactory<>("dept"));
        tv_major.setCellValueFactory(new PropertyValueFactory<>("major"));
        tv_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        tv_sy.setCellValueFactory(new PropertyValueFactory<>("studentYear"));

        tv.setItems(data);
    }

    /**
     * Adds a new record to the table view.
     */
    @FXML
    protected void addNewRecord() {

        data.add(new Person(
                data.size()+1,
                first_name.getText(),
                last_name.getText(),
                department.getText(),
                major.getText(),
                course.getText(),
                studentYear.getText()
        ));
    }

    /**
     * Clears the input fields in the form.
     */
    @FXML
    protected void clearForm() {
        first_name.clear();
        last_name.setText("");
        department.setText("");
        major.setText("");
        course.setText("");
        studentYear.setText("");
    }

    /**
     * Closes the application.
     */
    @FXML
    protected void closeApplication() {
        System.exit(0);
    }

    /**
     * Edits the selected record in the table view.
     */
    @FXML
    protected void editRecord() {
        Person p= tv.getSelectionModel().getSelectedItem();
        int c=data.indexOf(p);
        Person p2= new Person();
        p2.setId(c+1);
        p2.setFirstName(first_name.getText());
        p2.setLastName(last_name.getText());
        p2.setDept(department.getText());
        p2.setMajor(major.getText());
        p2.setCourse(course.getText());
        p2.setStudentYear(studentYear.getText());
        data.remove(c);
        data.add(c,p2);
        tv.getSelectionModel().select(c);
    }

    /**
     * Deletes the selected record from the table view.
     */
    @FXML
    protected void deleteRecord() {
        Person p= tv.getSelectionModel().getSelectedItem();
        data.remove(p);
    }
    /**
     * Opens a file chooser to select an image and displays it in the image view.
     */

    @FXML
    protected void showImage() {
        File file= (new FileChooser()).showOpenDialog(img_view.getScene().getWindow());
        if(file!=null){
            img_view.setImage(new Image(file.toURI().toString()));

        }
    }

    /**
     * Populates the form fields with the data of the selected item in the table view.
     *
     * @param mouseEvent The mouse event that triggered this action.
     */
    @FXML
    protected void selectedItemTV(MouseEvent mouseEvent) {
        Person p = tv.getSelectionModel().getSelectedItem();
        if (p != null) {
            assert p.getFirstName() != null;
            first_name.setText(p.getFirstName());
            last_name.setText(p.getLastName());
            department.setText(p.getDept());
            major.setText(p.getMajor());
            course.setText(p.getCourse());
            studentYear.setText(p.getStudentYear());
        }
    }

    /**
     * Opens the profile page in a new window.
     */
    @FXML
    private void openProfilePage() {
        System.out.println("Profile button clicked!");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile_page.fxml"));
            Parent root = fxmlLoader.load();

            /// Retrieve the correct controller (ProfilePageController) from the loader.
            ProfilePageController profileController = fxmlLoader.getController();
            /// Pass the current main controller instance to the profile page controller.
            profileController.setMainController(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Edit Profile");
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the about page in a new window.
     */
    @FXML
    protected void aboutPage() {
        System.out.println("About button clicked!");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("about_page.fxml"));
            Parent aboutRoot = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(aboutRoot));
            stage.setTitle("About");
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            System.err.println("Failed to Open About Page:");
            e.printStackTrace();
        }
    }

    /**
     * Updates the profile image in the image view.
     *
     * @param image The new image to display.
     */
    public void updateProfileImage(Image image) {
        img_view.setImage(image);
    }

    /**
     * Handles keyboard shortcuts for adding, editing, deleting, and clearing records.
     * @param event The key event that triggered this action.
     */
    @FXML
    public void keyboardShortcutController(KeyEvent event) {

        if (SHORTCUT_ADD.match(event)) {
            System.out.println("Ctrl+A detected");
            addNewRecord();
            event.consume(); /// Consume the event to prevent further processing
        } else if (SHORTCUT_EDIT.match(event)) {
            System.out.println("Ctrl+E detected");
            editRecord();
            event.consume();
        } else if (SHORTCUT_DELETE.match(event)) {
            System.out.println("Ctrl+D detected");
            deleteRecord();
            event.consume();
        } else if (SHORTCUT_CLEAR.match(event)) {
            System.out.println("Ctrl+C detected");
            clearForm();
            event.consume();
        }
    }
        /**
         * Handles the action of clicking the 'Login' menu item.
         * Opens the login screen in a new window.
         */
        @FXML
        private void openLoginPage() {
            try {
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("login_page.fxml")));
                Parent loginRoot = loader.load();

                Stage loginStage = new Stage();
                loginStage.setTitle("Login");
                loginStage.initModality(Modality.APPLICATION_MODAL);
                loginStage.initStyle(StageStyle.UTILITY);
                loginStage.setResizable(false); /// Match the behavior from DB_Application

                Scene loginScene = new Scene(loginRoot, 393, 353); // Using dimensions from DB_Application
                URL cssUrl = getClass().getResource("styling/style.css");

                if (cssUrl != null) {
                    loginScene.getStylesheets().add(cssUrl.toExternalForm());
                } else {
                    System.err.println("Warning: Could not find stylesheet for login page.");
                }

                loginStage.setScene(loginScene);

                /// Show the login window and wait for it to be closed if modal, otherwise just show
                loginStage.show();
                Stage currentStage = (Stage) addBtn.getScene().getWindow();
                currentStage.hide();

            } catch (IOException e) {
                System.err.println("Error loading login page: " + e.getMessage());
                e.printStackTrace();
                // Optionally show an error alert to the user
                showAlert("Error", "Could not open the login page.");
            } catch (NullPointerException e) {
                System.err.println("Error: Could not find login_page.fxml");
                e.printStackTrace();
                showAlert("Error", "Login screen resource not found.");
            }
        }

        // Helper method for showing alerts (if you don't have one already)
        private void showAlert(String title, String content) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }
}