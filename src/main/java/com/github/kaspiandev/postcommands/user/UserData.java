package com.github.kaspiandev.postcommands.user;

import com.github.kaspiandev.postcommands.permission.APIPermission;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserData implements ConfigurationSerializable {

    private final String name;
    private final List<APIPermission> permissions;

    @SuppressWarnings("unchecked")
    public UserData(Map<String, Object> data) {
        this.name = (String) data.get("name");
        this.permissions = (List<APIPermission>) data.get("permissions");
    }

    public UserData(String name, List<APIPermission> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public List<APIPermission> getPermissions() {
        return permissions;
    }

    public <T extends APIPermission> List<T> getPermissionsOfType(Class<T> permission) {
        return permissions.stream()
                          .filter(permission::isInstance)
                          .map(permission::cast)
                          .toList();
    }

    @NotNull
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("name", name);
        data.put("permissions", getPermissions());
        return data;
    }

}
