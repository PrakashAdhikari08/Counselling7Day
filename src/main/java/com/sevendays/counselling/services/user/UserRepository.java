package com.sevendays.counselling.services.user;

import com.sevendays.counselling.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by useless on 6/04/20.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String userName);
}
