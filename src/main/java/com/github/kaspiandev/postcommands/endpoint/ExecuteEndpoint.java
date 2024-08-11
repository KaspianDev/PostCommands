package com.github.kaspiandev.postcommands.endpoint;

import com.github.kaspiandev.postcommands.request.CommandRequest;
import io.javalin.http.HandlerType;
import io.javalin.router.Endpoint;

public class ExecuteEndpoint extends Endpoint {

    public ExecuteEndpoint() {
        super(HandlerType.POST,
                "/execute/{command}",
                (context) -> {
                    CommandRequest request = context.bodyAsClass(CommandRequest.class);
                });
    }

}
