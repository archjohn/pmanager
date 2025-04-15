package com.archjohn.pmanager.domain.service;

import com.archjohn.pmanager.domain.entity.Project;
import com.archjohn.pmanager.domain.exeception.ProjectNotFoundException;
import com.archjohn.pmanager.domain.infrastructure.dto.SaveProjectDataDTO;
import com.archjohn.pmanager.domain.model.ProjectStatus;
import com.archjohn.pmanager.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Transactional
    public Project createProject(SaveProjectDataDTO saveProjectData) {

        Project project = Project
                .builder()
                .name(saveProjectData.getName())
                .description(saveProjectData.getDescription())
                .initialDate(saveProjectData.getFinalDate())
                .finalDate(saveProjectData.getFinalDate())
                .status(ProjectStatus.PENDING)
                .build();

        projectRepository.save(project);

        return project;
    }

    public Project loadProject(String projectID) {
        return projectRepository
                .findById(projectID)
                .orElseThrow(() -> new ProjectNotFoundException(projectID));
    }
}



