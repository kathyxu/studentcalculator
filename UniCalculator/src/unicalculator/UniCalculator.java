/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unicalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import unicalculator.model.Course;
import static javafx.application.Application.launch;

/**
 * The UniCalculator program implements an application that calculates 
 * the weighted mark a student will require for their final examination.
 * @author karenhuang
 */
public class UniCalculator extends Application {

    private static Stage stage;
    private static ObservableList<Course> courses = FXCollections.observableArrayList();

    /**
     * Returns the stage.
     * @return stage
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * Returns the data as an observable list of Courses.
     *
     * @return the observable list of Courses.
     */
    public static ObservableList<Course> getCourses() {
        return courses;

    }

    @Override // Override the start method in the Application class
    public void start(Stage stage) throws Exception {
        UniCalculator.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("UniCalculator");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Main method to launch the application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
