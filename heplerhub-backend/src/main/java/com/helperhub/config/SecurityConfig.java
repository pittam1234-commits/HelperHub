package com.helperhub.config;

import com.helperhub.security.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
<<<<<<< HEAD
=======

>>>>>>> 34a8170f4808bd5747bff4a799badc0cdd572b62
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
<<<<<<< HEAD
=======
                // Disable CSRF
>>>>>>> 34a8170f4808bd5747bff4a799badc0cdd572b62
                .csrf(csrf -> csrf.disable())

                // Stateless Session
                .sessionManagement(session ->
<<<<<<< HEAD
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
=======
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
>>>>>>> 34a8170f4808bd5747bff4a799badc0cdd572b62
                )

                // Public Endpoints
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/error",
                                "/auth/**",
<<<<<<< HEAD
=======

>>>>>>> 34a8170f4808bd5747bff4a799badc0cdd572b62
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll()

                        .anyRequest().authenticated()
                )

<<<<<<< HEAD
=======
                // Return 401 instead of Browser Login Popup
>>>>>>> 34a8170f4808bd5747bff4a799badc0cdd572b62
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(
                                (request, response, authException) ->
                                        response.sendError(
                                                HttpServletResponse.SC_UNAUTHORIZED,
                                                "Unauthorized"
                                        )
                        )
                )

                // JWT Filter
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
