package com.github.rodmotta.bracket_master.adapters.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.github.rodmotta.bracket_master.core.ports.JwtServicePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class JwtService implements JwtServicePort {
    @Value("security.secret-key")
    private String secretKey;

    public String generate(String subject) {
        try {
            return JWT.create()
                    .withSubject(subject)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) //1hr
                    .sign(Algorithm.HMAC256(secretKey));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validate(String token) {
        try {
            JWT.require(Algorithm.HMAC256(secretKey))
                    .build()
                    .verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    public String getSubject(String token) {
        return JWT.decode(token).getSubject();
    }
}
