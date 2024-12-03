package dev.coworking.jwtutils;

import java.util.ArrayList;

import dev.coworking.entity.CredentialEntity;
import dev.coworking.repository.CredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final CredentialRepository credentialRepository;

    //проверка есть ли такой человек в базе
    @Override
    public UserDetails loadUserByUsername(String username) { // нормальное хождение в бд и проверку, username - email

        String password = credentialRepository.findPasswordByEmail(username);
        if ("randomuser123".equals(username)) {   //для теста
            return new User("randomuser123", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", new ArrayList<>());
        }
        if(password != null ) {
            return new User(username, password, new ArrayList<>());
        } else {
            throw  new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
