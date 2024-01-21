package com.bookingapplication.bookingapp.config;

import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bookingapplication.bookingapp.domain.UserType;
import com.bookingapplication.bookingapp.dtos.UserDTO;
import com.bookingapplication.bookingapp.service.UserService;

import jakarta.annotation.PostConstruct;

@Component
public class UserAuthenticationProvider {
	
	@Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

	@Autowired
    private UserService userService;

    @PostConstruct
    protected void init() {
        // this is to avoid having the raw secret key available in the JVM
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserDTO user) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(user.getEmail())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("name", user.getName())
                .withClaim("surname", user.getSurname())
                .withClaim("address", user.getAddress())
                .withClaim("phone", user.getPhone())
                .withClaim("type", user.getType().toString())
                .withClaim("isBlocked", String.valueOf(user.getIsBlocked()))
                .sign(algorithm);
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        UserDTO user = new UserDTO(decoded.getSubject(), null, decoded.getClaim("name").asString(), decoded.getClaim("surname").asString(), decoded.getClaim("address").asString(), decoded.getClaim("phone").asString(), UserType.valueOf(decoded.getClaim("type").asString()), Boolean.valueOf(decoded.getClaim("isBlocked").asString()));

        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    public Authentication validateTokenStrongly(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);
        
        UserDTO user = userService.findByEmail(decoded.getSubject());

        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

}
