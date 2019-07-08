package com.accolite.internal.project.exemployeeportal.controller;

import com.accolite.internal.project.exemployeeportal.exception.BadRequestException;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.GenericUtilResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.AuthResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.LoginRequest;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.SignUpRequest;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.user.UserRepository;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.ExEmployeeRepository;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.AuthProvider;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ExEmployee;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.User;
import com.accolite.internal.project.exemployeeportal.security.TokenProvider;
import com.accolite.internal.project.exemployeeportal.security.ExEmployeePrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ExEmployeeRepository exEmployeeRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }
    
    @PostMapping("/authenticated/login")
    public ResponseEntity<?> alreadyAuthenticateUser(@RequestParam("email") String email) {  	
        Authentication authentication = 
                new UsernamePasswordAuthenticationToken(
                		email,
                        null,
                        AuthorityUtils.createAuthorityList("EMPLOYEE")
                );
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + email)
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(email);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(exEmployeeRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        ExEmployee exEmployee = new ExEmployee();
        exEmployee.setFirstName(signUpRequest.getFirstName());
        exEmployee.setLastName(signUpRequest.getLastName());
        exEmployee.setEmployeeId(signUpRequest.getEmployeeId());
        exEmployee.setEmail(signUpRequest.getEmail());
        exEmployee.setPhoneNumber(signUpRequest.getPhoneNumber());
        exEmployee.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        

        ExEmployee result = exEmployeeRepository.save(exEmployee);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new GenericUtilResponse(true, "User registered successfully@"));
    }

}
