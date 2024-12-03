package dev.coworking.jwtutils;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class JwtResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String token;
    public JwtResponseModel(String token){
        this.token = token;
    }
}
