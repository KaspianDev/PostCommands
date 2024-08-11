package com.github.kaspiandev.postcommands.token;

import com.auth0.jwt.algorithms.Algorithm;
import com.github.kaspiandev.postcommands.PostCommands;

import java.security.SecureRandom;

public class TokenFactory {

    private final PostCommands plugin;
    private final Algorithm algorithm;

    public TokenFactory(PostCommands plugin) {
        this.plugin = plugin;
        this.algorithm = Algorithm.HMAC256("");
    }



}
