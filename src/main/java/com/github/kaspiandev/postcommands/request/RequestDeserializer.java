package com.github.kaspiandev.postcommands.request;

import com.google.gson.*;

import java.lang.reflect.Type;

public class RequestDeserializer implements JsonDeserializer<CommandRequest> {

    @Override
    public CommandRequest deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        RequestType requestType = RequestType.valueOf(jsonObject.get("type").getAsString());

        return switch (requestType) {
            case SERVER -> context.deserialize(jsonElement, ServerCommandRequest.class);
            case PLAYER -> context.deserialize(jsonElement, PlayerCommandRequest.class);
        };
    }

}
