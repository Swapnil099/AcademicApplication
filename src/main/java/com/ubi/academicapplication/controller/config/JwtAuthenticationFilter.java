package com.ubi.academicapplication.controller.config;

import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ") && !request.getRequestURI().equals("/authenticate")) {

        	jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwtToken);
            } catch (Exception e) {
                throw new CustomException(
                        HttpStatusCode.UNAUTHORIZED_EXCEPTION.getCode(),
                        HttpStatusCode.UNAUTHORIZED_EXCEPTION,
                        HttpStatusCode.UNAUTHORIZED_EXCEPTION.getMessage(),
                        new Result<>());
            }
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            try {
                if (jwtUtil.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } catch (Exception e) {
                throw new CustomException(
                        HttpStatusCode.UNAUTHORIZED_EXCEPTION.getCode(),
                        HttpStatusCode.UNAUTHORIZED_EXCEPTION,
                        HttpStatusCode.UNAUTHORIZED_EXCEPTION.getMessage(),
                        new Result<>());
            }
        }

//        if(username == null && !request.getRequestURI().matches("/swagger-ui/*") && !request.getRequestURI().equals("/authenticate")) {
//            throw new CustomException(
//                    HttpStatusCode.UNAUTHORIZED_EXCEPTION.getCode(),
//                    HttpStatusCode.UNAUTHORIZED_EXCEPTION,
//                    HttpStatusCode.UNAUTHORIZED_EXCEPTION.getMessage(),
//                    new Result<>());
//        }
        filterChain.doFilter(request, response);
    }

}
