package com.github.kaspiandev.postcommands;

import com.github.kaspiandev.postcommands.request.CommandRequest;
import com.github.kaspiandev.postcommands.request.RequestDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.json.JsonMapper;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

public class GsonMapper implements JsonMapper {

    private final Gson gson;

    public GsonMapper(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String toJsonString(@NotNull Object obj, @NotNull Type type) {
        return gson.toJson(obj, type);
    }

    @Override
    public <T> T fromJsonString(@NotNull String json, @NotNull Type targetType) {
        return gson.fromJson(json, targetType);
    }

}
