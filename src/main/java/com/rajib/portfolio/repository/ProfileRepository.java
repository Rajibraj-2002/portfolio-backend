package com.rajib.portfolio.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajib.portfolio.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {}