package com.sevendays.counselling.model.user;

import com.sevendays.counselling.model.availability.Availability;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by useless on 20/04/20.
 */
@Entity
public class Counselor extends User {
    private String specialization;
    private int level;
    private String description;
    private int registrationId;
    private int ratePerHour;

    @OneToMany
    private Set<Availability> availabilities;
    public Counselor() {
    }

    public Counselor(String specialization,  int level, String description, int registrationId, int ratePerHour,Set<Availability> availabilities) {
        this.specialization = specialization;
        this.level = level;
        this.description = description;
        this.registrationId = registrationId;
        this.ratePerHour = ratePerHour;
        this.availabilities=availabilities;
    }



    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    public int getRatePerHour() {
        return ratePerHour;
    }

    public void setRatePerHour(int ratePerHour) {
        this.ratePerHour = ratePerHour;
    }


    public Set<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(Set<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    @Override
    public String toString() {
        return "Counselor{" +
                "specialization='" + specialization + '\'' +
                ", level=" + level +
                ", description='" + description + '\'' +
                ", registrationId=" + registrationId +
                ", ratePerHour=" + ratePerHour +
                ", availabilities=" + availabilities +
                '}';
    }
}
