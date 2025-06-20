package com.portafolio.projectmanager.project_management_api.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.portafolio.projectmanager.project_management_api.Services.ProjectService;
import com.portafolio.projectmanager.project_management_api.models.Project;

import java.util.List;

@RestController // Convierte las respuestas a JSON automáticamente
@RequestMapping("/api/projects") // Definimos una URL base para todos los métodos en esta clase
public class ProjectController {

    private final ProjectService projectService;

    // Inyectamos el servicio
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping // Mapea peticiones HTTP POST 
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        // @RequestBody convierte el JSON de la petición en un objeto Project
        Project createdProject = projectService.createProject(project);
        // Devolvemos 201 (Created) y el proyecto creado en el cuerpo
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @GetMapping // Mapea peticiones HTTP GET 
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK); // Devuelve 200 OK
    }

    @GetMapping("/{id}") // Mapea peticiones GET por id
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        // @PathVariable extrae el 'id' de la URL
        return projectService.getProjectById(id)
                .map(project -> new ResponseEntity<>(project, HttpStatus.OK)) // Si lo encuentra devuelve 200 (OK)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Si no, devuelve 404 (Not Found)
    }

    @PutMapping("/{id}") // Mapea peticiones PUT por id
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        try {
            Project updatedProject = projectService.updateProject(id, projectDetails);
            return new ResponseEntity<>(updatedProject, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}") // Mapea peticiones DELETE por id
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        try {
            projectService.deleteProject(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Devuelve 204 No Content indicando exito sin cuerpo.
        } catch (RuntimeException e) {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}