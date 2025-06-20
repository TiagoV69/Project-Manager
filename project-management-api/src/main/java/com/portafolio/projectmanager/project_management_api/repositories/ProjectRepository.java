package com.portafolio.projectmanager.project_management_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portafolio.projectmanager.project_management_api.models.Project;

@Repository // Le indica a Spring que esta es una interfaz de acceso a datos.
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    // JpaRepository<Project, Long> nos da métodos como save(), findById(), findAll(), deleteById(), etc.
    
}