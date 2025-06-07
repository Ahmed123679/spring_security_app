package com.medo.spring_security_app.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.medo.spring_security_app.service.CustomUserDetailsService;

@Configuration // Marks this class as a source of bean definitions for the application context
@EnableWebSecurity
public class AppSecConfig {


    @Autowired
    CustomUserDetailsService userDetailsService;

    // Defines an in-memory user details service with a default user
    // @Bean
    // public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
    //     InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    //     // Creates a user with username 'user' and password 'password', role 'USER'
    //     manager.createUser(
    //         User.withUsername("ahmed")
    //             .password(passwordEncoder.encode("1234")) // Password is encoded
    //             .roles("USER")
    //             .build()
    //     );
    //     manager.createUser(
    //         User.withUsername("yasser")
    //             .password(passwordEncoder.encode("0000")) // Password is encoded
    //             .roles("USER")
    //             .build()
    //     );
        
    //     return manager;
    // }

    // Defines the password encoder bean using BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Uses BCrypt for password encoding
    }

    // Configures HTTP security for the application
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disables CSRF protection for simplicity
                .authorizeHttpRequests(auth -> auth.requestMatchers("/login","login-error", "/css/**", "/js/**").permitAll().anyRequest().authenticated())
                .userDetailsService(userDetailsService) // All requests require authentication
                .formLogin(form -> form
                                .loginPage("/login")
                                .permitAll() // Allows all users to access the login page
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/")
                                .failureUrl("/login-error")
                ).logout(log -> log
                                
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/logout-sucess")
                                .invalidateHttpSession(true)
                                
                                .clearAuthentication(true)
                                .permitAll()
                ); // Enables form-based login
        return http.build();
    }
}

