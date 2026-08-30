package com.jobportal.job_portal.config;




import com.jobportal.job_portal.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    WebSecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        http

        .csrf(csrf->csrf.disable())

        .authorizeHttpRequests(auth->auth

            .requestMatchers("/api/auth/**").permitAll()

            .anyRequest().authenticated()
        )
         .sessionManagement(session->session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         )

         .addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);
         return http.build();
    }
    
}
