package org.dev.authentication;


import java.util.Map;

/*@author: DEVPROBLEMS(A SARANG KUMAR TAK)*/

public class Response {

    private int statusCode;
    private String body;
    private Map<String, String> headers;

    public Response(){}

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
