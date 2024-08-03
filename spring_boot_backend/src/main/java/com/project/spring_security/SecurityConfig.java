package com.project.spring_security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.jwt.JwtFilter;

import static org.springframework.security.config.Customizer.withDefaults;

//@EnableWebSecurity//to enable spring sec frmwork support
@Configuration //to tell SC , this is config class containing @Bean methods
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//To enable method level authorization support : pre n post authorization
public class SecurityConfig {
	//dep : pwd encoder
	@Autowired
	private PasswordEncoder enc;
	//dep : custom jwt auth filter
	@Autowired
	private JwtFilter jwtFilter;
	//dep : custom auth entry point
	@Autowired
	private CustomAuthenticationEntryPoint authEntry;
	
	
	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception
	{
        //URL based authorization rules
//        http.cors(withDefaults()).
//                //disable CSRF token generation n verification
//                csrf(csrf -> csrf	.disable())
//                .exceptionHandling(handling -> handling.authenticationEntryPoint(authEntry)).
//                authorizeRequests(requests -> requests
//                .antMatchers("/signup" ,"/login",
//                        "/v*/api-doc*/**", "/swagger-ui/**").permitAll()
//                // only required for JS clnts (react / angular) : for the pre flight requests
//                .antMatchers(HttpMethod.OPTIONS).permitAll()
//                .anyRequest().authenticated())
//                //to tell spring sec : not to use HttpSession to store user's auth details
//                .sessionManagement(management -> management
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                //inserting jwt filter before sec filter
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		 http.cors(withDefaults())
         //disable CSRF token generation n verification
         .csrf(csrf -> csrf.disable())
         .exceptionHandling(handling -> handling.authenticationEntryPoint(authEntry))
         .authorizeRequests(requests -> requests
                 .anyRequest().permitAll()) // Allow all requests without authentication
         //to tell spring sec : not to use HttpSession to store user's auth details
         .sessionManagement(management -> management
                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
         //inserting jwt filter before sec filter
         .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	
		return http.build();
	}
	//configure AuthMgr as a spring bean
	@Bean
	public AuthenticationManager authenticationManager
	(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}
}
