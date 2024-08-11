package com.github.kaspiandev.postcommands;

import io.javalin.Javalin;
import org.bukkit.plugin.java.JavaPlugin;

public final class PostCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        Javalin javalin = Javalin.create()
                .start();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
