package com.sevendays.counselling.model.resetPassword;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by useless on 30/04/20.
 */
@Entity
public class ResetPassword {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long resetPasswordId;
    @Column(nullable = false, unique = true)
    private String  username;
    private String token;
    private boolean isUsed;
    private boolean isValidated;
    @CreationTimestamp
    private Date createdDate;
    public ResetPassword() {
    }

    public ResetPassword(String username, String token, boolean isUsed, boolean isValidated) {
        this.username = username;
        this.token = token;
        this.isUsed = isUsed;
        this.isValidated = isValidated;
    }

    public Long getResetPasswordId() {
        return resetPasswordId;
    }

    public void setResetPasswordId(Long resetPasswordId) {
        this.resetPasswordId = resetPasswordId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }


    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean validated) {
        isValidated = validated;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "ResetPassword{" +
                "resetPasswordId=" + resetPasswordId +
                ", username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", isUsed=" + isUsed +
                ", isValidated=" + isValidated +
                '}';
    }
}
