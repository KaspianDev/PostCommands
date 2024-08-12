package com.github.kaspiandev.postcommands.token;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class TokenSecretGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();

    private TokenSecretGenerator() {}

    public static String generate() throws NoSuchAlgorithmException {
        byte[] tokenData = new byte[256];
        RANDOM.nextBytes(tokenData);

        MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");
        return Base64.getUrlEncoder().withoutPadding().encodeToString(sha256Digest.digest(tokenData));
    }

}
