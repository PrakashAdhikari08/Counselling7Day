package com.sevendays.counselling.controller.user;

import com.sevendays.counselling.model.user.Patient;
import com.sevendays.counselling.model.user.Role;
import com.sevendays.counselling.services.user.PatientRepository;
import com.sevendays.counselling.services.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by useless on 20/04/20.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class PatientController {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasAnyRole('ADMIN','COUNSELOR')")
    @RequestMapping("/getPatientList")
    public List<Patient> getPatientList() {
        return patientRepository.findAll();
    }

    @PostMapping("/savePatient")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> savePatient(@RequestBody Patient patient){
        patient.setRole(Role.ROLE_PATIENT);
      Patient savePatient=  patientRepository.saveAndFlush(patient);
      return  new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','COUNSELOR','PATIENT')")
    @RequestMapping("/getPatient/{id}")
    public Patient getPatient(@PathVariable long id) {
        Patient patient=patientRepository.getOne(id);
       return patient;

    }

}
