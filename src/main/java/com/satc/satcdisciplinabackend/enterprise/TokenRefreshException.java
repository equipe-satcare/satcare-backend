package com.satc.satcdisciplinabackend.enterprise;

public class TokenRefreshException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TokenRefreshException (String token, String message) {
        super(String.format("Failed [%s]: $s", token, message));
    }
}
