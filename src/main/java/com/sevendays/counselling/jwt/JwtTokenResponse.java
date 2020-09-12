package com.sevendays.counselling.jwt;

import com.sevendays.counselling.model.user.User;

import java.io.Serializable;

public class JwtTokenResponse implements Serializable {


  private static final long serialVersionUID = 8317676219297719109L;

  private final String token;
  private User user;

    public JwtTokenResponse(String token, User userDetails) {
        this.token = token;
        System.out.println("this is user-----"+userDetails.getUsername());
            this.user=userDetails;

    }

    public String getToken() {
        return this.token;
    }

    public User getUser() {
        return user;
    }
}

