package com.github.kaspiandev.postcommands.user;

import com.github.kaspiandev.postcommands.permission.APIPermission;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ApiUser implements ConfigurationSerializable {

    private final String name;
    private final Set<APIPermission> permissions;

    public ApiUser(String name, Set<APIPermission> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public Set<APIPermission> getPermissions() {
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
                "name", getName(),
                "permissions", new ArrayList<>(getPermissions())
        );
    }

}
