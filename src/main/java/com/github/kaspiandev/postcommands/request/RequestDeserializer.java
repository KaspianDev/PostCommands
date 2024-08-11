package com.github.kaspiandev.postcommands.request;

import com.google.gson.*;

import java.lang.reflect.Type;

public class RequestDeserializer implements JsonDeserializer<CommandRequest> {

    @Override
    public CommandRequest deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonElement typeElement = jsonObject.get("type");
        if (typeElement == null) throw new JsonParseException("'type' property isn't set.");

        String typeName = typeElement.getAsString();
        try {
            RequestType requestType = RequestType.valueOf(typeName);

            return switch (requestType) {
                case SERVER -> context.deserialize(jsonElement, ServerCommandRequest.class);
                case PLAYER -> context.deserialize(jsonElement, PlayerCommandRequest.class);
            };
        } catch (IllegalArgumentException ex) {
            throw new JsonParseException("Specified RequestType does not exist: " + typeName, ex);
        }
    }

}
