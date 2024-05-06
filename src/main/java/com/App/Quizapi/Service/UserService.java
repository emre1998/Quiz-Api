package com.App.Quizapi.Service;

import com.App.Quizapi.Model.User;
import com.App.Quizapi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(@Validated String username,  @Validated String email, @Validated String password) {

        if(userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Bu kullanıcı adı kullanılamaz!");
        }
        if(username.length()<3) {
            throw new IllegalArgumentException("Kullanıcı adı 3 karaktenden uzun olmalıdır!");
        }
        if(userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Bu email zaten kayıtlı");
        }
        if(password.length()<3) {
            throw new IllegalArgumentException("Parolanız 3 karakterden uzun olmalıdır!");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);

    }
    public String loginUser(String username,String password) {
        User user = userRepository.findByUsername(username);
        if(user!=null && passwordEncoder.matches(password,user.getPassword())) {
            String token = Jwts.builder()
                    .setSubject(user.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 864000000)) // Token süresi: 10 gün
                    .signWith(SignatureAlgorithm.HS512, "SecretKey")
                    .compact();
            return "Bearer " + token;
        }
        return null;
    }

}
