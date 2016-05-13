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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import unicalculator.model.Assessment;
import unicalculator.model.Course;

/**
 * FXML Controller class
 *
 * @author karenhuang
 */
public class ViewAssessmentController implements Initializable {

    //Buttons
    @FXML
    private Button homeMenuBtn;
    @FXML
    private Button addCourseMenuBtn;

    @FXML
    private Button addAssessmentMenuBtn;
    @FXML
    private Button addAssessmentBtn;
    @FXML
    private Button saveAssessmentBtn;

    //TableView and Table Columns
    @FXML
    private TableView<Course> courseTable;
    @FXML
    private TableColumn<Course, String> courseNameCol;
    @FXML
    private TableView<Assessment> assessmentTable;
    @FXML
    private TableColumn<Assessment, String> assessmentNameCol;
    @FXML
    private TableColumn<Assessment, String> weightingCol;
    @FXML
    private TableColumn<Assessment, String> markReceivedCol;
    @FXML
    private TableColumn<Assessment, String> outOfCol;

    //Textfields
    @FXML
    private TextField assessmentTF;
    @FXML
    private TextField weightingTF;
    @FXML
    private TextField markReceivedTF;
    @FXML
    private TextField outOfTF;

    //Labels
    @FXML
    private Label requiredScoreL;
    @FXML
    private Label finalsWeightingL;
    @FXML
    private Label exceedMessageL;

    private static Stage stage;

    /**
     * Called when the user clicks on the Home, Add Course and Add Assessment
     * Menu buttons.
     */
    @FXML
    private void handleMenu(ActionEvent event) throws IOException {

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
     * Listen to change in Course selected and display Assessment tasks for
     * specific Course.
     * <p>
     * This method handles the display of Assessment tasks for a specific
     * Course. An item is selected from the Course table and the Assessment
     * Table will update and display the relevant assessments.
     * <p>
     */
    @FXML
    private void changeCourseSelected(MouseEvent event) {

        Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();
        assessmentTable.setItems(selectedCourse.getAssessments());

    }

    /**
     * Handles the creation of new Assessment tasks as well as the calculation
     * and display of weighted mark to be achieved for next Assessment.
     * <p>
     * This method handles the creation of a new Assessment. First, an item is
     * selected from the Course table to get Assessments for that instance of
     * Course. If an item is not selected, an alert message is displayed. When
     * the 'Add' button is selected, a new Assessment is created for the
     * selected Course. When the 'Save' button is selected, the score that is
     * required for the next assessment will be calculated and displayed.
     * <p>
     */
    @FXML
    private void addAndSaveNewAssessment(ActionEvent event) throws IOException {
        Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();
        if (selectedCourse == null) {
            // Course not selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(ViewAssessmentController.getStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("Course not Selected");
            alert.setContentText("Please select a course in the table.");

            alert.showAndWait();
        } else if (event.getSource() == addAssessmentBtn) {

            assessmentTable.setItems(selectedCourse.getAssessments());

            Assessment newAssessment = new Assessment(assessmentTF.getText(),
                    Double.parseDouble(weightingTF.getText()), Double.parseDouble(markReceivedTF.getText()), Double.parseDouble(outOfTF.getText()));
            selectedCourse.getAssessments().add(newAssessment);
            //set the weighted score for this assessment to the calculated weighted mark
            newAssessment.setWeightedScore(assessmentWeightedMark());

        } else if (event.getSource() == saveAssessmentBtn) {
            //required score for the next Assessment is calculated and displayed
            double requiredScore = selectedCourse.calculateRequiredScoreForAssessment();
            requiredScoreL.setText(Double.toString(requiredScore));
            //outstanding assessment weighting is calculated and displayed
            double finalsWeighting = selectedCourse.calculateAssessmentWeighting();
            finalsWeightingL.setText(Double.toString(finalsWeighting));
            // if the required score exceeds the weighting of next Assessment
            if (requiredScore > finalsWeighting) { 
                //calculate the maximum mark student can achieve for this course
                double maximumMark = selectedCourse.getTargetMark() - (requiredScore - finalsWeighting);
                //display required score as full weighted mark they can receive for next assessment
                //requiredScoreL.setText(Double.toString(finalsWeighting));
                //display maximum mark that student can achieve for this course
                exceedMessageL.setText("The maximum mark you can get for this course is: " + maximumMark);
            }
        }

        assessmentTF.clear();
        weightingTF.clear();
        markReceivedTF.clear();
        outOfTF.clear();

    }

    /**
     * calculates the weighted mark of an assessment.
     * <p>
     * Weighted Mark for an Assessment is calculated by division of mark
     * received by student by total marks available for an Assessment. This is
     * then multiplied by the weighting.
     * <p>
     *
     * @return the weighted mark for an assessment
     */
    public double assessmentWeightedMark() {
        double weightedMark = (Double.parseDouble(markReceivedTF.getText()) / Double.parseDouble(outOfTF.getText())) * Double.parseDouble(weightingTF.getText());
        return weightedMark;
    }

    /**
     * Called when the user clicks on the delete button.
     * <p>
     * This method handles the deletion of an Assessment. An item is selected
     * from the Assessment table to be deleted. If item is not selected, an
     * Alert pop-up is displayed.
     * <p>
     */
    @FXML
    private void handleDeleteAssessment() {
        int selectedIndex = assessmentTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            assessmentTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(ViewAssessmentController.getStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Assessment Selected");
            alert.setContentText("Please select an assessment in the table.");

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

        courseNameCol.setCellValueFactory(cellData
                -> cellData.getValue().courseNameProperty());

        assessmentNameCol.setCellValueFactory(cellData
                -> cellData.getValue().assessmentNameProperty());
        weightingCol.setCellValueFactory(cellData
                -> cellData.getValue().weightingProperty().asString());

        markReceivedCol.setCellValueFactory(cellData
                -> cellData.getValue().markReceivedProperty().asString());

        outOfCol.setCellValueFactory(cellData
                -> cellData.getValue().outOfProperty().asString());

        courseTable.setItems(UniCalculator.getCourses());

        assessmentTF.setPromptText("e.g. Quiz");
        weightingTF.setPromptText("e.g. 20.0");
        markReceivedTF.setPromptText("e.g. 0.0");
        outOfTF.setPromptText("e.g. 0.0");
    }

    /**
     * Returns the stage.
     *
     * @return stage
     */
    public static Stage getStage() {

        return stage;
    }

}
