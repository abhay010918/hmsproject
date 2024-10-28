package com.hms.controller;
import com.hms.entity.AppUser;
import com.hms.payload.LoginDto;
import com.hms.payload.TokenDto;
import com.hms.repository.AppUserRepository;
import com.hms.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private AppUserRepository appUserRepository;
    private UserService userService;

    public UserController(AppUserRepository appUserRepository, UserService userService) {
        this.appUserRepository = appUserRepository;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(
            @RequestBody AppUser user
    ){
        Optional<AppUser> OpUsername = appUserRepository.findByUsername(user.getUsername());Optional<AppUser> byEmail = appUserRepository.findByEmail(user.getEmail());

        if(OpUsername.isPresent()){
            return new ResponseEntity<>("Username Already Taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> OpEmail = appUserRepository.findByEmail(user.getEmail());
        if(OpEmail.isPresent()){
            return new ResponseEntity<>("Email Already Taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String encryptPass = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(4));
        user.setPassword(encryptPass);
        user.setRole("ROLE_USER");
        AppUser saveUser = appUserRepository.save(user);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @GetMapping("/massage")
    public String getMassage(){
        return "this is done ";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
         @RequestBody LoginDto dto
    ){
        String token = userService.veryfylogin(dto);
        if(token!=null){
            TokenDto tokendto = new TokenDto();
            tokendto.setToken(token);
            tokendto.setType("JWT");
            return new ResponseEntity<>(tokendto, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Invalid username/password", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/signup_propertyowner")
    public ResponseEntity<?> createPropertyowner(
            @RequestBody AppUser user
    ){
        Optional<AppUser> OpUsername = appUserRepository.findByUsername(user.getUsername());Optional<AppUser> byEmail = appUserRepository.findByEmail(user.getEmail());

        if(OpUsername.isPresent()){
            return new ResponseEntity<>("Username Already Taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> OpEmail = appUserRepository.findByEmail(user.getEmail());
        if(OpEmail.isPresent()){
            return new ResponseEntity<>("Email Already Taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String encryptPass = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(4));
        user.setPassword(encryptPass);
        user.setRole("ROLE_OWNER");
        AppUser saveUser = appUserRepository.save(user);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

}
