package com.example.library_management.config;

import com.example.library_management.filter.JwtAuthFilter;
import com.example.library_management.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigClass {
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req.requestMatchers("librarian/login/**", "student/login/**", "/error/**").permitAll()
//                        .requestMatchers("api/books/create/**","api/books/update/**","api/books/delete/**", "/api/register/student/**").hasAnyAuthority("LIBRARIAN")
//                        .requestMatchers("api/books/findBooksByAuthor/**",
//                                "api/books/findBooksByTitle", "api/books/findBooksByIsbn").hasAnyAuthority("STUDENT", "LIBRARIAN")
                        .anyRequest().authenticated())

//                .userDetailsService(
//                        customUserService
//                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}

