package com.rajib.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

@RestController
@RequestMapping("/api")
// FIX: Added https:// and localhost
@CrossOrigin(origins = {"https://rajib-portfolio-two.vercel.app", "http://localhost:3000"})
public class PortfolioController {

    @Autowired private ProfileRepository profileRepo;
    @Autowired private ProjectRepository projectRepo;
    @Autowired private SkillRepository skillRepo;
    @Autowired private ExperienceRepository experienceRepo;
    @Autowired private EducationRepository educationRepo;
    @Autowired private CertificateRepository certificateRepo;

    // ---------------- PROFILE ----------------
    @GetMapping("/profile")
    public Profile getProfile() {
        return profileRepo.findAll().stream().findFirst().orElse(new Profile());
    }

    @PutMapping("/profile/{id}")
    public Profile updateProfile(@PathVariable Long id, @RequestBody Profile newProfile) {
        return profileRepo.findById(id).map(profile -> {
            profile.setFullName(newProfile.getFullName());
            profile.setTitle(newProfile.getTitle());
            profile.setSummary(newProfile.getSummary());
            profile.setAboutContent(newProfile.getAboutContent());
            profile.setEmail(newProfile.getEmail());
            profile.setLinkedinLink(newProfile.getLinkedinLink());
            profile.setGithubLink(newProfile.getGithubLink());
            profile.setCvLink(newProfile.getCvLink());
            profile.setPhotoUrl(newProfile.getPhotoUrl());
            profile.setAboutPhotoUrl(newProfile.getAboutPhotoUrl());
            return profileRepo.save(profile);
        }).orElseGet(() -> {
            newProfile.setId(id);
            return profileRepo.save(newProfile);
        });
    }

    // ---------------- PROJECTS ----------------
    @GetMapping("/projects")
    public List<Project> getProjects() { return projectRepo.findAll(); }

    @PostMapping("/projects")
    public Project addProject(@RequestBody Project project) { return projectRepo.save(project); }
    
    @PutMapping("/projects/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project newProject) {
        return projectRepo.findById(id).map(project -> {
            project.setProjectName(newProject.getProjectName());
            project.setDescription(newProject.getDescription());
            project.setTechStack(newProject.getTechStack());
            project.setProjectLink(newProject.getProjectLink());
            project.setRepoLink(newProject.getRepoLink());
            project.setProjectCredentials(newProject.getProjectCredentials()); 
            return projectRepo.save(project);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
    }

    @DeleteMapping("/projects/{id}")
    public void deleteProject(@PathVariable Long id) { projectRepo.deleteById(id); }

    // ---------------- SKILLS ----------------
    @GetMapping("/skills")
    public List<Skill> getSkills() { return skillRepo.findAll(); }

    @PostMapping("/skills")
    public Skill addSkill(@RequestBody Skill skill) { return skillRepo.save(skill); }
    
    @PutMapping("/skills/{id}")
    public Skill updateSkill(@PathVariable Long id, @RequestBody Skill newSkill) {
        return skillRepo.findById(id).map(skill -> {
            skill.setSkillName(newSkill.getSkillName());
            skill.setCategory(newSkill.getCategory());
            return skillRepo.save(skill);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found"));
    }

    @DeleteMapping("/skills/{id}")
    public void deleteSkill(@PathVariable Long id) { skillRepo.deleteById(id); }
    
    // ---------------- EXPERIENCE ----------------
    @GetMapping("/experience")
    public List<Experience> getExperience() { return experienceRepo.findAll(); }

    @PostMapping("/experience")
    public Experience addExperience(@RequestBody Experience experience) { return experienceRepo.save(experience); }

    @PutMapping("/experience/{id}")
    public Experience updateExperience(@PathVariable Long id, @RequestBody Experience newExp) {
        return experienceRepo.findById(id).map(exp -> {
            exp.setRole(newExp.getRole());
            exp.setCompany(newExp.getCompany());
            exp.setDuration(newExp.getDuration());
            exp.setDescription(newExp.getDescription());
            return experienceRepo.save(exp);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Experience not found"));
    }

    @DeleteMapping("/experience/{id}")
    public void deleteExperience(@PathVariable Long id) { experienceRepo.deleteById(id); }

    // ---------------- EDUCATION ----------------
    @GetMapping("/education")
    public List<Education> getEducation() { return educationRepo.findAll(); }

    @PostMapping("/education")
    public Education addEducation(@RequestBody Education education) { return educationRepo.save(education); }

    @PutMapping("/education/{id}")
    public Education updateEducation(@PathVariable Long id, @RequestBody Education newEdu) {
        return educationRepo.findById(id).map(edu -> {
            edu.setDegree(newEdu.getDegree());
            edu.setUniversity(newEdu.getUniversity());
            edu.setYearRange(newEdu.getYearRange());
            edu.setDescription(newEdu.getDescription());
            return educationRepo.save(edu);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Education not found"));
    }

    @DeleteMapping("/education/{id}")
    public void deleteEducation(@PathVariable Long id) { educationRepo.deleteById(id); }

    // ---------------- CERTIFICATES ----------------
    @GetMapping("/certificates")
    public List<Certificate> getCertificates() { return certificateRepo.findAll(); }

    @PostMapping("/certificates")
    public Certificate addCertificate(@RequestBody Certificate certificate) { return certificateRepo.save(certificate); }
    
    @PutMapping("/certificates/{id}")
    public Certificate updateCertificate(@PathVariable Long id, @RequestBody Certificate newCert) {
        return certificateRepo.findById(id).map(cert -> {
            cert.setCertName(newCert.getCertName());
            cert.setIssuer(newCert.getIssuer());
            cert.setIssueDate(newCert.getIssueDate());
            // cert.setLink(newCert.getLink()); 
            return certificateRepo.save(cert);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificate not found"));
    }

    @DeleteMapping("/certificates/{id}")
    public void deleteCertificate(@PathVariable Long id) { certificateRepo.deleteById(id); }
}