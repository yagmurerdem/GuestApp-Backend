package com.project.yagmurquestapp.controllers;

import com.project.yagmurquestapp.entities.Kisi;
import com.project.yagmurquestapp.requests.KisiRequest;
import com.project.yagmurquestapp.requests.UserRequest;
import com.project.yagmurquestapp.responses.AuthResponse;
import com.project.yagmurquestapp.security.JwtTokenProvider;
import com.project.yagmurquestapp.services.KisiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private KisiService kisiService;
    private PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, KisiService kisiService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.kisiService = kisiService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login") //login için mesaj ve userid yi dönücek
    public AuthResponse login(@RequestBody UserRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        Kisi kisi=kisiService.getOneKisiByUserName(loginRequest.getUserName());
        AuthResponse authResponse=new AuthResponse();
        authResponse.setMessage("Bearer" +jwtToken);
        authResponse.setUserId(kisi.getId());
        return authResponse;
    }

    @PostMapping("/register") //register için bir mesaj dönücek
    public ResponseEntity<AuthResponse> register(@RequestBody KisiRequest registerRequest) {
        AuthResponse authResponse=new AuthResponse();
        if (kisiService.getOneKisiByUserName(registerRequest.getUserName()) != null){
            authResponse.setMessage("Username already in use.");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }


        Kisi kisi = new Kisi();
        kisi.setUserName(registerRequest.getUserName());
        kisi.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        kisiService.saveOneKisi(kisi);
        authResponse.setMessage("User successfuly registered");
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }
}
