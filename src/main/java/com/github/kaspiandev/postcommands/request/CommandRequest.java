package com.github.kaspiandev.postcommands.request;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

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

    public RequestStatus execute(Plugin plugin) {
        return getSender().map((sender) -> {
            if (command == null || command.isEmpty()) {
                return RequestStatus.COMMAND_UNSET;
            }

            if (Bukkit.isPrimaryThread()) {
                Bukkit.dispatchCommand(sender, command);
            } else {
                Bukkit.getScheduler().runTask(plugin, () -> Bukkit.dispatchCommand(sender, command));
            }
            return RequestStatus.OK;
        }).orElse(RequestStatus.NO_SENDER);
    }

}
