package com.example.backendPoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.backendPoc.service.EmployeeServiceImpl;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl emplServiceImpl;
    

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        emplServiceImpl.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

}
