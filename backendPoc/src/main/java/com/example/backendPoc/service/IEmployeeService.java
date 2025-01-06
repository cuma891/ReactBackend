package com.example.backendPoc.service;

import java.util.List;

import com.example.backendPoc.entity.Employee;

public interface IEmployeeService {

    void deleteEmployee(Long id);

    public List<Employee> getEmployeeList();
    
}
