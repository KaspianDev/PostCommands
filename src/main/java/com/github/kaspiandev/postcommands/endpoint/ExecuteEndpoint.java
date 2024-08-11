package com.github.kaspiandev.postcommands.endpoint;

import com.github.kaspiandev.postcommands.PostCommands;
import com.github.kaspiandev.postcommands.request.CommandRequest;
import com.github.kaspiandev.postcommands.request.RequestStatus;
import com.google.gson.JsonSyntaxException;
import io.javalin.http.HandlerType;
import io.javalin.http.HttpResponseException;
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

                        Bukkit.getScheduler().runTask(plugin, () -> {
                            RequestStatus status = request.execute();
                            throw status.getException();
                        });
                    } catch (JsonSyntaxException ex) {
                        throw new HttpResponseException(400, "Could not parse JSON!");
                    }
                });
    }

}
