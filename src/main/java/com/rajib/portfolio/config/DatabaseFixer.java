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
        System.out.println("--- 🛠️ RUNNING DATABASE FIXER (LONGTEXT) 🛠️ ---");
        
        // Profile Table
        runSql("ALTER TABLE profile MODIFY summary LONGTEXT");
        runSql("ALTER TABLE profile MODIFY about_content LONGTEXT");
        runSql("ALTER TABLE profile MODIFY photo_url LONGTEXT");
        runSql("ALTER TABLE profile MODIFY about_photo_url LONGTEXT");
        
        // Projects Table
        runSql("ALTER TABLE projects MODIFY description LONGTEXT");
        runSql("ALTER TABLE projects MODIFY project_credentials LONGTEXT");
        
        // Experience & Education
        runSql("ALTER TABLE experience MODIFY description LONGTEXT");
        runSql("ALTER TABLE education MODIFY description LONGTEXT");
        
        System.out.println("--- ✅ DATABASE COLUMNS EXPANDED TO LONGTEXT ---");
    }

    private void runSql(String sql) {
        if (sql == null) return;

        try {
            jdbcTemplate.execute(sql);
            System.out.println("SUCCESS: " + sql);
        } catch (DataAccessException e) {
            // It might fail if table doesn't exist yet or connection drops
            System.out.println("SKIPPED: " + sql + " (Reason: " + e.getMessage() + ")");
        }
    }
}