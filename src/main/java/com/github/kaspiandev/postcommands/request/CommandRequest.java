package com.github.kaspiandev.postcommands.request;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.Optional;

public abstract class CommandRequest {

    protected final RequestType type;
    protected final String command;

    protected CommandRequest(RequestType type, String command) {
        this.type = type;
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public RequestType getType() {
        return type;
    }

    public abstract Optional<CommandSender> getSender();

    public boolean execute() {
        return getSender().map((sender) -> {
            Bukkit.dispatchCommand(sender, command);
            return true;
        }).orElse(false);
    }

}
