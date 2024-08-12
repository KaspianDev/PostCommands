package com.github.kaspiandev.postcommands.user;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class TokenUser implements ConfigurationSerializable {

    private final User user;
    private final String token;

    public TokenUser(Map<String, Object> data) {
        this.user = (User) data.get("user");
        this.token = (String) data.get("token");
    }

    public TokenUser(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    @NotNull
    @Override
    public Map<String, Object> serialize() {
        return Map.of(
                "user", user,
                "token", token
        );
    }

}
