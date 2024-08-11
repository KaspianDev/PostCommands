package com.github.kaspiandev.postcommands.request;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerCommandRequest extends CommandRequest {

    private final String playerName;

    public PlayerCommandRequest(String playerName, String command) {
        super(RequestType.SERVER, command);
        this.playerName = playerName;
    }

    @Override
    public CommandSender getSender() {
        return Bukkit.getPlayer(playerName);
    }

}
