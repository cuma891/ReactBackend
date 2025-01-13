package com.example.backendPoc.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminResponse {
    private long id;
    private String emailId;
    private String jwtToken;
    
    public AdminResponse(long id, String emailId, String jwtToken) {
        this.id = id;
        this.emailId = emailId;
        this.jwtToken = jwtToken;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public String toString() {
        return "AdminResponse [id=" + id + ", emailId=" + emailId + ", jwtToken=" + jwtToken + "]";
    }

    
}
