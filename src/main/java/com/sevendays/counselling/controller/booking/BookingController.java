package com.sevendays.counselling.controller.booking;

import com.sevendays.counselling.model.availability.Availability;
import com.sevendays.counselling.model.booking.Booking;
import com.sevendays.counselling.model.user.Counselor;
import com.sevendays.counselling.model.user.Patient;
import com.sevendays.counselling.services.availability.AvailabilityRepository;
import com.sevendays.counselling.services.booking.BookingRepository;
import com.sevendays.counselling.services.mail.SendEmail;
import com.sevendays.counselling.services.user.CounselorRepository;
import com.sevendays.counselling.services.user.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by useless on 7/05/20.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class BookingController {
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    CounselorRepository counselorRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    SendEmail sendEmail;
    @Autowired
    AvailabilityRepository availabilityRepository;

    @PreAuthorize("hasRole('PATIENT')")
    @RequestMapping("/saveBooking")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveBooking(@RequestBody HashMap<String,String> parameters){
        System.out.println("this is for change"+parameters);
        Counselor counselor=counselorRepository.getOne(Long.valueOf(parameters.get("counselorId")));
        Patient patient=patientRepository.getOne(Long.valueOf(parameters.get("patientId")));

        Booking booking=new Booking();
        booking.setCounselor(counselor);
        booking.setPatient(patient);
        booking.setProblem(parameters.get("problem"));
        booking.setCharge(counselor.getRatePerHour()*2);
        try {
            booking.setDate(simpleDateFormat.parse(parameters.get("date")));

        booking.setServiceType(counselor.getSpecialization());
        booking.setShift(parameters.get("shift"));
        bookingRepository.save(booking);
            sendEmail.sendBookingEmail(booking);
        Set<Availability> availabilities=counselor.getAvailabilities();
            for(Availability availability1:availabilities){
                System.out.println("1"+availability1.toString());
            }
            Availability availabilityToRemove=new Availability();
        for(Availability availability:availabilities){
            if(availability.getDate().equals(booking.getDate())&&availability.getShift().equals(booking.getShift())){
                availabilityToRemove=availability;
            }
        }
        availabilities.remove(availabilityToRemove);
        for(Availability availability1:availabilities){
            System.out.println("2"+availability1.toString());
        }
        counselor.setAvailabilities(availabilities);
        counselorRepository.saveAndFlush(counselor);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/getBookingList")
    public List<Booking> getBookingList() {
        return bookingRepository.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN','COUNSELOR','PATIENT')")
    @RequestMapping("/getBooking/{bookingId}")
    public Booking getBooking(@PathVariable Long bookingId) {
        System.out.println("this is inside booking");
        return bookingRepository.getOne(Long.valueOf(bookingId));
    }

    @PreAuthorize("hasAnyRole('ADMIN','COUNSELOR','PATIENT')")
    @RequestMapping("/deleteBooking/{bookingId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Transactional
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        System.out.println("this is inside booking");
        Booking booking=bookingRepository.getOne(bookingId);
        Availability availability=new Availability();
        try {
            availability.setDate(simpleDateFormat.parse(booking.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        availability.setShift(booking.getShift());
        availabilityRepository.save(availability);
        Counselor counselor=booking.getCounselor();
        Set<Availability> availabilitySet=counselor.getAvailabilities();
        availabilitySet.add(availability);
        counselor.setAvailabilities(availabilitySet);
        counselorRepository.saveAndFlush(counselor);
        sendEmail.sendDeleteOfBookingEmail(booking);
        bookingRepository.deleteById(bookingId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('COUNSELOR')")
    @RequestMapping("/getBookingListByCounselor/{userId}")
    public List<Booking> getBookingListByCounselor(@PathVariable long userId) {
        return bookingRepository.findAllByCounselor(counselorRepository.getOne(userId));
    }

    @PreAuthorize("hasRole('PATIENT')")
    @RequestMapping("/getBookingListByPatient/{userId}")
    public List<Booking> getBookingListByPatient(@PathVariable long userId) {
        return bookingRepository.findAllByPatient(patientRepository.getOne(userId));
    }
}
