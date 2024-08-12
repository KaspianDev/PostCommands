package com.github.kaspiandev.postcommands.user;

import com.github.kaspiandev.postcommands.PostCommands;

import java.util.HashMap;
import java.util.Map;

public class UserFactory {

    private final PostCommands plugin;
    private final Map<String, ApiUser> users;

    public UserFactory(PostCommands plugin) {
        this.plugin = plugin;
        this.users = new HashMap<>();
    }

}
