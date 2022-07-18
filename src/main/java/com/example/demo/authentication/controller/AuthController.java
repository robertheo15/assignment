package com.example.demo.authentication.controller;

import com.example.demo.authentication.model.entity.Role;
import com.example.demo.authentication.model.entity.User;
import com.example.demo.authentication.model.vo.JwtAuthResponseVO;
import com.example.demo.authentication.model.vo.LoginVO;
import com.example.demo.authentication.payload.ErrorDetails;
import com.example.demo.authentication.repository.AuthRepository;
import com.example.demo.authentication.repository.RoleRepository;
import com.example.demo.authentication.security.JwtTokenProvider;
import com.example.demo.authentication.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;


@Api(value = "Auth controller exposes siginin and signup REST APIs")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final AuthRepository authRepository;

    private final RoleRepository roleRepository;

    private final AuthService authService;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider tokenProvider;

    @ApiOperation(value = "REST API to Register or Signup user to Blog app")
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponseVO> authenticateUser(@RequestBody LoginVO user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsernameOrEmail(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthResponseVO(token));
    }

    @ApiOperation(value = "REST API to Login user")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        //         add check for email exists in DB
        if (authRepository.existsByEmail(user.getEmail())) {
            ErrorDetails errorDetails = new ErrorDetails(new Date(), "Email is already taken!",
                    "false");
            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        }

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(roles));

        return new ResponseEntity<>(authService.create(user), HttpStatus.CREATED);
    }
}