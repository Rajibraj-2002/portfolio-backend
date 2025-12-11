package com.rajib.portfolio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseFixer implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- RUNNING DATABASE FIXER ---");
        try {
            // These commands expand the columns from 255 chars to 65,000+ chars (TEXT)
            // It tries to modify the table structure without deleting data
            
            jdbcTemplate.execute("ALTER TABLE profile MODIFY summary TEXT");
            jdbcTemplate.execute("ALTER TABLE profile MODIFY about_content TEXT");
            
            // Also fix URL columns just in case they are long
            jdbcTemplate.execute("ALTER TABLE profile MODIFY photo_url TEXT");
            jdbcTemplate.execute("ALTER TABLE profile MODIFY about_photo_url TEXT");
            
            // For Projects (New credential field)
            jdbcTemplate.execute("ALTER TABLE projects MODIFY description TEXT");
            jdbcTemplate.execute("ALTER TABLE projects MODIFY project_credentials TEXT");
            
            System.out.println("--- DATABASE COLUMNS FIXED SUCCESSFULLY ---");
        } catch (Exception e) {
            // If it fails (e.g. table doesn't exist yet), it just prints error but keeps running
            System.out.println("--- FIXER SKIPPED (Already fixed or table missing) ---");
            // System.out.println(e.getMessage());
        }
    }
}