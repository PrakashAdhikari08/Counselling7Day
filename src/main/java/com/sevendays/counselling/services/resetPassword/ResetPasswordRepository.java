package com.sevendays.counselling.services.resetPassword;

import com.sevendays.counselling.model.resetPassword.ResetPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by useless on 21/04/20.
 */
public interface ResetPasswordRepository extends JpaRepository<ResetPassword,Long> {
     ResetPassword findByUsername(String username);

     @Transactional
     @Modifying
     @Query(value ="DELETE FROM reset_password WHERE created_date < (NOW() - INTERVAL 20 MINUTE)",
             nativeQuery = true)
     void deleteExpireResetPassword();

}
