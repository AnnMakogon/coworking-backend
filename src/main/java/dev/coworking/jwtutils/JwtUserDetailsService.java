package dev.coworking.jwtutils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import dev.coworking.entity.CredentialEntity;
import dev.coworking.repository.CredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final CredentialRepository credentialRepository;

    //проверка есть ли такой человек в базе
    @Override
    public UserDetails loadUserByUsername(String username) { // нормальное хождение в бд и проверку, username - email

        CredentialEntity credential = credentialRepository.findCredentialByEmail(username);
        if ("randomuser123".equals(username)) {   //для теста
            return new User("randomuser123", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", new ArrayList<>());
        }
        if (credential.getPassword() != null) {
            return new User(username, credential.getPassword().getPassword(),
                    createAuthorityFromRole(credential.getRole().toString()));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public Collection<? extends GrantedAuthority> createAuthorityFromRole(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    public CredentialEntity getCredentialDetails(String email) {
        return credentialRepository.findDetailsByEmail(email);
    }

}
