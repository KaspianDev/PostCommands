package com.github.kaspiandev.postcommands.user;

import com.github.kaspiandev.postcommands.permission.APIPermission;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class User implements ConfigurationSerializable {

    private final String name;
    private final List<APIPermission> permissions;

    public User(String name, List<APIPermission> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public List<APIPermission> getPermissions() {
        return permissions;
    }

    public Optional<APIPermission> getPermission(APIPermission permission) {
        return permissions.stream()
                          .filter(permission::equals)
                          .findFirst();
    }

    @NotNull
    @Override
    public Map<String, Object> serialize() {
        return Map.of(
                "name", name,
                "permissions", getPermissions()
        );
    }

}
