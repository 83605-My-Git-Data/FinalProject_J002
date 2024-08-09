package com.project.jwt;

import java.io.Serializable;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.project.entities.Register;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtility implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private final String jwtSecret = "a7cG@1zdklG!9&vN$Xs(2rP%y*Fw^Kb#Lm8u3Te@Vp!o4jZ%hRa7cG@1zdklG!9&vN$Xs(2rP%y*Fw^Kb#Lm8u3Te@Vp!o4jZ%hR";
    private int jwtExpirationMs = 5 * 60 * 60 * 1000; // 5 hours in milliseconds
    private Key key;

    @PostConstruct
    public void init() {
        // Generates a key from the secret string for HS512
    	key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    	
    	System.out.println("key used genereated: "+key);
    }

    // Will be invoked by Authentication controller upon successful authentication
    public String generateJwtToken(Authentication authentication) {
        Register userPrincipal = (Register) authentication.getPrincipal();
        
        System.out.println("key used to sign in: "+key);
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userPrincipal.getId());
        claims.put("Role", userPrincipal.getRole());// id of user is stored
        claims.put("Name", userPrincipal.getFirstName()+" "+userPrincipal.getLastName());
        
        // JWT: userName, issued at, exp date, digital signature (does not typically contain password, can contain authorities)
        return Jwts.builder() // JWTs: a Factory class used to create JWT tokens
        		.setClaims(claims)
                .setSubject(userPrincipal.getEmail()) // Setting subject part of the token (typically user name/email)
                .setIssuedAt(new Date()) // Sets the JWT Claims iat (issued at) value of current date
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // Sets the JWT Claims exp (expiration) value
                .signWith(key, SignatureAlgorithm.HS512) // Signs the constructed JWT using the specified algorithm with the specified key, producing a JWS (Json web signature = signed JWT)
                .compact(); // Actually builds the JWT and serializes it to a compact, URL-safe string
    }

    // This method will be invoked by our custom JWT filter
    public String getUserNameFromJwtToken(Claims claims) {
        return claims.getSubject();
    }
      

	private String getAuthoritiesInString(Collection<? extends GrantedAuthority> authorities) {
		String authorityString = authorities.stream().
				map(authority -> authority.getAuthority())
				.collect(Collectors.joining(","));
		System.out.println(authorityString);
		return authorityString;
	}
	// this method will be invoked by our custom JWT filter to get list of granted authorities n store it in auth token
		public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims) {
		String authString = (String) claims.get("authorities");
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authString);
		authorities.forEach(System.out::println);
		return authorities;
	}
	// this method will be invoked by our custom JWT filter to get user id n store it in auth token
			public Long getUserIdFromJwtToken(Claims claims) {
				return Long.valueOf((int)claims.get("id"));			
			}
			
			public Authentication populateAuthenticationTokenFromJWT(String jwt) {
				// validate JWT n retrieve JWT body (claims)
				Claims payloadClaims = validateJwtToken(jwt);
				// get user name from the claims
				String email = getUserNameFromJwtToken(payloadClaims);
				// get granted authorities as a custom claim
				List<GrantedAuthority> authorities = getAuthoritiesFromClaims(payloadClaims);
				// get userId as the custom claim		
				Long userId=getUserIdFromJwtToken(payloadClaims);
				// add user name/email , user id n  granted authorities in Authentication object
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email,userId,
						authorities);
				System.out.println("is authenticated "+token.isAuthenticated());
				return token;
		
			}
     
  

    // This method will be invoked by our custom filter
    public Claims validateJwtToken(String jwtToken) {
    	
    	System.out.println("hiiiii");
    	
    	System.out.println("key used to validate: "+key);
    	
    	
    	System.out.println("inside validate: "+Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody());
    	
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody(); // Parses the signed JWT and returns the resulting Jws<Claims> instance
    }

  

    // Method to parse JWT
    public Claims parseJwt(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }
}


