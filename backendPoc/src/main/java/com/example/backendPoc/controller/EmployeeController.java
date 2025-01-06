package com.example.backendPoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backendPoc.entity.Employee;
import com.example.backendPoc.service.EmployeeServiceImpl;


@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeServiceImpl.addEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        employeeServiceImpl.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @GetMapping("/employeeList")
    public List<Employee> getEmployeeList(){
        return employeeServiceImpl.getEmployeeList();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Employee updated = employeeServiceImpl.updateEmployee(id, updatedEmployee);

        if (updated != null) {
            return ResponseEntity.ok(updated);  // Return updated employee with 200 OK
        } else {
            return ResponseEntity.status(404).body(null);  // Return 404 if employee not found
        }
    }

}
