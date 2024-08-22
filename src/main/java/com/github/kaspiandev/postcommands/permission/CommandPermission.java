package com.github.kaspiandev.postcommands.permission;

import com.github.kaspiandev.postcommands.request.CommandRequest;

import java.util.Map;

public class CommandPermission implements APIPermission {

    private final String command;

    public CommandPermission(Map<String, Object> data) {
        this.command = (String) data.get("command");
    }

    public CommandPermission(String command) {
        this.command = command;
    }

    @Override
    public String getName() {
        return "command";
    }

    @Override
    public boolean check(CommandRequest commandRequest) {
        if (command.equals("*")) return true;
        return commandRequest.getCommand().startsWith(command);
    }

    @Override
    public Map<String, Object> toMap() {
        return Map.of(
                "command", command
        );
    }

    public String getCommand() {
        return command;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CommandPermission commandPermission)) return false;
        return commandPermission.getCommand().equals(command);
    }

}
