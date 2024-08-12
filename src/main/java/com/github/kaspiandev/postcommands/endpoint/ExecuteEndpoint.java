package com.github.kaspiandev.postcommands.endpoint;

import com.github.kaspiandev.postcommands.PostCommands;
import com.github.kaspiandev.postcommands.request.CommandRequest;
import com.github.kaspiandev.postcommands.request.RequestStatus;
import com.google.gson.JsonParseException;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.HandlerType;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.router.Endpoint;

public class ExecuteEndpoint extends Endpoint {

    public ExecuteEndpoint(PostCommands plugin) {
        super(HandlerType.POST,
                "/execute",
                (context) -> {
                    String authHeader = context.header("X-Api-Key");
                    if (authHeader == null || !authHeader.equals(plugin.getConfig().getString("secret"))) {
                        throw new UnauthorizedResponse();
                    }

                    String body = context.body();
                    try {
                        CommandRequest request = plugin.getGson().fromJson(body, CommandRequest.class);

                        RequestStatus status = request.execute(plugin);
                        throw status.getResponse();
                    } catch (JsonParseException ex) {
                        throw new BadRequestResponse("Could not parse JSON!");
                    }
                });
    }

}
