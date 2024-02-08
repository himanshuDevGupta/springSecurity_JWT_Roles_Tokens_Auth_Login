package com.twd.SpringSecurityJWT.repository;

import com.twd.SpringSecurityJWT.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ForgotPasswordRepo extends JpaRepository<OurUsers, Long> {
    OurUsers findByEmail(String email);

    OurUsers findByToken(String token);
}
