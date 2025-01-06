package com.example.backendPoc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backendPoc.entity.Employee;
import com.example.backendPoc.exception.ResourceNotFoundException;
import com.example.backendPoc.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee emp){
        return employeeRepository.save(emp);
    }
    
    @Override
    public void deleteEmployee(Long id) {
        // TODO Auto-generated method stub
        if(!employeeRepository.existsById(id)){
            throw new ResourceNotFoundException("Employee not found wih id: "+id);
        }
        employeeRepository.deleteById(id);

        
    }

}
