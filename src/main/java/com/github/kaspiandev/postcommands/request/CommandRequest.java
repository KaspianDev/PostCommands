package com.github.kaspiandev.postcommands.request;

public abstract class CommandRequest {

    protected final String command;

    protected CommandRequest(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

}
