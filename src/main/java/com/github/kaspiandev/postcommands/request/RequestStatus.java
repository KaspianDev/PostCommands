package com.github.kaspiandev.postcommands.request;

import io.javalin.http.HttpStatus;

public enum RequestStatus {

    COMMAND_UNSET(HttpStatus.BAD_REQUEST, "Command is unset or empty!"),
    NO_SENDER(HttpStatus.BAD_REQUEST, "Sender is needed for this request and was not found!"),
    OK(HttpStatus.OK, "Command executed successfully!");

    private final HttpStatus httpStatus;
    private final String message;

    RequestStatus(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public RequestStatusResponse getResponse() {
        return new RequestStatusResponse(httpStatus.getCode(), message);
    }

}
