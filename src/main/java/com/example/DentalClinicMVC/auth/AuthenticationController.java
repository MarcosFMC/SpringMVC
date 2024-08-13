package com.example.DentalClinicMVC.auth;


import com.example.DentalClinicMVC.auth.dto.AuthenticationRequest;
import com.example.DentalClinicMVC.auth.dto.AuthenticationResponse;
import com.example.DentalClinicMVC.auth.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login (
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.login(request));
    }

}