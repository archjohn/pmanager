package com.archjohn.pmanager.domain.infrastructure.exception;

public class DuplicateMemberException extends RequestException {

    public DuplicateMemberException(String name) {
        super("MemberDuplicate", "A member with the email already exists: " + name);
    }
}
