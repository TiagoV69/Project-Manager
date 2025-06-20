package com.portafolio.projectmanager.project_management_api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Le dice a JPA que esta clase es una tabla en la base de datos.
public class Project {

    @Id // Marca este campo como PK de la tabla.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL genera y gestiona este valor automáticamente.
    private Long id;

    private String name;
    private String description;

    public Project() {
        // Constructor vacío requerido por JPA
    }

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}