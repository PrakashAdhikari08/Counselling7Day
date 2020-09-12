package com.sevendays.counselling.services.availability;

import com.sevendays.counselling.model.availability.Availability;
import com.sevendays.counselling.model.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by useless on 21/04/20.
 */
public interface AvailabilityRepository extends JpaRepository<Availability,Long> {

    @Query(
            value = "SELECT * FROM availability u WHERE u.date <=current_date ",
            nativeQuery = true)
    List<Availability> findPastAvailabilities();

    @Transactional
    @Modifying
    @Query(value ="delete from counselor_availabilities where " +
            "availabilities_availability_id=:availabilityId",
              nativeQuery = true)
    void deleteAvailability(@Param("availabilityId") Long availabilityId);

}
