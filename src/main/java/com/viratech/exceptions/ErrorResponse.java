package com.viratech.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
        int status,
        String message,
        List<ObjectError> errors

) {
    public record ObjectError(String field, String message){}
}
