package org.davideviscogliosi.devchecklist.dto;

import lombok.Data;

public class OutcomeDTO<T> {

    private String message;
    private T body;
    private String error;

    public OutcomeDTO() {
    }

    public OutcomeDTO(String message, T body, String error) {
        this.message = message;
        this.body = body;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}