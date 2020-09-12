package com.sevendays.counselling.controller.report;

import com.sevendays.counselling.model.booking.Booking;
import com.sevendays.counselling.model.report.TreatmentReport;
import com.sevendays.counselling.model.user.Counselor;
import com.sevendays.counselling.services.booking.BookingRepository;
import com.sevendays.counselling.services.treatmentReport.TreatmentReportRepository;
import com.sevendays.counselling.services.user.CounselorRepository;
import com.sevendays.counselling.services.user.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

/**
 * Created by useless on 12/05/20.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ReportController {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    TreatmentReportRepository treatmentReportRepository;
     @Autowired
    PatientRepository patientRepository;
     @Autowired
    CounselorRepository counselorRepository;

    @PreAuthorize("hasAnyRole('ADMIN','COUNSELOR')")
    @PostMapping("/saveReport")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveReport(@RequestBody HashMap<String,String> parameters) {
        System.out.println("this is inside report");
        Booking booking=bookingRepository.getOne(Long.valueOf(parameters.get("bookingId")));
        TreatmentReport report=new TreatmentReport();
        report.setProblem(parameters.get("problem"));
        report.setFinding(parameters.get("finding"));
        report.setSuggestion(parameters.get("suggestion"));
        treatmentReportRepository.saveAndFlush(report);
        booking.setTreatmentReport(report);
        bookingRepository.saveAndFlush(booking);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @PreAuthorize("hasAnyRole('ADMIN','COUNSELOR')")
    @RequestMapping("/getOverAllReportCount")
    public Map<String,String> getOverAllReportCount() {
          Map<String,String> countMap=new HashMap<>();
          countMap.put("patientCount",String.valueOf(patientRepository.count()));
          countMap.put("counselorCount",String.valueOf(counselorRepository.count()));
          countMap.put("totalBooking",String.valueOf(bookingRepository.count()));
          countMap.put("patientWithBooking",String.valueOf(bookingRepository.findPatientCountFromBooking()));
           return countMap;
    }

    @PreAuthorize("hasAnyRole('ADMIN','COUNSELOR')")
    @RequestMapping("/getBookingCountByCounselor")
    public List<Object> getBookingCountByCounselor() {
        return bookingRepository.findCounselorBookingCount();
    }

    @PreAuthorize("hasAnyRole('ADMIN','COUNSELOR')")
    @RequestMapping("/getBookingCountByServiceType")
    public List<Object> getBookingCountByServiceType() {
        return bookingRepository.findServiceTypeBookingCount();
    }

    @PreAuthorize("hasAnyRole('ADMIN','COUNSELOR')")
    @RequestMapping("/getBookingCountByMonth")
    public List<Object> getBookingCountByMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        return bookingRepository.findBookingCountByMonth(cal.getTime());
    }

    @PreAuthorize("hasAnyRole('ADMIN','COUNSELOR')")
    @RequestMapping("/getPatientByGender")
    public List<Object> getPatientByGender() {

        return patientRepository.findPatientByGender();
    }





    }
