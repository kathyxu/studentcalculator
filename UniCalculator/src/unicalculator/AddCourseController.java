/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unicalculator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import unicalculator.model.Course;

/**
 * FXML Controller class for AddCourse page
 *
 * @author karenhuang
 */
public class AddCourseController implements Initializable {

    @FXML
    private TextField courseNameTF;
    @FXML
    private TextField targetMarkTF;

    @FXML
    private TableView<Course> courseTable;

    @FXML
    private TableColumn<Course, String> courseCol;
    @FXML
    private TableColumn<Course, String> targetMarkCol;

    //Buttons
    @FXML
    private Button homeMenuBtn;
    @FXML
    private Button addCourseMenuBtn;

    @FXML
    private Button addAssessmentMenuBtn;

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    /**
     * Called when the user clicks on the Home, Add Course and Add Assessment
     * Menu buttons.
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

        Parent root = null;
        if (event.getSource() == homeMenuBtn) {
            root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        } else if (event.getSource() == addCourseMenuBtn) {
            root = FXMLLoader.load(getClass().getResource("AddCourse.fxml"));
        } else if (event.getSource() == addAssessmentMenuBtn) {
            root = FXMLLoader.load(getClass().getResource("ViewAssessment.fxml"));
        }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        Stage stage = UniCalculator.getStage();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Called when users click on the add button to add a new Course.
     * <p>
     * User input is read in and a new Course is created and added to the
     * observable list of Courses.<p>
     */
    @FXML
    private void handleAddCourse(ActionEvent event) throws IOException {
        String courseName = courseNameTF.getText();
        double targetMark = Double.parseDouble(targetMarkTF.getText());
        UniCalculator.getCourses().add(new Course(courseName, targetMark));
        courseNameTF.clear();
        targetMarkTF.clear();

    }

    /**
     * Called when the user clicks on the delete button.
     * <p>
     * This method handles the deletion of a course. An item is selected from
     * the Course table to be deleted. If item is not selected, an Alert pop-up
     * is displayed.
     * <p>
     */
    @FXML
    private void handleDeleteCourse() {
        int selectedIndex = courseTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            courseTable.getItems().remove(selectedIndex);
        } else {
            // Course not selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(ViewAssessmentController.getStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Course Selected");
            alert.setContentText("Please select a course in the table.");

            alert.showAndWait();
        }

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        courseCol.setCellValueFactory(cellData
                -> cellData.getValue().courseNameProperty());
        targetMarkCol.setCellValueFactory(cellData
                -> cellData.getValue().targetMarkProperty().asString());
        courseTable.setItems(UniCalculator.getCourses());

        courseNameTF.setPromptText("Enter Course Name");
        targetMarkTF.setPromptText("Enter Target Mark");

    }

}
