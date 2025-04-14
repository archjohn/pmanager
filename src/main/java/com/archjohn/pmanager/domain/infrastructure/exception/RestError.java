package com.archjohn.pmanager.domain.infrastructure.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestError {
    private final String errorCode;
    private final String errorMessage;
    private final int statusCode;
    private final String path;
}
