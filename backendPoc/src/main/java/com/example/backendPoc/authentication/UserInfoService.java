package com.example.backendPoc.authentication;

import com.example.backendPoc.exception.ResourceNotFoundException;
import com.example.backendPoc.repository.AdminRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserInfoService {


    @Autowired
    private AdminRepository adminRepository;


    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws ResourceNotFoundException {

                return adminRepository.findByEmailId(username);
            }
        };

    }


}