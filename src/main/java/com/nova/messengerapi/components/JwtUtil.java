package com.nova.messengerapi.components;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "69696969696969696969696969696969"; // Replace with a secure key
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    public JwtUtil() {
    }

    public String generateToken(String username) {
        try {
            JWSSigner signer = new MACSigner(SECRET_KEY.getBytes());

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(username)
                    .issueTime(new Date())
                    .expirationTime(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

            signedJWT.sign(signer);

            return signedJWT.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException("Error generating JWT token", e);
        }
    }

    public String extractUsername(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());

            if (signedJWT.verify(verifier)) {
                JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
                return claims.getSubject();
            } else {
                throw new RuntimeException("Invalid JWT token");
            }
        } catch (ParseException | JOSEException e) {
            throw new RuntimeException("Error extracting username from JWT token", e);
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
            Date expiration = claims.getExpirationTime();
            return expiration.before(new Date());
        } catch (ParseException e) {
            throw new RuntimeException("Error checking token expiration", e);
        }
    }

    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}
