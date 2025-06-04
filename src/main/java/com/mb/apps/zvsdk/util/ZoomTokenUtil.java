package com.mb.apps.zvsdk.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ZoomTokenUtil {

    @Value("${zoom.api.key}")
    private String key;

    @Value("${zoom.api.secret}")
    private String secret;

    public String generateVideoSdkJwt(String userId)
    {
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + 360000; // 1 hour

        return Jwts.builder().issuer(key).subject(userId).issuedAt(new Date(nowMillis)).expiration(new Date(expMillis)).signWith(SignatureAlgorithm.HS256, secret.getBytes()).compact();

    }
}
