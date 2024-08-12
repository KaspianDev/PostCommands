package com.github.kaspiandev.postcommands.user;

import com.github.kaspiandev.postcommands.PostCommands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserFactory {

    private final PostCommands plugin;
    private final List<TokenUser> users;

    @SuppressWarnings("unchecked")
    public UserFactory(PostCommands plugin) {
        this.plugin = plugin;
        List<TokenUser> configUsers = (List<TokenUser>) plugin.getConfig().getList("users");
        this.users = (configUsers == null)
                ? new ArrayList<>()
                : configUsers;
    }

    public TokenUser addUser(User user) {
        TokenUser tokenUser = new TokenUser(user, plugin.getTokenFactory().generateToken(user));
        users.add(tokenUser);
        plugin.getConfig().set("users", users);
        return tokenUser;
    }

    public List<TokenUser> getUsers() {
        return users;
    }

    public Optional<TokenUser> findUser(String name) {
        return users.stream()
                    .filter((user) -> user.getUser().getName().equals(name))
                    .findAny();
    }

}
