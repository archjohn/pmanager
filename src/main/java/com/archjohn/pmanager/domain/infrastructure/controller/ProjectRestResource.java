package com.archjohn.pmanager.domain.infrastructure.controller;

import com.archjohn.pmanager.domain.entity.Project;
import com.archjohn.pmanager.domain.infrastructure.dto.ProjectDTO;
import com.archjohn.pmanager.domain.infrastructure.dto.SaveProjectDataDTO;
import com.archjohn.pmanager.domain.service.ProjectService;
import static com.archjohn.pmanager.domain.infrastructure.controller.RestConstants.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;



@RestController
@RequestMapping(PATH_PROJECTS)
@RequiredArgsConstructor
public class ProjectRestResource {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody @Valid   SaveProjectDataDTO saveProjectDataDTO) {
        Project project = projectService.createProject(saveProjectDataDTO);

        return ResponseEntity
                .created(URI.create(PATH_PROJECTS + "/" + project.getId()))
                .body(ProjectDTO.create(project));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> loadProject(@PathVariable("id") String projectId) {
        Project project = projectService.loadProject(projectId);
        return ResponseEntity.ok(ProjectDTO.create(project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable("id") String projectID) {
        projectService.deleteProject(projectID);
        return ResponseEntity.noContent().build();
    }


}
