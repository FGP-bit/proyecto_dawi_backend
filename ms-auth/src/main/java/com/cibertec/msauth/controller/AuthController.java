package com.cibertec.msauth.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.msauth.dto.LoginRequest;
import com.cibertec.msauth.dto.LoginResponse;
import java.time.LocalDateTime;
import com.cibertec.msauth.util.JwtUtil;

@RestController
@RequestMapping("api/auth")
public class AuthController {

	@Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) { 
        
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        if (authentication.isAuthenticated()) {
            String token = jwtUtil.generateToken(request.getUsername());
          
            String mensaje = "ALERTA: El usuario " + request.getUsername() + " inició sesión el " + LocalDateTime.now();
            rabbitTemplate.convertAndSend("cola-login", mensaje);
            System.out.println("Mensaje enviado a RabbitMQ: " + mensaje);

            return ResponseEntity.ok(new LoginResponse(token));
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}