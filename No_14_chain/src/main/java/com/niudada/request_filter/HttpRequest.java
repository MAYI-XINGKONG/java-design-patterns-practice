package com.niudada.request_filter;

import lombok.Getter;
import lombok.Setter;

public class HttpRequest {

    @Getter
    private String url;

    @Getter
    private String method;

    @Getter
    private String authToken;

    @Setter
    private boolean authenticated;

    @Setter
    private boolean authorized;

    private boolean valid;

    public HttpRequest(String url, String method, String authToken) {
        this.url = url;
        this.method = method;
        this.authToken = authToken;
        this.authenticated = false;
        this.authorized = false;
        this.valid = true;
    }

    public boolean isAuthenticated () {
        return authenticated;
    }

    public boolean isAuthorized () {
        return authorized;
    }

    public boolean isValid () {
        return valid;
    }

    public void setInvalid () {
        this.valid = false;
    }
}
