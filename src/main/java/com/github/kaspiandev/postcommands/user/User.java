package com.github.kaspiandev.postcommands.user;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

public class User implements ConfigurationSerializable {

    private final UserData userData;
    private final String token;

    public User(Map<String, Object> data) {
        this.userData = (UserData) data.get("user");
        this.token = (String) data.get("token");
    }

    public User(UserData userData, String token) {
        this.userData = userData;
        this.token = token;
    }

    public UserData getUser() {
        return userData;
    }

    public String getToken() {
        return token;
    }

    @NotNull
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("user", userData);
        data.put("token", token);
        return data;
    }

}
