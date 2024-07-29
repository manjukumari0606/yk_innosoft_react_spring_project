package com.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex.dto.UserResponse;
import com.ex.entity.Product;
import com.ex.entity.User_Info;
import com.ex.repository.UserRepository;
import com.ex.services.JwtService;
import com.ex.services.UserService;

@RestController
@RequestMapping("/api")
public class UserInfoController {
	
		private UserService userService;
	    private final PasswordEncoder passwordEncoder;
	    
	    @Autowired
	    private UserRepository userRepository;
	    
	    @Autowired
		private AuthenticationManager authenticationManager;
		
		@Autowired
		private JwtService jwtService;

	    @Autowired
	    public UserInfoController(UserService userService, PasswordEncoder passwordEncoder) {
	        this.userService = userService;
	        this.passwordEncoder = passwordEncoder;
	    }
	    
	    @PostMapping("/save")
		public String saveUser(@RequestBody User_Info user_Info) {
	    	userService.saveUser(user_Info);
			return "User addedd successfully";
		}

	    @PostMapping("/register")
	    public String register(@RequestBody User_Info user) {
	        User_Info existingUser = userService.findByEmail(user.getEmail());
	        if (existingUser != null) {
	            return "Email already exists";
	        }
	        userService.saveUser(user);
	        return "User registered successfully";
	    }

	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody User_Info user) {
	        User_Info existingUser = userService.findByEmail(user.getEmail());
	        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
	            return ResponseEntity.ok().body("Login successful");
	        } else {
	            return ResponseEntity.badRequest().body("Invalid credentials");
	        }
	    }
	    
	    @PostMapping("/authenticate")
	    public ResponseEntity<UserResponse> authenticateAndGetToken(@RequestBody User_Info user) { // Return ResponseEntity for better control over the response

	        Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

	        if (authentication.isAuthenticated()) {
	            // Generate token
	            String token = jwtService.generateToken(user.getEmail());

	            // Load user details from the database
	            User_Info authenticatedUser = userRepository.findByEmail(user.getEmail());
	            if (authenticatedUser == null) {
	                throw new UsernameNotFoundException("User not found in the database");
	            }

	            // Create response object
	            UserResponse authResponse = new UserResponse(token, authenticatedUser);

	            return ResponseEntity.ok(authResponse);
	        } else {
	            throw new UsernameNotFoundException("Invalid User request");
	        }
	    }

	}
