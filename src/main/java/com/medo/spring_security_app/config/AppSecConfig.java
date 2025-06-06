package com.medo.spring_security_app.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Marks this class as a source of bean definitions for the application context
@EnableWebSecurity
public class AppSecConfig {

    // Defines an in-memory user details service with a default user
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // Creates a user with username 'user' and password 'password', role 'USER'
        manager.createUser(
            User.withUsername("ahmed")
                .password(passwordEncoder.encode("1234")) // Password is encoded
                .roles("USER")
                .build()
        );
        manager.createUser(
            User.withUsername("yasser")
                .password(passwordEncoder.encode("0000")) // Password is encoded
                .roles("USER")
                .build()
        );
        
        return manager;
    }

    // Defines the password encoder bean using BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configures HTTP security for the application
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated()) // All requests require authentication
            .formLogin(); // Enables form-based login
        return http.build();
    }
}

