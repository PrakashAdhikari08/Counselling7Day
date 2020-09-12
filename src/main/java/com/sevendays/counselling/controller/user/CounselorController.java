package com.sevendays.counselling.controller.user;

import com.sevendays.counselling.model.availability.Availability;
import com.sevendays.counselling.model.user.Counselor;
import com.sevendays.counselling.model.user.Role;
import com.sevendays.counselling.model.user.User;
import com.sevendays.counselling.services.availability.AvailabilityRepository;
import com.sevendays.counselling.services.user.CounselorRepository;
import com.sevendays.counselling.services.user.UserRepository;
import jdk.nashorn.api.scripting.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by useless on 20/04/20.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class CounselorController {
    @Autowired
    CounselorRepository counselorRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    AvailabilityRepository availabilityRepository;

    @RequestMapping("/getCounselorList")
    public List<Counselor> getCounselorList() {

        System.out.println("this is request");
        return counselorRepository.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN','COUNSELOR')")
    @PostMapping("/saveCounselor")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveCounselor(@RequestBody Counselor counselor){
        counselor.setRole(Role.ROLE_COUNSELOR);
      Counselor saveCounselor=  counselorRepository.save(counselor);
      return  new ResponseEntity<Void>(HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ADMIN','COUNSELOR')")
    @RequestMapping("/getCounselor/{id}")
    public Counselor getCounselor(@PathVariable long id) {
        Counselor counselor=counselorRepository.getOne(id);
       return counselor;

    }

    @PreAuthorize("hasRole('COUNSELOR')")
    @RequestMapping("/saveAvailability/{id}")
    public ResponseEntity<Void> saveAvailability(@RequestBody List<Availability> availability, @PathVariable long id) {
        availabilityRepository.saveAll(availability);
        Counselor counselor=counselorRepository.getOne(id);
        Set<Availability> availabilities=counselor.getAvailabilities();
        availabilities.addAll(availability);
        counselor.setAvailabilities(availabilities);
        counselorRepository.saveAndFlush(counselor);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/getAvailability")
    public List<String[]>  getAvailability() {
        List<Counselor> counselors=counselorRepository.findAll();
        List<String[]> availabilities=new ArrayList<>();
        for(Counselor counselor:counselors){
            for(Availability availability:counselor.getAvailabilities()){
                String[] availabilityDetails={String.valueOf(counselor.getUserId()),counselor.getFirstName(),counselor.getLastName(),String.valueOf(availability.getAvailabilityId()),availability.getDate(),availability.getShift()};
                availabilities.add(availabilityDetails);
            }
        }
        return availabilities;

    }
    @PreAuthorize("hasRole('COUNSELOR')")
    @RequestMapping("/getAvailabilityByCounselor/{id}")
    public List<String[]>  getAvailabilityByCounselor( @PathVariable long id) {
        System.out.println("this is indie"+id);
        Counselor counselor=counselorRepository.getOne(id);
        List<String[]> availabilities=new ArrayList<>();
            for(Availability availability:counselor.getAvailabilities()){
                String[] availabilityDetails={String.valueOf(counselor.getUserId()),counselor.getFirstName(),counselor.getLastName(),String.valueOf(availability.getAvailabilityId()),availability.getDate(),availability.getShift()};
                availabilities.add(availabilityDetails);
            }
        return availabilities;

    }

    @PreAuthorize("hasAnyRole('ADMIN','COUNSELOR')")
    @RequestMapping("/deleteAvailability/{counselorId}/{availabilityId}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable("counselorId") long counselorId,@PathVariable("availabilityId") long availabilityId ) {
        Availability availability=availabilityRepository.getOne(availabilityId);
        Counselor counselor=counselorRepository.getOne(counselorId);
        Set<Availability> availabilities=counselor.getAvailabilities();
        availabilities.remove(availability);
        counselor.setAvailabilities(availabilities);
        counselorRepository.saveAndFlush(counselor);
        availabilityRepository.delete(availability);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }



}
