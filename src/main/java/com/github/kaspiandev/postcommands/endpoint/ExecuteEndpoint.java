package com.github.kaspiandev.postcommands.endpoint;

import io.javalin.http.HandlerType;
import io.javalin.router.Endpoint;

public class ExecuteEndpoint extends Endpoint {

    public ExecuteEndpoint() {
        super(HandlerType.POST,
                "/execute/{command}",
                (context) -> {
                    context.bodyAsClass();
                });
    }

}
