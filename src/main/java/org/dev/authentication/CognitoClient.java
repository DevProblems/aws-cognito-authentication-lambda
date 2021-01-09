package org.dev.authentication;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;

import java.util.LinkedHashMap;
import java.util.Map;

/*@author: DEVPROBLEMS(A SARANG KUMAR TAK)*/

public class CognitoClient {

    private final AWSCognitoIdentityProvider client ;
    private final String clientId = "CLIENT_ID";
    private final String userPool = "USER_POOL_ID";

    public CognitoClient() {
        client = createCognitoClient();
    }

    private AWSCognitoIdentityProvider createCognitoClient() {
        AWSCredentials cred = new BasicAWSCredentials("ACCESS_KEY", "SECRET_ACCESS_KEY");
        AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(cred);
        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(credProvider)
                .withRegion(Regions.US_WEST_2)
                .build();
    }

    public SignUpResult signUp(String name, String email, String password) {
        SignUpRequest request = new SignUpRequest().withClientId(clientId ).withUsername(email).withPassword(password);
        SignUpResult result = client.signUp(request);
        return result;
    }

    public ConfirmSignUpResult confirmSignUp(String email, String confirmationCode) {
        ConfirmSignUpRequest confirmSignUpRequest = new ConfirmSignUpRequest().withClientId(clientId).withUsername(email).withConfirmationCode(confirmationCode);
        return client.confirmSignUp(confirmSignUpRequest);
    }

    public Map<String, String> login(String email, String password) {
        Map<String, String> authParams = new LinkedHashMap<String, String>() {{
            put("USERNAME", email);
            put("PASSWORD", password);
        }};

        AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
                .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .withUserPoolId(userPool)
                .withClientId(clientId)
                .withAuthParameters(authParams);
        AdminInitiateAuthResult authResult = client.adminInitiateAuth(authRequest);
        AuthenticationResultType resultType = authResult.getAuthenticationResult();
         return new LinkedHashMap<String, String>() {{
            put("idToken", resultType.getIdToken());
            put("accessToken", resultType.getAccessToken());
            put("refreshToken", resultType.getRefreshToken());
            put("message", "Successfully login");
        }};

    }
}
