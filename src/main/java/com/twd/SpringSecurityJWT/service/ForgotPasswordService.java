package com.twd.SpringSecurityJWT.service;


import com.twd.SpringSecurityJWT.repository.ForgotPasswordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import com.twd.SpringSecurityJWT.entity.OurUsers;

@Service
public class ForgotPasswordService {
    private static final long EXPIRE_TOKEN=30;

    @Autowired
    private ForgotPasswordRepo repo;


    public String forgotPass(String email){
        Optional<OurUsers> userOptional = Optional.ofNullable(repo.findByEmail(email));

        if(!userOptional.isPresent()){
            return "Invalid email id.";
        }

        OurUsers user=userOptional.get();
        user.setToken(generateToken());
        user.setTokenCreationDate(LocalDateTime.now());

        user=repo.save(user);
        return user.getToken();
    }

    public String resetPass(String token, String password){
        Optional<OurUsers> userOptional= Optional.ofNullable(repo.findByToken(token));

        if(!userOptional.isPresent()){
            return "Invalid token";
        }
        LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

        if (isTokenExpired(tokenCreationDate)) {
            return "Token expired.";

        }

        OurUsers user = userOptional.get();

        user.setPassword(password);
        user.setToken(null);
        user.setTokenCreationDate(null);

        repo.save(user);

        return "Your password successfully updated.";
    }

    private String generateToken() {
        StringBuilder token = new StringBuilder();

        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }

    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >=EXPIRE_TOKEN;
    }

}
