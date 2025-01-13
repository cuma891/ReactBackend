package com.example.backendPoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backendPoc.entity.Admin;
import java.util.List;


@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>{

    Admin findByEmailId(String emailId);
    
}
