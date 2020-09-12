package com.sevendays.counselling.services.user;

import com.sevendays.counselling.model.user.Admin;
import com.sevendays.counselling.model.user.Counselor;
import com.sevendays.counselling.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by useless on 21/04/20.
 */
public interface AdminRepository extends JpaRepository<Admin,Long> {


}
