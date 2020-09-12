package com.sevendays.counselling.controller.resetPassword;


import com.sevendays.counselling.model.resetPassword.ResetPassword;
import com.sevendays.counselling.model.user.User;
import com.sevendays.counselling.services.mail.SendEmail;
import com.sevendays.counselling.services.resetPassword.ResetPasswordRepository;
import com.sevendays.counselling.services.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by useless on 12/05/20.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ResetPasswordController {

    @Autowired
    UserRepository userRepository;

     @Autowired
     ResetPasswordRepository resetPasswordRepository;
    @Autowired
    SendEmail sendEmail;

    @Transactional
    @PostMapping("/validateUser")
    public ResponseEntity<String> validateUser(@RequestBody Map<String,String> receiveData) {
        String username=receiveData.get("username");
         User user=userRepository.findByUsername(username);
         if(user!=null){
             if(resetPasswordRepository.findByUsername(username)==null) {
                 String code = String.valueOf(10000 + new Random().nextInt(90000));
                 sendEmail.sendPasswordResendCode(username, code);
                 ResetPassword resetPassword = new ResetPassword();
                 resetPassword.setToken(code);
                 resetPassword.setUsername(username);
                 resetPassword.setUsed(false);
                 resetPassword.setValidated(false);
                 resetPasswordRepository.saveAndFlush(resetPassword);
             }
             return ResponseEntity.status(HttpStatus.OK).body(username);
         }else{
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Can not find the user name");
         }
    }

    @PostMapping("/validateToken")
    public ResponseEntity<String> validateToken(@RequestBody Map<String,String> token) {
        String username=token.get("username");
        String code=token.get("code");
        ResetPassword resetPassword=resetPasswordRepository.findByUsername(username);
        if(resetPassword!=null&&resetPassword.getToken().equals(code)){
            resetPassword.setValidated(true);
            resetPasswordRepository.saveAndFlush(resetPassword);
            return ResponseEntity.status(HttpStatus.OK).body(username);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Can not validate the token");
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody Map<String,String> data) {
        String username=data.get("username");
        String password=data.get("password");
        ResetPassword resetPassword=resetPasswordRepository.findByUsername(username);
        User user =userRepository.findByUsername(username);
        if(user!=null&&resetPassword.isValidated()&&(!resetPassword.isUsed())){
            resetPassword.setUsed(true);
            resetPasswordRepository.delete(resetPassword);
            user.setPassword(password);
            userRepository.saveAndFlush(user);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully changed ");
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Can not find change password please try again");
        }
    }
    }
