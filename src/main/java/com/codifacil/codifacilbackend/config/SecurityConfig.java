package com.codifacil.codifacilbackend.config;

import com.codifacil.codifacilbackend.models.entity.User;
import com.codifacil.codifacilbackend.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import java.util.Optional;

@Configuration
public class SecurityConfig {

    /*@Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private LogoutHandler logoutHandler;*/

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    //Configuración que permite filtrar las solicitudes que requieren autenticación, las que no requieren autenticación, setear el interceptor, etc
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests()

                .requestMatchers(
                        "/api/auth/**",
                        "/api/category/**",
                        "/api/quicktext/**",
                        "/api/quicktext/calculate/**",
                        "/user/create",
                        "/swagger-ui/**"
                )
                .permitAll()

                .anyRequest().authenticated()

                .and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)

                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                //.authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

                /*.logout()
                .logoutUrl("/api/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler( ((request, response, authentication) -> SecurityContextHolder.clearContext()) )*/

        return httpSecurity.build();
    }

}
