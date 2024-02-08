package com.twd.SpringSecurityJWT.repository;

import com.twd.SpringSecurityJWT.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OurUserRepo extends JpaRepository<OurUsers, Integer> {
//    @Query(value = "select * from users where email = ?1", nativeQuery = true)

    Optional<OurUsers> findByEmail(String email);
     List<OurUsers> findAll();
}
