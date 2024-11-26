package dev.coworking.config;

import dev.coworking.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/api/login/**", "/api/logout").permitAll()
                .antMatchers("/api/bookingTable/**", "/api/booking/**",
                        "/api/tableGet/**", "/api/workspaceGet/**").hasAnyAuthority(Role.CUSTOMER.name(), Role.MANAGER.name())
                .antMatchers("/api/manager/**", "/api/tablePut/**",
                        "/api/tableDel/**",
                        "/api/workspaceManager/**", "/api/workspacePut/**",
                        "/api/workspaceDel/**").hasAuthority(Role.MANAGER.name())
                .antMatchers("/api/bookingPers/**", "/api/bookingUpdateDate/**",
                        "/api/customer/**", "/api/workspaceConcrete/**").hasAuthority(Role.CUSTOMER.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response,
                                         AuthenticationException authException) {
                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    }
                })
                .and()
                .csrf().disable()
                .cors().disable();

        return http.build();
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery(
                        """
                                SELECT c.email AS username, p.password AS passwords, 1 AS enable 
                                FROM credentials AS c 
                                JOIN passwords AS p ON c.password_id = p.id 
                                WHERE c.email = ? 
                                """
                )
                .authoritiesByUsernameQuery(
                        """
                                 SELECT email, role from credentials where email=?
                                """
                );
    }

    /*@Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery(
                        """
                                SELECT m.fio AS username, p.password AS password, 'MANAGER' AS role 
                                FROM managers AS m 
                                JOIN credentials AS c ON m.credential_id = c.id 
                                JOIN passwords AS p ON c.password_id = p.id 
                                WHERE n.fio = ? 
                                UNION 
                                SELECT c.fio AS username, p.password AS password, 'CUSTOMER' AS role 
                                FROM customers AS c 
                                JOIN credentials AS cr ON c.credential_id = cr.id 
                                JOIN passwords AS p ON cr.password_id = p.id 
                                WHERE c.fio = ? 
                                """)
                .authoritiesByUsernameQuery(
                        """
                                SELECT m.fio AS username, 'MANAGER' AS role 
                                FROM managers AS m 
                                JOIN credentials AS c ON m.credential_id = c.id 
                                WHERE m.fio = ? 
                                UNION 
                                SELECT c.fio AS username, 'CUSTOMER' AS role 
                                FROM customers AS c 
                                JOIN credentials AS cr ON c.credential_id = cr.id 
                                WHERE c.fio = ? 
                                """);
    }*/
}
