package com.rajib.portfolio.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
// FIX: Added https:// and localhost
@CrossOrigin(origins = {"https://rajib-portfolio-two.vercel.app", "http://localhost:3000"})
public class ImageUploadController {

    private final Path fileStorageLocation = Paths.get(System.getProperty("user.dir") + "/uploads");

    public ImageUploadController() {
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // NOTE: This URL will likely need to be the Render URL in production
            // Ideally, use environment variable for base URL
            String fileUrl = "https://rajib-portfolio-api.onrender.com/uploads/" + fileName;
            
            return ResponseEntity.ok(fileUrl);
        } catch (IOException ex) {
            return ResponseEntity.status(500).body("Could not upload file: " + ex.getMessage());
        }
    }
}