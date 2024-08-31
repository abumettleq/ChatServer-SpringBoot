package com.nova.messengerapi.controllers;

import com.nova.messengerapi.entities.User;
import com.nova.messengerapi.responses.LoginResponse;
import com.nova.messengerapi.responses.UserIdResponse;
import com.nova.messengerapi.services.UserService;
import com.nova.messengerapi.components.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;

    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> authenticate(@RequestParam("username") String username, @RequestParam("password") String password) {
        LoginResponse response = new LoginResponse();
        User user = userService.getUserByUsername(username);

        if (user == null) {
            response.setSuccess(false);
            response.setMessage("User doesn't exist.");
            response.setToken("");
            response.setUsername("");
            return ResponseEntity.ok(response);
        }

        if (!user.getPassword().equals(password)) {
            response.setSuccess(false);
            response.setMessage("Wrong password.");
            response.setToken("");
            response.setUsername("");
            return ResponseEntity.ok(response);
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(username);

        response.setSuccess(true);
        response.setMessage("Login successful.");
        response.setToken(token);
        response.setUsername(username);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate")
    public ResponseEntity<LoginResponse> validate(@RequestParam("token") String token) {
        LoginResponse response = new LoginResponse();

        if(jwtUtil.isTokenExpired(token))
        {
            response.setSuccess(false);
            response.setMessage("Session expired, login again.");
            response.setToken("");
            response.setUsername("");
            return ResponseEntity.ok(response);
        }

        response.setSuccess(true);
        response.setMessage("Welcome back.");
        response.setToken(token);
        response.setUsername(jwtUtil.extractUsername(token));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/fetch-user-id")
    public ResponseEntity<UserIdResponse> fetchUserId(@RequestParam("username") String username) {
        UserIdResponse response = new UserIdResponse();

        User user = userService.getUserByUsername(username);
        if(user == null)
        {
            response.setSuccess(false);
            response.setUserId(0);
            return ResponseEntity.ok(response);
        }

        response.setSuccess(true);
        response.setUserId(user.getUserId());
        return ResponseEntity.ok(response);
    }
}
