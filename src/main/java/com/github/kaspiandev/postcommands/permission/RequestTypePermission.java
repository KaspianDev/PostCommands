package com.github.kaspiandev.postcommands.permission;

import com.github.kaspiandev.postcommands.request.RequestType;
import com.github.kaspiandev.postcommands.user.ApiUser;

public class RequestTypePermission implements APIPermission {

    private final RequestType requestType;

    public RequestTypePermission(RequestType requestType) {
        this.requestType = requestType;
    }

    @Override
    public String getName() {
        return "requestType";
    }

    @Override
    public boolean check(ApiUser user) {
        return user.getPermission(this).isPresent();
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
