package com.github.kaspiandev.postcommands;

import com.github.kaspiandev.postcommands.endpoint.ExecuteEndpoint;
import com.github.kaspiandev.postcommands.token.TokenSecretGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
import org.bukkit.plugin.java.JavaPlugin;

import java.security.NoSuchAlgorithmException;

public final class PostCommands extends JavaPlugin {

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

        saveConfig();

        Javalin javalin = Javalin
                .create((config) -> {
                    config.jsonMapper(new GsonMapper());
                })
                .addEndpoint(new ExecuteEndpoint())
                .start(getConfig().getString("host"), getConfig().getInt("port"));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
