package com.rajib.portfolio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String title;
    
    // Field 1: Short Summary (Hero Section)
    @Column(columnDefinition = "TEXT")
    private String summary;       
    
    // Field 2: Long Bio (About Section)
    @Column(columnDefinition = "TEXT")
    private String aboutContent;  
    
    private String email;
    private String linkedinLink;
    private String githubLink;
    private String cvLink;
    
    // Photo 1: For Home / Hero Section
    @Column(columnDefinition = "LONGTEXT")
    private String photoUrl;

    // --- NEW FIELD: Photo 2 for About Section ---
    @Column(columnDefinition = "LONGTEXT")
    private String aboutPhotoUrl;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    
    public String getAboutContent() { return aboutContent; } 
    public void setAboutContent(String aboutContent) { this.aboutContent = aboutContent; } 
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getLinkedinLink() { return linkedinLink; }
    public void setLinkedinLink(String linkedinLink) { this.linkedinLink = linkedinLink; }
    
    public String getGithubLink() { return githubLink; }
    public void setGithubLink(String githubLink) { this.githubLink = githubLink; }
    
    public String getCvLink() { return cvLink; }
    public void setCvLink(String cvLink) { this.cvLink = cvLink; }
    
    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    // --- New Getter & Setter for About Photo ---
    public String getAboutPhotoUrl() { return aboutPhotoUrl; }
    public void setAboutPhotoUrl(String aboutPhotoUrl) { this.aboutPhotoUrl = aboutPhotoUrl; }
}