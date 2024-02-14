package com.test.dansmultipro.test.dansmultipro.controller;


import com.test.dansmultipro.test.dansmultipro.dto.JwtResponse;
import com.test.dansmultipro.test.dansmultipro.dto.LoginRequest;
import com.test.dansmultipro.test.dansmultipro.dto.RefreshTokenRequest;
import com.test.dansmultipro.test.dansmultipro.dto.SignupRequest;
import com.test.dansmultipro.test.dansmultipro.model.entity.User;
import com.test.dansmultipro.test.dansmultipro.security.jwt.JwtUtils;
import com.test.dansmultipro.test.dansmultipro.security.service.UserDetailsImpl;
import com.test.dansmultipro.test.dansmultipro.security.service.UserDetailsServiceImpl;
import com.test.dansmultipro.test.dansmultipro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService penggunaService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest request) {
        log.info("request signin controller : {}", request);
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        log.info("authentication : {}" , authentication);
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        String refreshToken = jwtUtils.generateRefresJwtToken(authentication);
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok().body(new JwtResponse(token, refreshToken, principal.getUsername()));
    }

    @PostMapping("/signup")
    public User signup(@RequestBody SignupRequest request) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        User created = penggunaService.create(user);
        return created;
    }


    @PostMapping("/refreshToken")
    public ResponseEntity<JwtResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        String token = request.getRefreshToken();
        boolean valid = jwtUtils.validateJwtToken(token);
        if (!valid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        String username = jwtUtils.getUserNameFromJwtToken(token);
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetailsImpl, null,
                userDetailsImpl.getAuthorities());
        String newToken = jwtUtils.generateJwtToken(authentication);
        String refreshToken = jwtUtils.generateRefresJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(newToken, refreshToken, username));
    }
}

