package com.sevendays.counselling.model.user;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by useless on 20/04/20.
 */
@Entity
public class Admin extends User {
    private String position;
    private int employeeNo;


    public Admin() {
    }

    public Admin(String position, Long phoneNumber, int employeeNo) {
        this.position = position;
        this.employeeNo = employeeNo;
    }



    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(int employeeNo) {
        this.employeeNo = employeeNo;
    }



    @Override
    public String toString() {
        return "Admin{" +
                ", position='" + position + '\'' +
                ", employeeNo=" + employeeNo +
                '}';
    }
}
