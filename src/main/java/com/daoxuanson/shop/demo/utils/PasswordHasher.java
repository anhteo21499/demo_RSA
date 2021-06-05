package com.daoxuanson.shop.demo.utils;

import com.daoxuanson.shop.demo.exception.InternalServerException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHasher {
    private static final String HASH_ALGORITHM = "SHA-1";

    public static String hash(String password)  {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] digest = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new InternalServerException(e.getMessage());
        }

    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(hash("123456"));
    }
}
