package com.sevendays.counselling.model.report;

import com.sevendays.counselling.model.booking.Booking;

import javax.persistence.*;

/**
 * Created by useless on 30/04/20.
 */
@Entity
public class TreatmentReport {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long treatmentId;
    private String problem;
    private String suggestion;
    private String finding;

    public TreatmentReport() {
    }

    public TreatmentReport(String problem, String suggestion, String finding) {
        this.problem = problem;
        this.suggestion = suggestion;
        this.finding = finding;
    }

    public Long getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Long treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getFinding() {
        return finding;
    }

    public void setFinding(String finding) {
        this.finding = finding;
    }

    @Override
    public String toString() {
        return "TreatmentReport{" +
                "treatmentId=" + treatmentId +
                ", problem='" + problem + '\'' +
                ", suggestion='" + suggestion + '\'' +
                ", finding='" + finding + '\'' +
                '}';
    }
}
