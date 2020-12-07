package com.project.ess.security;

import com.project.ess.entity.UserEntity;
import com.project.ess.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final UserRepository userRepository;



    public AuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header=request.getHeader(SecurityConstants.getHeaderString());

        if(header==null || !header.startsWith(SecurityConstants.getTokenPrefix())){
            filterChain.doFilter(request,response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken=getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        String token=request.getHeader(SecurityConstants.getHeaderString());

        if(token!=null){
            token=token.replace(SecurityConstants.getTokenPrefix(),"");

            String user= Jwts.parser()
                    .setSigningKey(SecurityConstants.getTokenSecret())
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
//            System.out.println(user);

            if(user!=null){
//                return new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>());
                UserEntity userEntity=userRepository.findByEmail(user);

                UserPrincipal userPrincipal=new UserPrincipal(userEntity);
                return new UsernamePasswordAuthenticationToken(user,null,userPrincipal.getAuthorities());
            }
            return null;
        }
        return null;
    }
}
