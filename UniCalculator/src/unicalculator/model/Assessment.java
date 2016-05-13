/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unicalculator.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents an assessment task in a Course. 
 * <p>
 * A Course is made up of many assessments.
 * <p>
 * @author karenhuang
 */
public class Assessment {
    // Variables 
    private final SimpleStringProperty assessmentName;
    private final SimpleDoubleProperty weighting;
    private final SimpleDoubleProperty markReceived;
    private final SimpleDoubleProperty outOf;
    private double weightedScore;

    /**
     * Constructor for Assessment
     *
     * @param assessmentName the name of the assessment task
     * @param weighting the weighting for the assessment task
     * @param markReceived the mark the student received for the assessment task
     * @param outOf the maximum mark student can receive for specific assessment
     * task
     */
    public Assessment(String assessmentName, double weighting, double markReceived, double outOf) {
        this.assessmentName = new SimpleStringProperty(assessmentName);
        this.weighting = new SimpleDoubleProperty(weighting);
        this.markReceived = new SimpleDoubleProperty(markReceived);
        this.outOf = new SimpleDoubleProperty(outOf);

    }

    /**
     * Gets the name of this Assessment
     *
     * @return this Assessment's name.
     */
    public String getAssessmentName() {
        return assessmentName.get();
    }

    /**
     * Changes the name of this Assessment
     *
     * @param assessmentName the new name of the Assessment
     */
    public void setAssessmentName(String assessmentName) {
        this.assessmentName.set(assessmentName);
    }

    /**
     * Gets the name of this Assessment as a StringProperty
     *
     * @return this Assessment's name as a StringProperty.
     */
    public StringProperty assessmentNameProperty() {
        return assessmentName;
    }

    /**
     * Gets the weighting of this Assessment
     *
     * @return this Assessment's weighting.
     */
    public double getWeighting() {
        return weighting.get();
    }

    /**
     * Changes the weighting of this Assessment.
     *
     * @param weighting the new weighting of the Assessment.
     */
    public void setWeighting(double weighting) {
        this.weighting.set(weighting);
    }

    /**
     * Gets the weighting of this Assessment as a SimpleDoubleProperty
     *
     * @return this Assessment's weighting as a SimpleDoubleProperty.
     */
    public SimpleDoubleProperty weightingProperty() {
        return weighting;
    }

    /**
     * Gets the mark received by student for this Assessment
     *
     * @return the mark received by student for this Assessment.
     */
    public double getMarkReceived() {
        return markReceived.get();
    }

    /**
     * Changes the mark received by student for this Assessment.
     *
     * @param markRecevied the new mark received by student for this assessment.
     */
    public void setMarkReceived(double markRecevied) {
        this.markReceived.set(markRecevied);
    }

    /**
     * Gets the student's mark for this Assessment as a SimpleDoubleProperty
     *
     * @return mark received by student for this Assessment as a
     * SimpleDoubleProperty.
     */
    public SimpleDoubleProperty markReceivedProperty() {
        return markReceived;
    }

    /**
     * Gets the maximum mark that can be received for this Assessment
     *
     * @return the maximum mark that can be received for this Assessment.
     */
    public double getOutOf() {
        return outOf.get();
    }

    /**
     * Changes the maximum mark that can be received for this Assessment
     *
     * @param outOf the new maximum mark that student can receive for this
     * Assessment.
     */
    public void setOutOf(double outOf) {
        this.outOf.set(outOf);
    }

    /**
     * Gets the maximum mark that can be received for this Assessment as a
     * SimpleDoubleProperty
     *
     * @return the maximum mark that can be received as a SimpleDoubleProperty.
     */
    public SimpleDoubleProperty outOfProperty() {
        return outOf;
    }

    /**
     * Gets the weighted score for this Assessment
     *
     * @return the weightedScore for this Assessment.
     */
    public double getWeightedScore() {
        return weightedScore;
    }

    /**
     * Changes the weightedScore that student receives for this Assessment
     *
     * @param weightedScore the new weighted score for this Assessment
     */
    public void setWeightedScore(double weightedScore) {

        this.weightedScore = weightedScore;

    }

}
