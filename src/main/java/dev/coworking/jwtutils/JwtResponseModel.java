package dev.coworking.jwtutils;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class JwtResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String role;

    private final String token;

    public JwtResponseModel(Long id, String role, String token) {
        this.id = id;
        this.role = role;
        this.token = token;
    }
}
