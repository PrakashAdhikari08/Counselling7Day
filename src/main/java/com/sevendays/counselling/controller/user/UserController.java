package com.sevendays.counselling.controller.user;

import com.sevendays.counselling.model.user.User;
import com.sevendays.counselling.services.mail.SendEmail;
import com.sevendays.counselling.services.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by useless on 22/04/20.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class UserController{
    @Autowired
    UserRepository userRepository;
    @Autowired
    SendEmail sendEmail;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/getUserList")
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN','COUNSELOR','PATIENT')")
    @PostMapping("/saveUser/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveUser(@RequestBody User user, @PathVariable long id){
        User saveUser=  userRepository.save(user);
        return  new ResponseEntity<Void>(HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ADMIN','COUNSELOR','PATIENT')")
    @RequestMapping("/getUser/{id}")
    public User getUser(@PathVariable long id) {
        User user=userRepository.getOne(id);
        return user;

    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable long id){
        userRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @PostMapping("/contact")
    public ResponseEntity<Void> contactDetails(@RequestBody Map<String, String> details){
        System.out.println(details);
        sendEmail.sendContactMail(details);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
