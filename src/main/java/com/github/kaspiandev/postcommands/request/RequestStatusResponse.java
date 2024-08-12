package com.github.kaspiandev.postcommands.request;

import io.javalin.http.HttpResponseException;
import org.jetbrains.annotations.NotNull;

public class RequestStatusResponse extends HttpResponseException {

    public RequestStatusResponse(int status, @NotNull String message) {
        super(status, message);
    }

}
