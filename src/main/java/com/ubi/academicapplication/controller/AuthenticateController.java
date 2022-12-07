package com.ubi.academicapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubi.academicapplication.dto.jwtdto.JwtResponse;
import com.ubi.academicapplication.dto.jwtdto.LoginCredentialDto;
import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.service.UserAuthenticationService;
import com.ubi.academicapplication.service.UserDetailsServiceImpl;
import com.ubi.academicapplication.service.UserService;
import com.ubi.academicapplication.util.JwtUtil;

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
    public Response<JwtResponse> getToken(@RequestBody LoginCredentialDto loginCredentialDTO) {
        String username = loginCredentialDTO.getUsername();
        String password = loginCredentialDTO.getPassword();
        Response<JwtResponse> response = new Response<>();
        Result<JwtResponse> result = new Result<>();
        if(!userService.isUsernamePasswordValid(username,password)){
            throw new CustomException(HttpStatusCode.INVALID_CREDENTIALS.getCode(),
                    HttpStatusCode.INVALID_CREDENTIALS,
                    HttpStatusCode.INVALID_CREDENTIALS.getMessage(),
                    result);
        }

        try {
            userAuthenticationService.authenticate(username,password);
        } catch (Exception e) {
            response.setStatusCode(HttpStatusCode.INVALID_CREDENTIALS.getCode());
            response.setMessage(HttpStatusCode.INVALID_CREDENTIALS.getMessage());
            return response;
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String roleName = userService.getRoleByUsername(username);
        String token = jwtUtil.generateToken(userDetails);

        response.setStatusCode(HttpStatusCode.SUCCESSFUL.getCode());
        response.setMessage(HttpStatusCode.SUCCESSFUL.getMessage());
        response.setResult(new Result<JwtResponse>(new JwtResponse(token,roleName)));
        return response;
    }

}
