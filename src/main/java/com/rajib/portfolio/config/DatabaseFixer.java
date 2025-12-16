package com.rajib.portfolio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseFixer implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- 🛠️ RUNNING DATABASE FIXER (LINKS UPDATE) 🛠️ ---");
        
        // 1. Profile: Expand Content & Image URLs
        runSql("ALTER TABLE profile MODIFY summary LONGTEXT");
        runSql("ALTER TABLE profile MODIFY about_content LONGTEXT");
        runSql("ALTER TABLE profile MODIFY photo_url LONGTEXT");
        runSql("ALTER TABLE profile MODIFY about_photo_url LONGTEXT");
        
        // 2. Profile: Expand Social & CV Links (CRITICAL FIX)
        runSql("ALTER TABLE profile MODIFY cv_link LONGTEXT");
        runSql("ALTER TABLE profile MODIFY linkedin_link LONGTEXT");
        runSql("ALTER TABLE profile MODIFY github_link LONGTEXT");
        runSql("ALTER TABLE profile MODIFY email TEXT"); // Just in case
        runSql("ALTER TABLE profile MODIFY title TEXT"); 
        
        // 3. Projects: Expand Description & Links
        runSql("ALTER TABLE projects MODIFY description LONGTEXT");
        runSql("ALTER TABLE projects MODIFY project_credentials LONGTEXT");
        runSql("ALTER TABLE projects MODIFY project_link LONGTEXT");
        runSql("ALTER TABLE projects MODIFY repo_link LONGTEXT");
        runSql("ALTER TABLE projects MODIFY tech_stack TEXT");
        
        // 4. Experience & Education & Certificates
        runSql("ALTER TABLE experience MODIFY description LONGTEXT");
        runSql("ALTER TABLE education MODIFY description LONGTEXT");
        runSql("ALTER TABLE certificate MODIFY link LONGTEXT"); // Note: Table name might be 'certificate' or 'certificates' depending on naming strategy
        runSql("ALTER TABLE certificates MODIFY link LONGTEXT"); // Try plural too just in case

        System.out.println("--- ✅ DATABASE COLUMNS EXPANDED (LINKS INCLUDED) ---");
    }

    private void runSql(String sql) {
        if (sql == null) return;
        try {
            jdbcTemplate.execute(sql);
            System.out.println("SUCCESS: " + sql);
        } catch (DataAccessException e) {
            // It might fail if table/column doesn't exist yet, which is fine.
            System.out.println("SKIPPED: " + sql + " (Reason: " + e.getMessage() + ")");
        }
    }
}