package com.github.kaspiandev.postcommands.request;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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

    public abstract CommandSender getSender();

    public void execute() {
        Bukkit.dispatchCommand(getSender(), command);
    }

}
