package com.github.kaspiandev.postcommands.permission;

import java.util.ArrayList;
import java.util.List;

public class PermissionRegistry {

    private static final List<Class<? extends APIPermission>> REGISTRY = new ArrayList<>();

    static {
        REGISTRY.add(CommandPermission.class);
        REGISTRY.add(RequestTypePermission.class);
    }

    public static void register(Class<? extends APIPermission> permission) {
        REGISTRY.add(permission);
    }

    public static List<Class<? extends APIPermission>> getRegistry() {
        return REGISTRY;
    }

    private PermissionRegistry() {}

}
