package com.portafolio.projectmanager.project_management_api.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portafolio.projectmanager.project_management_api.models.Project;
import com.portafolio.projectmanager.project_management_api.repositories.ProjectRepository;

@Service // Marcamos esta clase como un componente de servicio de Spring
public class ProjectService {

    private final ProjectRepository projectRepository;

    // Inyección de dependencias por constructor.
    // Spring ve que este constructor necesita un ProjectRepository y nos lo "inyecta" automáticamente.
    // Esta es la forma recomendada de inyección de dependencias según lo entendido.
    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Método para crear un nuevo proyecto
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // Método para obtener todos los proyectos
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Método para obtener un proyecto por su ID
    // Usamos Optional para manejar el caso de que el proyecto no exista
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }
    
    // Método para actualizar un proyecto existente
    public Project updateProject(Long id, Project projectDetails) {
        // Primero, buscamos el proyecto existente.
        Project project = projectRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con id: " + id)); // Lanza una excepción si no lo encuentra
        
        // Actualizamos los campos
        project.setName(projectDetails.getName());
        project.setDescription(projectDetails.getDescription());

        // Guardamos el proyecto actualizado en la base de datos
        return projectRepository.save(project);
    }

    // Método para borrar un proyecto
    public void deleteProject(Long id) {
         if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Proyecto no encontrado con id: " + id);
        }
        projectRepository.deleteById(id);
    }
}