package com.github.kaspiandev.postcommands.endpoint;

import com.github.kaspiandev.postcommands.PostCommands;
import com.github.kaspiandev.postcommands.request.CommandRequest;
import io.javalin.http.HandlerType;
import io.javalin.router.Endpoint;
import org.bukkit.Bukkit;

public class ExecuteEndpoint extends Endpoint {

    public ExecuteEndpoint(PostCommands plugin) {
        super(HandlerType.POST,
                "/execute",
                (context) -> {
                    CommandRequest request = context.bodyAsClass(CommandRequest.class);
                    Bukkit.getScheduler().runTask(plugin, request::execute);
                });
    }

}
