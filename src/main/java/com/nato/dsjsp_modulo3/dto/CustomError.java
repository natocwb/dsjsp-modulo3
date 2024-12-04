package com.nato.dsjsp_modulo3.dto;

import java.time.Instant;

public class CustomError {

    private Instant timestamp;
    private String error;
    private String path;
    private Integer status;

    public CustomError(String error, Instant timestamp, String path, Integer status) {
        this.error = error;
        this.timestamp = timestamp;
        this.path = path;
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }

    public Integer getStatus() {
        return status;
    }
}
