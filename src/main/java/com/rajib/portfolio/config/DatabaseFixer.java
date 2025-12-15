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
        System.out.println("--- 🛠️ RUNNING DATABASE FIXER 🛠️ ---");
        
        // Helper method to run SQL safely
        runSql("ALTER TABLE profile MODIFY summary TEXT");
        runSql("ALTER TABLE profile MODIFY about_content TEXT");
        runSql("ALTER TABLE profile MODIFY photo_url TEXT");
        runSql("ALTER TABLE profile MODIFY about_photo_url TEXT");
        
        runSql("ALTER TABLE projects MODIFY description TEXT");
        runSql("ALTER TABLE projects MODIFY project_credentials TEXT");
        
        runSql("ALTER TABLE experience MODIFY description TEXT");
        runSql("ALTER TABLE education MODIFY description TEXT");
        
        System.out.println("--- ✅ DATABASE FIXER FINISHED ---");
    }

    private void runSql(String sql) {
        // Fix "Null type safety" warning by ensuring sql is not null
        if (sql == null) return;

        try {
            jdbcTemplate.execute(sql);
            System.out.println("SUCCESS: " + sql);
        } catch (DataAccessException e) {
            // Fix "catch specific exception" warning by catching DataAccessException
            // It might fail if table doesn't exist yet, which is fine.
            System.out.println("SKIPPED: " + sql + " (Reason: " + e.getMessage() + ")");
        }
    }
}