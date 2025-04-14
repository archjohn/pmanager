package com.archjohn.pmanager.domain.infrastructure.controller;

import com.archjohn.pmanager.domain.entity.Project;
import com.archjohn.pmanager.domain.infrastructure.dto.SaveProjectDataDTO;
import com.archjohn.pmanager.domain.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectRestResource {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody SaveProjectDataDTO saveProjectDataDTO) {
        Project project = projectService.createProject(saveProjectDataDTO);

        return ResponseEntity
                .created(URI.create("/projects/" + project.getId()))
                .body(project);
    }

}
