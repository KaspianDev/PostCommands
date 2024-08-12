package com.github.kaspiandev.postcommands.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.kaspiandev.postcommands.PostCommands;
import com.github.kaspiandev.postcommands.user.UserData;

import java.util.Objects;

public class TokenFactory {

    private final PostCommands plugin;
    private final Algorithm algorithm;

    public TokenFactory(PostCommands plugin) {
        this.plugin = plugin;
        this.algorithm = Algorithm.HMAC256(Objects.requireNonNull(plugin.getConfig().getString("secret")));
    }

    public String generateToken(UserData userData) {
        return JWT.create()
                  .withSubject(userData.getName())
                  .sign(algorithm);
    }

    public DecodedJWT verify(String token) {
        JWTVerifier verifier = JWT.require(algorithm)
                                  .build();
        return verifier.verify(token);
    }

}
