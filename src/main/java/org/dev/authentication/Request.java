package org.dev.authentication;

/*@author: DEVPROBLEMS(A SARANG KUMAR TAK)*/
public class Request {

    private String body;
    private String resource;

    public Request(){}

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
