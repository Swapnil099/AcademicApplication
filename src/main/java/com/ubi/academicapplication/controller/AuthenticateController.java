package com.ubi.academicapplication.controller;

import com.ubi.academicapplication.dto.jwtdto.JwtResponse;
import com.ubi.academicapplication.dto.jwtdto.LoginCredentialDTO;
import com.ubi.academicapplication.service.UserAuthenticationService;
import com.ubi.academicapplication.service.UserDetailsServiceImpl;
import com.ubi.academicapplication.service.UserService;
import com.ubi.academicapplication.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthenticateController {

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> getToken(@RequestBody LoginCredentialDTO loginCredentialDTO) {
        String username = loginCredentialDTO.getUsername();
        String password = loginCredentialDTO.getPassword();
        if(!userService.isUsernamePasswordValid(username,password)) return ResponseEntity.ok().body("Invalid Username password");

        try {
            userAuthenticationService.authenticate(username,password);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok().body(new JwtResponse(token));
    }

}
