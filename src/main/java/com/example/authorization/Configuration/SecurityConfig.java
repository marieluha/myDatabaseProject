package com.example.authorization.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.web.filter.HiddenHttpMethodFilter;

import com.example.authorization.Service.CustomUserDetailsServices;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomUserDetailsServices customUserDetailsService;


    // hashing
    @Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


    // configures web security
        @SuppressWarnings("removal")
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
            
            http.csrf().disable().authorizeHttpRequests() // defines public and auth URLs
            .requestMatchers("/register").permitAll()
            .requestMatchers("/organize", "/games", "/images/**", "/posts/**").permitAll()
            .requestMatchers("/home").permitAll().and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/home", true).permitAll()
            .and()
            .logout()
            .invalidateHttpSession(true)
             .clearAuthentication(true) // clears after logout
             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
             .logoutSuccessUrl("/login?logout").permitAll(); // redirects to logout
            
            return http.build();
            
    }



    @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}


}
