package com.sevendays.counselling.controller.user;

import com.sevendays.counselling.model.user.Admin;
import com.sevendays.counselling.model.user.Counselor;
import com.sevendays.counselling.model.user.Role;
import com.sevendays.counselling.model.user.User;
import com.sevendays.counselling.services.user.AdminRepository;
import com.sevendays.counselling.services.user.CounselorRepository;
import com.sevendays.counselling.services.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by useless on 20/04/20.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class AdminController {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/getAdminList")
    public List<Admin> getAdminList() {
        return adminRepository.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/saveAdmin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveAdmin(@RequestBody Admin admin){
        admin.setRole(Role.ROLE_ADMIN);
      Admin saveAdmin=  adminRepository.save(admin);
      return  new ResponseEntity<Void>(HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/getAdmin/{id}")
    public Admin getAdmin(@PathVariable long id) {
        Admin admin=adminRepository.getOne(id);
       return admin;

    }

}
