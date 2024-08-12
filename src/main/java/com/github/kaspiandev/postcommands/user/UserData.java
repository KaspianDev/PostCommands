package com.github.kaspiandev.postcommands.user;

import com.github.kaspiandev.postcommands.permission.APIPermission;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @SuppressWarnings("unchecked")
    public <T extends APIPermission> Optional<T> getPermission(Class<? extends T> permission) {
        return (Optional<T>) permissions.stream()
                                        .filter(permission::isInstance)
                                        .findFirst();
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
