package com.rajib.portfolio.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajib.portfolio.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {}