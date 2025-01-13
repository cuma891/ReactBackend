package com.example.backendPoc.service;


import com.example.backendPoc.entity.Employee;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class GoogleAuthService {

    private static final String CLIENT_ID = "182571820195-74n7nc81s803261nnb5gd6kkiojfpmnh.apps.googleusercontent.com"; 

    public Employee validateTokenAndRetrieveUser(String idToken) throws IOException {
        // Validate and parse the ID token
        GoogleIdToken.Payload payload = verifyAndExtractGoogleIdToken(idToken);

        if (payload != null) {
            // Create Employee object from the Google payload data
            Employee employee = new Employee();
            employee.setName((String) payload.get("name"));
            employee.setEmail((String) payload.get("email"));
            return employee;
        } else {
            throw new RuntimeException("Invalid Google Token");
        }
    }

    private GoogleIdToken.Payload verifyAndExtractGoogleIdToken(String idToken) throws IOException {
    
        HttpTransport transport = new NetHttpTransport();
     //   JsonFactory jsonFactory = new JacksonFactory();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
            .setAudience(Collections.singletonList(CLIENT_ID))
            .build();

        // Parse the token
        GoogleIdToken token = GoogleIdToken.parse(jsonFactory, idToken);

        // Verify the token and return the payload if valid
        try{

        if (verifier.verify(token)) {
            return token.getPayload();
        } 

        }catch(GeneralSecurityException e)  {
            throw new RuntimeException("Security error during token verification",e);
        }
        return null; // Invalid token
    } 
}


