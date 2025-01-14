package com.example.backendPoc.controller;

import com.example.backendPoc.entity.Admin;
import com.example.backendPoc.entity.AdminResponse;
import com.example.backendPoc.entity.Employee;
import com.example.backendPoc.service.AdminServiceImpl;
import com.example.backendPoc.service.GoogleAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class GoogleAuthController {

    @Autowired
    private GoogleAuthService googleAuthService;

    @Autowired
    private AdminServiceImpl adminServiceImpl;

    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> request) {
        String idToken = request.get("idToken"); 
        System.out.println(idToken);
        if (idToken == null || idToken.isEmpty()) {
            return ResponseEntity.badRequest().body("ID token is required");
        }

        try {
           
            Admin admin = googleAuthService.validateTokenAndRetrieveUser(idToken);

            AdminResponse adminResponse = adminServiceImpl.generateJWTAdminResponse(admin);
            // Return the employee object containing user info
            return ResponseEntity.ok(adminResponse);
        } catch (IOException e) {
            // Return an error response if the token is invalid or verification fails
            return ResponseEntity.status(401).body("Invalid Google ID token");
        } catch (Exception e) {
            // Handle any other unexpected errors
            return ResponseEntity.status(500).body("Internal Server Error");
        }
   }
}
