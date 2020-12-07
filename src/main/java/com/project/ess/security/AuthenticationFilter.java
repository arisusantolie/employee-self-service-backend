package com.project.ess.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ess.SpringApplicationContext;
import com.project.ess.dto.EmployeeDTO;
import com.project.ess.dto.UserLoginDTO;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.services.EmployeeService;
import com.project.ess.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import io.jsonwebtoken.Jwts;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;





    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            UserLoginDTO credentials=new ObjectMapper().readValue(request.getInputStream(),UserLoginDTO.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(),credentials.getPassword(),new ArrayList<>()));
        }catch (IOException ex){
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String userName=((UserPrincipal) authResult.getPrincipal()).getUsername();
        Claims claims = Jwts.claims().setSubject(((UserPrincipal) authResult.getPrincipal()).getUsername());

        var roles =((UserPrincipal) authResult.getPrincipal()).getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String token=Jwts.builder()
                .setSubject(userName)
                .claim("Roles",roles)
                .setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(SecurityConstants.getExpirationTime())))
                .signWith(SignatureAlgorithm.HS512,SecurityConstants.getTokenSecret())
                .compact();
        EmployeeService employeeService=(EmployeeService) SpringApplicationContext.getBean("employeeService"); //cast ke userservice untuk bisa dapat user id
        EmployeeEntity empEntity=employeeService.findByEmail(userName).get();

        response.addHeader(SecurityConstants.getHeaderString(),SecurityConstants.getTokenPrefix()+token);

        response.addHeader("employeeNo",String.valueOf(empEntity.getEmployeeNo()));

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(mapper.writeValueAsString("unAuthorized"));

    }
}
