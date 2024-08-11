package com.github.kaspiandev.postcommands.endpoint;

import com.github.kaspiandev.postcommands.PostCommands;
import com.github.kaspiandev.postcommands.request.CommandRequest;
import com.google.gson.JsonSyntaxException;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.HandlerType;
import io.javalin.http.HttpStatus;
import io.javalin.router.Endpoint;
import org.bukkit.Bukkit;

public class ExecuteEndpoint extends Endpoint {

    public ExecuteEndpoint(PostCommands plugin) {
        super(HandlerType.POST,
                "/execute",
                (context) -> {
                    String body = context.body();
                    try {
                        CommandRequest request = plugin.getGson().fromJson(body, CommandRequest.class);
                        if (request == null) {
                            context.status(HttpStatus.BAD_REQUEST);
                            return;
                        }

                        Bukkit.getScheduler().runTask(plugin, () -> {
                            if (request.execute()) {
                                context.status(HttpStatus.OK);
                            } else {
                                context.status(HttpStatus.BAD_REQUEST);
                            }
                        });
                    } catch (JsonSyntaxException ex) {
                        context.status(HttpStatus.BAD_REQUEST);
                    }
                });
    }

}
