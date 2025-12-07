package com.rajib.portfolio.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajib.portfolio.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {}