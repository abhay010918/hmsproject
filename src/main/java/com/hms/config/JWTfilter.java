package com.hms.config;


import com.hms.entity.AppUser;
import com.hms.repository.AppUserRepository;
import com.hms.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTfilter extends OncePerRequestFilter {

    private JWTService jwtService;
    private AppUserRepository appUserRepository;

    public JWTfilter(JWTService jwtService, AppUserRepository appUserRepository) {
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String token =  request.getHeader("Authorization");
        System.out.println(token);

        if(token != null && token.startsWith("Bearer ")){

            String tokenVal = token.substring(8, token.length() - 1);
            String username = jwtService.getUsername(tokenVal);

            Optional<AppUser> byUsername = appUserRepository.findByUsername(username);
            if(byUsername.isPresent()){
                AppUser appUser = byUsername.get();

                UsernamePasswordAuthenticationToken
                        AuthenticationToken = new
                        UsernamePasswordAuthenticationToken(appUser, null , Collections.singleton(new SimpleGrantedAuthority(appUser.getRole())));

                AuthenticationToken.setDetails(new WebAuthenticationDetails(request));

                SecurityContextHolder.getContext().setAuthentication(AuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}

