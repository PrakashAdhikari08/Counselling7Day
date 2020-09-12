package com.sevendays.counselling.model.availability;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by useless on 30/04/20.
 */
@Entity
public class Availability {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long availabilityId;
    Date date;
    String shift;

    public Availability() {
    }

    public Availability(Date date, String shift) {
        this.date = date;
        this.shift = shift;
    }

    public Long getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(Long availabilityId) {
        this.availabilityId = availabilityId;
    }

    public String getDate() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    @Override
    public String toString() {
        return "Availability{" +
                "availabilityId=" + availabilityId +
                ", date=" + date +
                ", shift='" + shift + '\'' +
                '}';
    }
}
