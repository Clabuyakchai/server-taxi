package com.clabuyakchai.api.security;

import com.clabuyakchai.api.configuration.ClassAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //TODO
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
        http.authorizeRequests()//
                .antMatchers("/local/signin").permitAll()
                .antMatchers("/local/signup").permitAll()
                .antMatchers("/staff/signin").permitAll()
                .antMatchers("/staff/signup").permitAll()
//                .antMatchers("/station/*").permitAll()
//                .antMatchers("/route/*").permitAll()
                .antMatchers("/staff/*").permitAll()
//                .antMatchers("/book/*").permitAll()

//            .antMatchers("/userpage").permitAll()
                // Disallow everything else..
                .anyRequest().authenticated();

        // If a user try to access a resource without having enough permissions
        http.exceptionHandling().accessDeniedPage("/local");

        // Apply JWT
        //TODO
        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

        // Optional, if you want to findTimetableByDatetimeAndNameDriver the API from a browser
        // http.httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        return new ClassAuthenticationManager();
    }
}
