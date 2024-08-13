package com.example.DentalClinicMVC.auth;


import com.example.DentalClinicMVC.auth.dto.AuthenticationRequest;
import com.example.DentalClinicMVC.auth.dto.AuthenticationResponse;
import com.example.DentalClinicMVC.auth.dto.RegisterRequest;
import com.example.DentalClinicMVC.configuration.JwtService;
import com.example.DentalClinicMVC.entity.Role;
import com.example.DentalClinicMVC.entity.User;
import com.example.DentalClinicMVC.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request){
        var user = User.builder().
                firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        var jwt = jwtService.geneteToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwt)
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest request){
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword())
                );

                 var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        var jwt = jwtService.geneteToken(user);

        return AuthenticationResponse
                .builder()
                .token(jwt)
                .build();
    }
}
