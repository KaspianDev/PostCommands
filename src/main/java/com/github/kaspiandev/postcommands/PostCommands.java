package com.github.kaspiandev.postcommands;

import com.github.kaspiandev.postcommands.endpoint.ExecuteEndpoint;
import com.github.kaspiandev.postcommands.permission.APIPermission;
import com.github.kaspiandev.postcommands.permission.RequestTypePermission;
import com.github.kaspiandev.postcommands.request.CommandRequest;
import com.github.kaspiandev.postcommands.request.RequestDeserializer;
import com.github.kaspiandev.postcommands.request.RequestType;
import com.github.kaspiandev.postcommands.token.TokenFactory;
import com.github.kaspiandev.postcommands.token.TokenSecretGenerator;
import com.github.kaspiandev.postcommands.user.User;
import com.github.kaspiandev.postcommands.user.UserData;
import com.github.kaspiandev.postcommands.user.UserFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public final class PostCommands extends JavaPlugin {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(CommandRequest.class, new RequestDeserializer())
            .create();
    private UserFactory userFactory;
    private TokenFactory tokenFactory;

    @Override
    public void onEnable() {
        ConfigurationSerialization.registerClass(APIPermission.class);
        ConfigurationSerialization.registerClass(RequestTypePermission.class);
        ConfigurationSerialization.registerClass(UserData.class);
        ConfigurationSerialization.registerClass(User.class);

        getConfig().options().copyDefaults(true);

        if (!getConfig().isSet("secret")) {
            try {
                getConfig().addDefault("secret", TokenSecretGenerator.generate());
            } catch (NoSuchAlgorithmException e) {
                getLogger().severe("Failed to generate a secure secret token! Disabling.");
                getServer().getPluginManager().disablePlugin(this);
                return;
            }
        }

        tokenFactory = new TokenFactory(this);

        userFactory = new UserFactory(this);
        if (userFactory.getUsers().isEmpty()) {
            userFactory.addUser(new UserData("admin", List.of(new RequestTypePermission(RequestType.SERVER))));
        }

        saveConfig();

        Javalin.create()
               .addEndpoint(new ExecuteEndpoint(this))
               .start(getConfig().getString("host"), getConfig().getInt("port"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Gson getGson() {
        return gson;
    }

    public TokenFactory getTokenFactory() {
        return tokenFactory;
    }

    public UserFactory getUserFactory() {
        return userFactory;
    }

}
