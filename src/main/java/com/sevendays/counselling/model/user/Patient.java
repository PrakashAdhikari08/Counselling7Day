package com.sevendays.counselling.model.user;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by useless on 20/04/20.
 */
@Entity
public class Patient extends User{
    private int height;
    private int weight;
    private Long emergencyContactNumber;
    private String allergicDrugs;
    private String currentIllness;
    private String currentMedication;
    private String notes;

    public Patient() {
    }

    public Patient(int height, int weight, Long emergencyContactNumber, String allergicDrugs, String currentIllness, String currentMedication, String notes, User user) {
        this.height = height;
        this.weight = weight;
        this.emergencyContactNumber = emergencyContactNumber;
        this.allergicDrugs = allergicDrugs;
        this.currentIllness = currentIllness;
        this.currentMedication = currentMedication;
        this.notes = notes;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Long getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(Long emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getAllergicDrugs() {
        return allergicDrugs;
    }

    public void setAllergicDrugs(String allergicDrugs) {
        this.allergicDrugs = allergicDrugs;
    }

    public String getCurrentIllness() {
        return currentIllness;
    }

    public void setCurrentIllness(String currentIllness) {
        this.currentIllness = currentIllness;
    }

    public String getCurrentMedication() {
        return currentMedication;
    }

    public void setCurrentMedication(String currentMedication) {
        this.currentMedication = currentMedication;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Patient{" +
                ", height=" + height +
                ", weight=" + weight +
                ", emergencyContactNumber=" + emergencyContactNumber +
                ", allergicDrugs='" + allergicDrugs + '\'' +
                ", currentIllness='" + currentIllness + '\'' +
                ", currentMedication='" + currentMedication + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
