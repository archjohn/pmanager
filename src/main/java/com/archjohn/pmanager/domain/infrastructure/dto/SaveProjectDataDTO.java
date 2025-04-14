package com.archjohn.pmanager.domain.infrastructure.dto;

import com.archjohn.pmanager.domain.model.ProjectStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SaveProjectDataDTO {
    private final  String id;
    private final String name;
    private final String description;
    private final LocalDate initialDate;
    private final LocalDate finalDate;
    private final ProjectStatus status;

}
