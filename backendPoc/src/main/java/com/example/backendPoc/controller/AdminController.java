package com.example.backendPoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backendPoc.entity.Admin;
import com.example.backendPoc.entity.AdminResponse;
import com.example.backendPoc.entity.Employee;
import com.example.backendPoc.entity.LoginRequest;
import com.example.backendPoc.service.AdminServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private  AdminServiceImpl adminService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        try{
            if(admin.getEmailId() == null)
       {    
        throw new NullPointerException("Email cannot be null");
       }
       if(admin.getPassword() == null){
        throw new NullPointerException("Password cannot be null");
       }
            Admin admin2 = adminService.registerAdmin(admin);
            return ResponseEntity.ok(admin2);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try{
       if(loginRequest.getEmailId() == null)
       {    
        throw new NullPointerException("Email cannot be null");
       }
       if(loginRequest.getPassword() == null){
        throw new NullPointerException("Password cannot be null");
       }
       AdminResponse admin = adminService.loginAdmin(loginRequest.getEmailId(), loginRequest.getPassword());
       return ResponseEntity.ok(admin);
    }
    catch(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
    }
}
    
    
}
