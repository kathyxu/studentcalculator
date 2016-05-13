/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unicalculator.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a Course that a student is enrolled in.
 * <p>
 * A student may have many courses.
 * <p>
 * @author karenhuang
 */
public class Course {

    private final SimpleStringProperty courseName;
    private final ObservableList<Assessment> assessments = FXCollections.observableArrayList();
    private final SimpleDoubleProperty targetMark;
    private static final double COURSE_WEIGHTING = 100.0;

    /**
     * Constructor for Course
     *
     * @param courseName the name or course code for this Course
     * @param targetMark the target mark student would like to achieve for this
     * Course
     */
    public Course(String courseName, double targetMark) {
        this.courseName = new SimpleStringProperty(courseName);
        this.targetMark = new SimpleDoubleProperty(targetMark);

    }

    /**
     * Gets the name or course code of this Course
     *
     * @return the identifier for this Course.
     */
    public String getCourseName() {
        return courseName.get();
    }

    /**
     * Changes the name or course code of this Course
     *
     * @param courseName the new name or course code for this Course.
     */
    public void setCourseName(String courseName) {
        this.courseName.set(courseName);
    }

    /**
     * Gets the name or course code of this Course as a StringProperty
     *
     * @return the name or course code of this Course as a StringProperty
     */
    public StringProperty courseNameProperty() {
        return courseName;
    }

    /**
     * Gets the target mark for this Course
     *
     * @return the target mark desired by student for this Course
     */
    public double getTargetMark() {
        return targetMark.get();
    }

    /**
     * Changes the target mark for this Course
     *
     * @param targetMark the new target mark for this Course.
     */
    public void setTargetMark(double targetMark) {
        this.targetMark.set(targetMark);
    }

    /**
     * Gets the target mark for this Course as a SimpleDoubleProperty
     *
     * @return the target mark for this Course as a SimpleDoubleProperty.
     */
    public SimpleDoubleProperty targetMarkProperty() {
        return targetMark;
    }

    /**
     * Returns the data as an observable list of Assessments
     *
     * @return the observable list of Assessments.
     */
    public ObservableList<Assessment> getAssessments() {
        return this.assessments;
    }

    /**
     * Gets the sum of current weighted scores in this Course.
     * <p>
     * The sum is obtained by looping through the asssessments ObservableList
     * and summing the weighted score for each Assessment in this Course.
     * <p>
     *
     * @return the current total weighted score for this Course.
     */
    public double getSumOfMarks() {
        double sum = 0.0;
        for (int i = 0; i < assessments.size(); i++) {
            sum += assessments.get(i).getWeightedScore();
        }
        return sum;

    }

    /**
     * Gets the current total assessment weighting.
     * <p>
     * This is the sum of the weighting for each Assessment in this Course. The
     * total weighting is obtained by looping through the assessments
     * ObservableList and summing the weighting for each Assessment.
     * <p>
     *
     * @return the current total weighting for assessments in this Course.
     */
    public double getTotalAssessmentWeighting() {
        double totalWeighting = 0.0;
        for (int i = 0; i < assessments.size(); i++) {
            totalWeighting += assessments.get(i).getWeighting();
        }
        return totalWeighting;
    }

    /**
     * This method calculates the score that is required for the next
     * Assessment, in order to achieve target mark for this Course.
     * <p>
     * The required score for the next assessment is calculated by subtracting
     * the sum of weighted scores for assessments in this Course from the target
     * mark. This can be done as both are weighted.
     * <p>
     * @return the required score for next assessment task
     */
    public double calculateRequiredScoreForAssessment() {
        double requiredScoreForAssessment;
        requiredScoreForAssessment = (getTargetMark() - getSumOfMarks());
        return requiredScoreForAssessment;
    }

    /**
     * This method calculates the weighting of assessments in this Course that
     * are not completed.
     * <p>
     * The weighting for the next assessment is calculated by subtracting the
     * total weighting for assessments in this Course from the total weighting
     * for this Course. This can be done as both are weighted. The maximum
     * weighting for a Course is always 100.0.
     * <p>
     * @return the required score for next assessment task
     */
    public double calculateAssessmentWeighting() {
        double finalsWeighting;
        finalsWeighting = COURSE_WEIGHTING - getTotalAssessmentWeighting();
        return finalsWeighting;
    }

}
