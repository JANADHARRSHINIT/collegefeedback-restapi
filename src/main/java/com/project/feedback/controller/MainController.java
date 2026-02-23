package com.project.feedback.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.project.feedback.config.JwtUtil;
import com.project.feedback.entity.Userentity;
import com.project.feedback.service.UserService;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class MainController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @GetMapping("/")
    public String home() {
        return "College Feedback Backend Server is Running!";
    }
    
    @GetMapping("api/test")
    public String test() {
        return "API is working!";
    }
    
    @GetMapping("api/users/login")
    public String loginTest() {
        return "Login endpoint is ready. Use POST with username/password.";
    }
    
    @PostMapping("api/users/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        String role = loginData.get("role");
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Check hardcoded admin credentials first
            if ("admin".equals(username) && "admin123".equals(password)) {
                Map<String, Object> user = new HashMap<>();
                user.put("role", "admin");
                user.put("username", username);
                user.put("id", 1L);
                
                String token = jwtUtil.generateToken(username, "ADMIN");
                response.put("token", token);
                response.put("user", user);
                return ResponseEntity.ok(response);
            }
            
            // Check database for other users
            Userentity user = userService.findByUsername(username);
            if (user != null && user.isActive()) {
                // Check if password matches
                if (passwordEncoder.matches(password, user.getPasswordHash()) || 
                    password.equals(user.getPasswordHash())) {
                    
                    // Check if role matches (if specified)
                    if (role == null || role.equalsIgnoreCase(user.getRole())) {
                        Map<String, Object> userMap = new HashMap<>();
                        userMap.put("id", user.getId());
                        userMap.put("username", user.getUsername());
                        userMap.put("role", user.getRole().toLowerCase());
                        userMap.put("firstName", user.getFirstName());
                        userMap.put("lastName", user.getLastName());
                        userMap.put("email", user.getEmail());
                        
                        String token = jwtUtil.generateToken(username, user.getRole());
                        response.put("token", token);
                        response.put("user", userMap);
                        return ResponseEntity.ok(response);
                    } else {
                        response.put("message", "Invalid role for this user");
                        return ResponseEntity.badRequest().body(response);
                    }
                } else {
                    response.put("message", "Invalid password");
                    return ResponseEntity.badRequest().body(response);
                }
            } else {
                response.put("message", "User not found or inactive");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "Login failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
