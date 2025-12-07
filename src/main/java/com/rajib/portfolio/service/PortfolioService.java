package com.rajib.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajib.portfolio.model.Certificate;
import com.rajib.portfolio.model.Education;
import com.rajib.portfolio.model.Experience;
import com.rajib.portfolio.model.Profile;
import com.rajib.portfolio.model.Project;
import com.rajib.portfolio.model.Skill;
import com.rajib.portfolio.repository.CertificateRepository;
import com.rajib.portfolio.repository.EducationRepository;
import com.rajib.portfolio.repository.ExperienceRepository;
import com.rajib.portfolio.repository.ProfileRepository;
import com.rajib.portfolio.repository.ProjectRepository;
import com.rajib.portfolio.repository.SkillRepository;

@Service
public class PortfolioService {

    @Autowired private ProjectRepository projectRepo;
    @Autowired private ProfileRepository profileRepo;
    @Autowired private EducationRepository eduRepo;
    @Autowired private SkillRepository skillRepo;
    @Autowired private ExperienceRepository expRepo;
    @Autowired private CertificateRepository certRepo;

    // --- GET METHODS (For the public website) ---
    public List<Project> getProjects() { return projectRepo.findAll(); }
    public Profile getProfile() { return profileRepo.findById(1L).orElse(null); } // Assumes ID 1 is you
    public List<Education> getEducation() { return eduRepo.findAll(); }
    public List<Skill> getSkills() { return skillRepo.findAll(); }
    public List<Experience> getExperience() { return expRepo.findAll(); }
    public List<Certificate> getCertificates() { return certRepo.findAll(); }

    // --- SAVE/UPDATE METHODS (For Admin Rajib) ---
    public Project saveProject(Project p) { return projectRepo.save(p); }
    public Profile saveProfile(Profile p) { return profileRepo.save(p); }
    public Education saveEducation(Education e) { return eduRepo.save(e); }
    public Skill saveSkill(Skill s) { return skillRepo.save(s); }
    public Experience saveExperience(Experience e) { return expRepo.save(e); }
    public Certificate saveCertificate(Certificate c) { return certRepo.save(c); }

    // --- DELETE METHODS ---
    public void deleteProject(Long id) { projectRepo.deleteById(id); }
    public void deleteSkill(Long id) { skillRepo.deleteById(id); }
    public void deleteEducation(Long id) { eduRepo.deleteById(id); }
    public void deleteExperience(Long id) { expRepo.deleteById(id); }
    public void deleteCertificate(Long id) { certRepo.deleteById(id); }
}