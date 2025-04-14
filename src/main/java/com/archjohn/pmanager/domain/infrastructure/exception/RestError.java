package com.archjohn.pmanager.domain.infrastructure.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RestError {
    private final String errorCode;
    private final String errorMessage;
    private final List<String> details;
    private final int statusCode;
    private final String path;
}
