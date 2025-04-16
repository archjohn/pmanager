package com.archjohn.pmanager.domain.exeception;

import com.archjohn.pmanager.domain.infrastructure.exception.RequestException;

public class MemberNotFoundException extends RequestException {

    public MemberNotFoundException(String projectId) {
        super("MemberNotFound", "Member not found: " + projectId);
    }
}
