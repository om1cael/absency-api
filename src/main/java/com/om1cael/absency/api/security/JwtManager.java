package com.om1cael.absency.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class JwtManager {

    @Value("{jwt.secret}")
    private String secret;

    private final String issuer = "absency-api";
    private final int HOUR_IN_SECONDS = 3600;

    public String createToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer(this.issuer)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(HOUR_IN_SECONDS * 24))
                .sign(this.getAlgorithm());
    }

    public String getSubject(String token) {
        JWTVerifier verifier =
                JWT.require(this.getAlgorithm())
                    .withIssuer(this.issuer)
                    .build();

        return verifier.verify(token).getSubject();
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(this.secret);
    }
}
