package com.rajib.portfolio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;
    private String projectLink;
    private String repoLink;
    private String techStack;
    
    // Using TEXT to allow longer descriptions
    @Column(columnDefinition = "TEXT")
    private String description;

    // --- NEW FIELD ---
    @Column(columnDefinition = "TEXT")
    private String projectCredentials; 

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public String getProjectLink() { return projectLink; }
    public void setProjectLink(String projectLink) { this.projectLink = projectLink; }
    public String getRepoLink() { return repoLink; }
    public void setRepoLink(String repoLink) { this.repoLink = repoLink; }
    public String getTechStack() { return techStack; }
    public void setTechStack(String techStack) { this.techStack = techStack; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    // New Get/Set
    public String getProjectCredentials() { return projectCredentials; }
    public void setProjectCredentials(String projectCredentials) { this.projectCredentials = projectCredentials; }
}