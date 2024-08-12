package com.github.kaspiandev.postcommands;

import com.github.kaspiandev.postcommands.endpoint.ExecuteEndpoint;
import com.github.kaspiandev.postcommands.permission.RequestTypePermission;
import com.github.kaspiandev.postcommands.request.CommandRequest;
import com.github.kaspiandev.postcommands.request.RequestDeserializer;
import com.github.kaspiandev.postcommands.request.RequestType;
import com.github.kaspiandev.postcommands.token.TokenSecretGenerator;
import com.github.kaspiandev.postcommands.user.ApiUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
import org.bukkit.plugin.java.JavaPlugin;

import java.security.NoSuchAlgorithmException;
import java.util.Set;

public final class PostCommands extends JavaPlugin {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(CommandRequest.class, new RequestDeserializer())
            .create();

    @Override
    public void onEnable() {
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

        getConfig().set("a", new ApiUser("kaspian", Set.of(new RequestTypePermission(RequestType.SERVER))));

        saveConfig();

        Javalin javalin = Javalin
                .create()
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

}
