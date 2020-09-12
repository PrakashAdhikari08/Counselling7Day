package com.sevendays.counselling.services.availability;

import com.sevendays.counselling.model.availability.Availability;
import com.sevendays.counselling.services.user.CounselorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by useless on 30/05/20.
 */
@Component
public class ScheduleAvailabilityDelete {
    @Autowired
    AvailabilityRepository availabilityRepository;
    @Autowired
    CounselorRepository counselorRepository;

    //@Scheduled(cron = "0 0 9 * * ?")
    @Scheduled(fixedRate = 600000)
    public void deletePastAvailability() {
        List<Availability> expireAvailabilities= availabilityRepository.findPastAvailabilities();
        for(Availability availability:expireAvailabilities){
                availabilityRepository.deleteAvailability(availability.getAvailabilityId());
                availabilityRepository.delete(availability);
        }

    }
}
