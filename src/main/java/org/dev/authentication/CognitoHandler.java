package org.dev.authentication;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/*@author: DEVPROBLEMS(A SARANG KUMAR TAK)*/

public class CognitoHandler implements RequestHandler<Request, Response> {

    @Override
    public Response handleRequest(Request request, Context context) {
        return new Service().authentication(request);
    }
}
