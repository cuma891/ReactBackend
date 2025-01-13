package com.example.backendPoc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.backendPoc.authentication.JwtService;
import com.example.backendPoc.authentication.UserInfoService;
import com.example.backendPoc.entity.Admin;
import com.example.backendPoc.entity.AdminResponse;
import com.example.backendPoc.exception.ResourceNotFoundException;
import com.example.backendPoc.repository.AdminRepository;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class AdminServiceImpl {
    
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
	private UserInfoService userInfoService;

	@Autowired
	private JwtService jwtService;

    public Admin registerAdmin(Admin admin){
        return adminRepository.save(admin);
    }

    public AdminResponse loginAdmin(String email, String password) throws Exception{
        Admin  admin =  adminRepository.findByEmailId(email);
        if(admin == null){
            throw new ResourceNotFoundException("Employee not found wih email: "+email);
        }
        if(!admin.getPassword().equals(password)){
            throw new Exception("password is incorrect");
        }
        else{
            UserDetails userDetails = userInfoService.userDetailsService().loadUserByUsername(String.valueOf(email));
            String jwt = jwtService.generateToken(userDetails);
            AdminResponse  adminResponse = new AdminResponse(admin.getId(), admin.getEmailId(), jwt);
            return adminResponse;
        }
    }
        
}
