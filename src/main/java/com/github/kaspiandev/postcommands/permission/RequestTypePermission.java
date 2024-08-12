package com.github.kaspiandev.postcommands.permission;

import com.github.kaspiandev.postcommands.request.RequestType;
import com.github.kaspiandev.postcommands.user.UserData;

import java.util.Map;

public class RequestTypePermission implements APIPermission {

    private final RequestType requestType;

    public RequestTypePermission(Map<String, Object> data) {
        this.requestType = RequestType.valueOf((String) data.get("request-type"));
    }

    public RequestTypePermission(RequestType requestType) {
        this.requestType = requestType;
    }

    @Override
    public String getName() {
        return "request-type";
    }

    @Override
    public boolean check(UserData userData) {
        return userData.getPermission(this).isPresent();
    }

    @Override
    public Map<String, Object> toMap() {
        return Map.of(
                "request-type", getRequestType().name()
        );
    }

    public RequestType getRequestType() {
        return requestType;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RequestTypePermission requestTypePermission)) return false;
        return requestTypePermission.getRequestType() == getRequestType();
    }

}
