package com.rajib.portfolio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String university;
    private String degree;
    private String yearRange;
    
    @Column(columnDefinition = "TEXT")
    private String description;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUniversity() { return university; }
    public void setUniversity(String university) { this.university = university; }
    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }
    public String getYearRange() { return yearRange; }
    public void setYearRange(String yearRange) { this.yearRange = yearRange; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}