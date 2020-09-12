package com.sevendays.counselling.services.treatmentReport;

import com.sevendays.counselling.model.booking.Booking;
import com.sevendays.counselling.model.report.TreatmentReport;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by useless on 21/04/20.
 */
public interface TreatmentReportRepository extends JpaRepository<TreatmentReport,Long> {


}
