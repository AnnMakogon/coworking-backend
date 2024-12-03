package dev.coworking.jwtutils;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class JwtRequestModel implements Serializable{

    private static final long serialVersionUID = 2636936156391265891L;
    private String username;
    private String password;
    public JwtRequestModel(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
}
