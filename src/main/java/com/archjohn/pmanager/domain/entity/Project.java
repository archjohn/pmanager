package com.archjohn.pmanager.domain.entity;

import com.archjohn.pmanager.domain.model.ProjectStatus;

import java.time.LocalDate;
import java.util.Objects;

public class Project {

    private String id;
    private String name;
    private String description;
    private LocalDate initialDate;
    private LocalDate finalDate;
    private ProjectStatus status;

    public Project(
            String id,
            String name,
            String description,
            LocalDate initialDate,
            LocalDate finalDate,
            ProjectStatus status
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.status = status;
    }

    public Project() {}

    private String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private LocalDate getInitialDate() {
        return initialDate;
    }

    private void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    private LocalDate getFinalDate() {
        return finalDate;
    }

    private void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    private ProjectStatus getStatus() {
        return status;
    }

    private void setProjectStatus(ProjectStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(getId(), project.getId()) && Objects.equals(getName(), project.getName()) && Objects.equals(getDescription(), project.getDescription()) && Objects.equals(getInitialDate(), project.getInitialDate()) && Objects.equals(getFinalDate(), project.getFinalDate()) && getStatus() == project.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getInitialDate(), getFinalDate(), getStatus());
    }
}
