package com.project.jwt;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.project.dao.RegisterDao;
import com.project.entities.Register;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;



@Component
public class JwtHelper {
	
	
	@Autowired
	RegisterDao registerDao;

    private String SECRET_KEY = "GeneratedBase64EncodedSecretKeyadityaisverygGoodPerson";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        
    
        Register register =  registerDao.findByEmail(userDetails.getUsername());
        claims.put("id", register.getId());
        claims.put("Role", register.getRole());
        claims.put("Name", register.getFirstName()+" "+register.getLastName());
       
       
        return createToken(claims, userDetails.getUsername());
    }
    
    

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
