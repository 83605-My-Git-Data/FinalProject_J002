package com.project.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.spring_security.UserDetailsService;

import io.jsonwebtoken.Claims;


@Component
public class JwtFilter  extends OncePerRequestFilter{
	
	
	
	
	
	
	
	
	@Autowired
	private JwtUtility utils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// check auth header from incoming request
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			// => req header contains JWT
			String jwt = authHeader.substring(7);
			
			System.out.println(jwt);
			// validate JWT
			Claims payloadClaims = utils.validateJwtToken(jwt);
			
			System.out.println(payloadClaims);
			// get user name from the claims
			String email = utils.getUserNameFromJwtToken(payloadClaims);
			
			System.out.println(email);
			
			// add username/email n granted authorities in Authentication object
			UsernamePasswordAuthenticationToken token = 
					new UsernamePasswordAuthenticationToken(email,null);
			// save this auth token under spring sec so that subsequent filters will NOT
			// retry the auth again
			SecurityContextHolder.getContext().setAuthentication(token);
			System.out.println("saved auth token in sec ctx");
		}
		filterChain.doFilter(request, response);// to continue with remaining chain of spring sec filters

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Autowired
//	private JwtUtility jwtUtility;
//	
//	@Autowired
//	private UserDetailsService userDetailsService;
//	
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		String authorization = request.getHeader("Authorization");
//        String token = null;
//        String userName = null;
//
//        if(null != authorization && authorization.startsWith("Bearer ")) {
//            token = authorization.substring(7);
//            userName = jwtUtility.getUsernameFromToken(token);
//        }
//
//        if(null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails
//                    = userDetailsService.loadUserByUsername(userName);
//
//            if(jwtUtility.validateToken(token,userDetails)) {
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
//                        = new UsernamePasswordAuthenticationToken(userDetails,
//                        null, userDetails.getAuthorities());
//
//                usernamePasswordAuthenticationToken.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(request)
//                );
//
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//
//        }
//        filterChain.doFilter(request, response);
//    }
//
//		
	}


