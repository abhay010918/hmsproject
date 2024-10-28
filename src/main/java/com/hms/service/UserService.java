package com.hms.service;

import com.hms.entity.AppUser;
import com.hms.payload.LoginDto;
import com.hms.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {

    private AppUserRepository appUserRepository;

    private JWTService jwtService;

    public UserService(AppUserRepository appUserRepository, JWTService jwtService) {

        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }

    public String veryfylogin(LoginDto dto){
        Optional<AppUser> byUsername = appUserRepository.findByUsername(dto.getUsername());

        if(byUsername.isPresent()){
            AppUser appUser = byUsername.get();
            if (BCrypt.checkpw(dto.getPassword(), appUser.getPassword())){
                // generated token
                String token = jwtService.generateToken(appUser.getUsername());
                return token;
            }
        }else {
            return null;
        }
        return null;
    }

}
