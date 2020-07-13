package com.service.app.rest_spring.dto;

import java.util.Date;

public class ErrorMessage {

    private Date tiempStamp;
    private String message;

    public ErrorMessage() {
    }

    public Date getTiempStamp() {
        return tiempStamp;
    }

    public void setTiempStamp(Date tiempStamp) {
        this.tiempStamp = tiempStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "tiempStamp=" + tiempStamp +
                ", message='" + message + '\'' +
                '}';
    }
}
