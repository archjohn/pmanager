package com.archjohn.pmanager.domain.exeception;

import com.archjohn.pmanager.domain.infrastructure.exception.RequestException;

public class DuplicateProjectException  extends RequestException {


    public DuplicateProjectException(String name) {
        super("ProjectDuplicate", "A project with the name already exists: " + name );
    }
}
