package com.archjohn.pmanager.domain.exeception;

import com.archjohn.pmanager.domain.infrastructure.exception.RequestException;

public class ProjectNotFoundException extends RequestException {
    public ProjectNotFoundException(String projectId) {
        super("ProjectNotFound", "Project not found: " + projectId);
    }
}
