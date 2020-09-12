package com.sevendays.counselling.services.resetPassword;

import com.sevendays.counselling.model.availability.Availability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by useless on 2/06/20.
 */
@Component
public class ScheduleResetPasswordDelete {
    @Autowired
    ResetPasswordRepository resetPasswordRepository;

    @Scheduled(fixedRate = 600000)
    public void deleteResetPasswordToken() {
       resetPasswordRepository.deleteExpireResetPassword();

    }

}
