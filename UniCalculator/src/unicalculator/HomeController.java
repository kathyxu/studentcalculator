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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import unicalculator.model.Course;

/**
 * FXML Controller class
 *
 * @author karenhuang
 */
public class HomeController implements Initializable {

    //Table and Table Columns
    @FXML
    private TableView<Course> homeTable;
    @FXML
    private TableColumn<Course, String> courseCol;

    //Buttons
    @FXML
    private Button homeMenuBtn;
    @FXML
    private Button addCourseMenuBtn;

    @FXML
    private Button addAssessmentMenuBtn;

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
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        courseCol.setCellValueFactory(cellData
                -> cellData.getValue().courseNameProperty());
        homeTable.setItems(UniCalculator.getCourses());
    }

}
