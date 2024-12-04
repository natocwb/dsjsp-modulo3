package com.nato.dsjsp_modulo3.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError{
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(String message, Instant timestamp, String path, int status) {
        super(message, timestamp, path, status);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}
