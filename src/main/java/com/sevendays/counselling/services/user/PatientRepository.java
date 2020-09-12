package com.sevendays.counselling.services.user;

import com.sevendays.counselling.model.user.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by useless on 21/04/20.
 */
public interface PatientRepository extends JpaRepository<Patient,Long> {
    @Query("SELECT gender, COUNT(*) FROM Patient GROUP BY gender")
    List<Object> findPatientByGender();


}
