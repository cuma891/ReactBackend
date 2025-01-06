package com.example.backendPoc.service;

import java.util.List;
import java.util.Optional;

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

    public List<Employee> getEmployeeList() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> existingEmployeeOptional = employeeRepository.findById(id);

        if (existingEmployeeOptional.isPresent()) {
            Employee existingEmployee = existingEmployeeOptional.get();

            // Update the fields of the existing employee with the new data
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setAge(updatedEmployee.getAge());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setAddress(updatedEmployee.getAddress());
            existingEmployee.setSalary(updatedEmployee.getSalary());

            // Save the updated employee
            return employeeRepository.save(existingEmployee);
        } else {
            // If the employee doesn't exist, return null (or you can throw an exception)
            return null;
        }
    }

}
