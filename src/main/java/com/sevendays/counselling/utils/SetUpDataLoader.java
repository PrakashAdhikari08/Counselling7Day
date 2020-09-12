package com.sevendays.counselling.utils;

import com.sevendays.counselling.model.user.Admin;
import com.sevendays.counselling.model.user.Role;
import com.sevendays.counselling.model.user.User;
import com.sevendays.counselling.services.mail.SendEmail;
import com.sevendays.counselling.services.user.AdminRepository;
import com.sevendays.counselling.services.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by useless on 7/04/20.
 */
@Component
public class SetUpDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private AdminRepository adminRepository;




    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (adminRepository.findAll().size()>0)
            return;

        Admin admin = new Admin();
        admin.setFirstName("Test");
        admin.setLastName("Test");
        admin.setPassword("test1234");
        admin.setUsername("test@gmail.com");
        admin.setRole(Role.ROLE_ADMIN);
        admin.setPosition("Manager");
        admin.setEmployeeNo(1);
        adminRepository.save(admin);

    }



}
