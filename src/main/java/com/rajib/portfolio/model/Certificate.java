package com.rajib.portfolio.model;

import jakarta.persistence.Column; 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String certName; 
    private String issuer;   
    private String issueDate; 
    
    // Ensure the link is large enough for a long URL or file path
    @Column(columnDefinition = "TEXT") 
    private String link; 

    // --- GETTERS AND SETTERS (CRITICAL FIX) ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCertName() { return certName; }
    public void setCertName(String certName) { this.certName = certName; }

    public String getIssuer() { return issuer; }
    public void setIssuer(String issuer) { this.issuer = issuer; }

    public String getIssueDate() { return issueDate; }
    public void setIssueDate(String issueDate) { this.issueDate = issueDate; }
    
    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}