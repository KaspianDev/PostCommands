package com.github.kaspiandev.postcommands.user;

import com.github.kaspiandev.postcommands.PostCommands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserFactory {

    private final PostCommands plugin;
    private final List<User> users;

    @SuppressWarnings("unchecked")
    public UserFactory(PostCommands plugin) {
        this.plugin = plugin;
        List<User> configUsers = (List<User>) plugin.getConfig().getList("users");
        this.users = (configUsers == null)
                ? new ArrayList<>()
                : configUsers;
    }

    public User addUser(UserData userData) {
        User user = new User(userData, plugin.getTokenFactory().generateToken(userData));
        users.add(user);
        plugin.getConfig().set("users", users);
        return user;
    }

    public List<User> getUsers() {
        return users;
    }

    public Optional<User> findUser(String name) {
        return users.stream()
                    .filter((user) -> user.getUser().getName().equals(name))
                    .findAny();
    }

}
