package com.github.kaspiandev.postcommands.endpoint;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.kaspiandev.postcommands.PostCommands;
import com.github.kaspiandev.postcommands.permission.RequestTypePermission;
import com.github.kaspiandev.postcommands.request.CommandRequest;
import com.github.kaspiandev.postcommands.request.RequestStatus;
import com.github.kaspiandev.postcommands.user.User;
import com.github.kaspiandev.postcommands.user.UserData;
import com.google.gson.JsonParseException;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.HandlerType;
import io.javalin.http.Header;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.router.Endpoint;

import java.util.Optional;

public class ExecuteEndpoint extends Endpoint {

    private static final String AUTH_SCHEME = "Bearer ";

    public ExecuteEndpoint(PostCommands plugin) {
        super(HandlerType.POST,
                "/execute",
                (context) -> {
                    String authHeader = context.header(Header.AUTHORIZATION);
                    if (authHeader == null || !authHeader.startsWith(AUTH_SCHEME)) throw new UnauthorizedResponse();

                    String authToken = authHeader.substring(AUTH_SCHEME.length());
                    try {
                        DecodedJWT decodedJWT = plugin.getTokenFactory().verify(authToken);

                        Optional<User> user = plugin.getUserManager().findUser(decodedJWT.getSubject());
                        if (user.isEmpty()) throw new UnauthorizedResponse();

                        UserData userData = user.get().getUser();

                        String body = context.body();
                        try {
                            Optional<RequestTypePermission> typePermission = userData.getPermission(RequestTypePermission.class);
                            if (typePermission.isEmpty()) throw new UnauthorizedResponse();

                            CommandRequest request = plugin.getGson().fromJson(body, CommandRequest.class);
                            if (!typePermission.get().check(request)) throw new UnauthorizedResponse();

                            RequestStatus status = request.execute(plugin);
                            throw status.getResponse();
                        } catch (JsonParseException ex) {
                            throw new BadRequestResponse("Could not parse JSON!");
                        }
                    } catch (JWTVerificationException ex) {
                        throw new UnauthorizedResponse();
                    }
                });
    }

}
