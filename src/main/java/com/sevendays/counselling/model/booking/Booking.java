package com.sevendays.counselling.model.booking;

import com.sevendays.counselling.model.report.TreatmentReport;
import com.sevendays.counselling.model.user.Counselor;
import com.sevendays.counselling.model.user.Patient;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by useless on 30/04/20.
 */
@Entity
public class Booking {
    @Id
@GeneratedValue(strategy= GenerationType.AUTO)
    private Long bookingId;
    private int charge;
    private String problem;
    private Date date;
    private String shift;
    private String serviceType;
    @ManyToOne
    Counselor counselor;
    @ManyToOne
    Patient patient;
    @OneToOne
    TreatmentReport treatmentReport;

    public Booking() {
    }

    public Booking(int charge, String problem, Date date, String shift, String serviceType, Counselor counselor, Patient patient,TreatmentReport treatmentReport) {
        this.charge = charge;
        this.problem = problem;
        this.date = date;
        this.shift = shift;
        this.serviceType = serviceType;
        this.counselor = counselor;
        this.patient = patient;
        this.treatmentReport=treatmentReport;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Counselor getCounselor() {
        return counselor;
    }

    public void setCounselor(Counselor counselor) {
        this.counselor = counselor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public TreatmentReport getTreatmentReport() {
        return treatmentReport;
    }

    public void setTreatmentReport(TreatmentReport treatmentReport) {
        this.treatmentReport = treatmentReport;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", charge=" + charge +
                ", problem='" + problem + '\'' +
                ", date=" + date +
                ", shift='" + shift + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", counselor=" + counselor +
                ", patient=" + patient +
                ", treatmentReport=" + treatmentReport +
                '}';
    }
}
