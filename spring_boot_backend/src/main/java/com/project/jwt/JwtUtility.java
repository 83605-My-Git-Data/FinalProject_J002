package com.project.jwt;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
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
        
        
        // JWT: userName, issued at, exp date, digital signature (does not typically contain password, can contain authorities)
        return Jwts.builder() // JWTs: a Factory class used to create JWT tokens
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



















//@Component
//public class JwtUtility implements Serializable {
//	
//	
//
//	private final String jwtSecret  = "a7cG@1zdklG!9&vN$Xs(2rP%y*Fw^Kb#Lm8u3Te@Vp!o4jZ%hR";
//
//	
//	private int jwtExpirationMs = 5*60*60*100;
//	
//	
//	private Key key;
//
//	@PostConstruct
//	public void init() {
//		key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
//	}
//
//	// will be invoked by Authentication controller) , upon successful
//	// authentication
//	public String generateJwtToken(Authentication authentication) {
//		
//		
//		
//		
//		    Register userPrincipal =  (Register) authentication.getPrincipal();
////JWT : userName,issued at ,exp date,digital signature(does not typically contain password , can contain authorities
//		return Jwts.builder() // JWTs : a Factory class , used to create JWT tokens
//				.setSubject((userPrincipal.getEmail())) // setting subject part of the token(typically user
//															// name/email)
//				.setIssuedAt(new Date())// Sets the JWT Claims iat (issued at) value of current date
//				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))// Sets the JWT Claims exp
//																					// (expiration) value.
//				
//				.signWith(key, SignatureAlgorithm.HS512) // Signs the constructed JWT using the specified
//															// algorithm with the specified key, producing a
//															// JWS(Json web signature=signed JWT)
//
//				// Using token signing algo : HMAC using SHA-512
//				.compact();// Actually builds the JWT and serializes it to a compact, URL-safe string
//	}
//
//	// this method will be invoked by our custom JWT filter
//	public String getUserNameFromJwtToken(Claims claims) {
//		return claims.getSubject();
//	}
//
//	// this method will be invoked by our custom filter
//	public Claims validateJwtToken(String jwtToken) {
//		// try {
//		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().
//		// Sets the signing key used to verify JWT digital signature.
//				parseClaimsJws(jwtToken).getBody();// Parses the signed JWT returns the resulting Jws<Claims> instance
//		// throws exc in case of failures in verification
//		return claims;		
//	}
//
//	
//}
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

//    private static final long serialVersionUID = 234234523523L;
//
//    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;//five hours
//
//    
//    private String secretKey = "ilovekiara";
//
//    //retrieve username from jwt token
//    public String getUsernameFromToken(String token) {
//        return getClaimFromToken(token, Claims::getSubject);
//    }
//
//    //retrieve expiration date from jwt token
//    public Date getExpirationDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//
//
//    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = getAllClaimsFromToken(token);
//        return claimsResolver.apply(claims);
//    }
//
//
//    //for retrieving any information from token we will need the secret key
//    private Claims getAllClaimsFromToken(String token) {
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//    }
//
//
//    //check if the token has expired
//    private Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//
//
//    //generate token for user
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        return doGenerateToken(claims, userDetails.getUsername());
//    }
//
//
//    //while creating the token -
//    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
//    //2. Sign the JWT using the HS512 algorithm and secret key. token expires in 5hr
//    private String doGenerateToken(Map<String, Object> claims, String subject) {
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
//    }
//
//
//    //validate token
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = getUsernameFromToken(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }



