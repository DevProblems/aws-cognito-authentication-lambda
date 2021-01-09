package org.dev.authentication;

import com.amazonaws.services.cognitoidp.model.ConfirmSignUpResult;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/*@author: DEVPROBLEMS(A SARANG KUMAR TAK)*/

public class Service {

   CognitoClient client = new CognitoClient();

   public Response authentication(Request request) {
        Response res = new Response();
        String resource = request.getResource();
        switch (resource) {
            case "/signup" :
                res = signUp(request.getBody());
                break;
            case "/confirmsignup" :
                res = confirmSignUp(request.getBody());
                break;
            case "/login" :
                res = login(request.getBody());
                break;
            default:
                res.setBody("Wrong resource name");
                res.setStatusCode(500);
                break;
        }
       return res;
   }

   public Response signUp(String body) {
       Response res = new Response();
       try {
           ObjectMapper mapper = new ObjectMapper();
           Map<String, String> parameters = mapper.readValue(body, new TypeReference<Map<String, String>>(){});
           String name = parameters.get("fullname");
           String email = parameters.get("email");
           String password = parameters.get("password");
           SignUpResult result =  client.signUp(name,email, password);
           if(result != null) {
               res.setStatusCode(200);
               res.setBody("Confirmation Code is sent to registered email address");
           } else {
               res.setStatusCode(400);
               res.setBody("Please try again after sometime");
           }
       }
       catch(Exception e) {
           res.setStatusCode(500);
           res.setBody(e.getMessage());
       }
        return res;
   }

   public Response confirmSignUp(String body) {
       Response res = new Response();
       try {
           ObjectMapper mapper = new ObjectMapper();
           Map<String, String> parameters = mapper.readValue(body, new TypeReference<Map<String, String>>() {});
           String email = parameters.get("email");
           String confirmationCode = parameters.get("confirmationcode");
           ConfirmSignUpResult result = client.confirmSignUp(email,confirmationCode);
           if(result != null) {
               res.setStatusCode(200);
               res.setBody("Confirm Signup is successful");
           }
           else {
               res.setStatusCode(400);
               res.setBody("Please try again after sometime");
           }
       }
       catch(Exception e) {
           res.setStatusCode(500);
           res.setBody(e.getMessage());
       }
       return res;
   }

   public Response login(String body) {
       Response res = new Response();
       try {
           ObjectMapper mapper = new ObjectMapper();
           Map<String, String> parameters = mapper.readValue(body, new TypeReference<Map<String, String>>() {});
           String email = parameters.get("email");
           String password = parameters.get("password");
           Map<String, String> tokens = client.login(email, password);
           
           if(tokens != null){
               res.setStatusCode(200);
               res.setBody(tokens.toString());
           }
           else {
               res.setStatusCode(400);
               res.setBody("Please try again after sometime");
           }
       }
       catch(Exception e) {
           res.setStatusCode(500);
           res.setBody(e.getMessage());
       }
       return res;
   }

}
