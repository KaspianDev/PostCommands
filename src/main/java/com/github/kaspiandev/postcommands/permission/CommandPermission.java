package com.github.kaspiandev.postcommands.permission;

import com.github.kaspiandev.postcommands.request.CommandRequest;

import java.util.Map;

public class CommandPermission implements APIPermission {

    private final String command;
    private final boolean strict;

    public CommandPermission(Map<String, Object> data) {
        this.command = (String) data.get("command");
        this.strict = (boolean) data.get("strict");
    }

    public CommandPermission(String command, boolean strict) {
        this.command = command;
        this.strict = strict;
    }

    @Override
    public String getName() {
        return "command";
    }

    @Override
    public boolean check(CommandRequest commandRequest) {
        if (command.equals("*")) return true;
        if (strict) {
            return commandRequest.getCommand().equals(command);
        } else {
            return commandRequest.getCommand().startsWith(command);
        }
    }

    @Override
    public Map<String, Object> toMap() {
        return Map.of(
                "command", command,
                "strict", strict
        );
    }

    public boolean isStrict() {
        return strict;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CommandPermission commandPermission)) return false;
        if (commandPermission.strict != strict) return false;
        return commandPermission.command.equals(command);
    }

}
