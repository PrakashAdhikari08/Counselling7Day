package com.sevendays.counselling.services.booking;

import com.sevendays.counselling.model.booking.Booking;
import com.sevendays.counselling.model.user.Admin;
import com.sevendays.counselling.model.user.Counselor;
import com.sevendays.counselling.model.user.Patient;
import com.sevendays.counselling.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by useless on 21/04/20.
 */
public interface BookingRepository extends JpaRepository<Booking,Long> {

     List<Booking> findAllByCounselor(Counselor counselor);
     List<Booking> findAllByPatient(Patient patient);
     @Query("select count(distinct patient_user_id) from Booking")
     int findPatientCountFromBooking();

     @Query("select counselor, count(booking_id) from Booking group by counselor_user_id")
     List<Object> findCounselorBookingCount();

     @Query("select serviceType, count(booking_id) from Booking group by service_type")
     List<Object> findServiceTypeBookingCount();


     @Query("SELECT MONTHNAME(date), COUNT(booking_id) FROM Booking WHERE date >= :previousYear GROUP BY MONTHNAME(date)")
     List<Object> findBookingCountByMonth(@Param("previousYear") Date previousYear);

}
