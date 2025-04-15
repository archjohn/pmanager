package com.archjohn.pmanager.domain.service;

import com.archjohn.pmanager.domain.entity.Project;
import com.archjohn.pmanager.domain.exeception.DuplicateProjectException;
import com.archjohn.pmanager.domain.exeception.ProjectNotFoundException;
import com.archjohn.pmanager.domain.infrastructure.dto.SaveProjectDataDTO;
import com.archjohn.pmanager.domain.model.ProjectStatus;
import com.archjohn.pmanager.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.management.ObjectName;
import java.util.Objects;
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

    @Transactional
    public void deleteProject(String projectID) {
        Project project = loadProject(projectID);
        projectRepository.delete(project);
    }

    @Transactional
    public Project updateProject(String projectId, SaveProjectDataDTO saveProjectData) {
        if(existsProjectWithName(saveProjectData.getName(), projectId)) {
            throw new DuplicateProjectException(saveProjectData.getName());
        }

        Project project = loadProject(projectId);

        project.setName(saveProjectData.getName());
        project.setDescription(saveProjectData.getDescription());
        project.setInitialDate(saveProjectData.getInitialDate());
        project.setFinalDate(saveProjectData.getFinalDate());
        project.setStatus(saveProjectData.getStatus());

        return project;
    }

    private boolean existsProjectWithName(String name, String idToExclude) {
        return projectRepository
                .findByName(name)
                .filter(p -> !Objects.equals(p.getId(), idToExclude))
                .isPresent();
    }


}



