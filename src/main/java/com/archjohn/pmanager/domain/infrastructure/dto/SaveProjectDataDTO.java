package com.archjohn.pmanager.domain.infrastructure.dto;

import com.archjohn.pmanager.domain.model.ProjectStatus;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jdk.jfr.Description;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SaveProjectDataDTO {
    @NotNull(message = "Name cannot be empty")
    @Size(min = 1, max = 80, message = "Invalid name")
    private final String name;

    @NotNull(message = "Description cannot be empty")
    @Size(min = 1, max = 150, message = "Invalid Description")
    private final String description;

    @NotNull(message = "Initial date cannot empty")
    private final LocalDate initialDate;

    @NotNull(message = "Final date cannot empty")
    private final LocalDate finalDate;

    private final ProjectStatus status;

    @AssertTrue(message = "Dates are not consistent")
    @SuppressWarnings("unused")
    private boolean isInitialDataBeforeFinalDate() {
        return initialDate.isBefore(finalDate);
    }

}
