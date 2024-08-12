package com.github.kaspiandev.postcommands.permission;

import com.github.kaspiandev.postcommands.user.User;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

public interface APIPermission extends ConfigurationSerializable {

    String getName();

    boolean check(User user);

    Map<String, Object> toMap();

    @NotNull
    @Override
    default Map<String, Object> serialize() {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("name", getName());
        data.putAll(toMap());
        return data;
    }

}
