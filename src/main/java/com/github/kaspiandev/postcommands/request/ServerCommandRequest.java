package com.github.kaspiandev.postcommands.request;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Optional;

public class ServerCommandRequest extends CommandRequest {

    public ServerCommandRequest(String command) {
        super(RequestType.SERVER, command);
    }

    @Override
    public Optional<CommandSender> getSender() {
        return Optional.of(Bukkit.getConsoleSender());
    }

}
