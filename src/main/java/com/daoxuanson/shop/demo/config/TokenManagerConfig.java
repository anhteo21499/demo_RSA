package com.daoxuanson.shop.demo.config;


import com.daoxuanson.shop.demo.security.jwt.TokenConsumer;
import com.daoxuanson.shop.demo.security.jwt.TokenProducer;
import com.daoxuanson.shop.demo.security.jwt.util.KeyReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Configuration
public class TokenManagerConfig {

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.subject}")
    private String subject;

    @Value("${jwt.audience}")
    private String audience;

    @Value("#{'${jwt.audiences}'.split(',')}")
    private List<String> audiences;

    @Value("${jwt.expiration}")
    private float expirationInMin;

    @Value("${jwt.notBefore}")
    private float notBeforeInMin;

    @Value("${jwt.privateKey}")
    private String privateKeyPath;

    @Value("${jwt.publicKey}")
    private String publicKeyPath;


    @Bean
    public TokenProducer tokenProducer() {
        return createTokenProducer(expirationInMin);

    }

    private TokenProducer createTokenProducer(float time) {
        try {
            PrivateKey privateKey = KeyReader.getPrivateKey(privateKeyPath);
            return new TokenProducer(issuer, subject, audiences.stream().toArray(size -> new String[size]),
                    time, notBeforeInMin, privateKey);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | IOException e) {
            return null;
        }
    }

    @Bean
    public TokenConsumer tokenConsumer() {
        try {
            PublicKey publicKey = KeyReader.getPublicKey(publicKeyPath);
            return new TokenConsumer(audience, publicKey);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | IOException e) {
            return null;
        }
    }
}
