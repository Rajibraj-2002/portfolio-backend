package com.rajib.portfolio.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "rajib-portfolio-two.vercel.app")
public class ImageUploadController {

    // Define the folder where images will be stored
    // System.getProperty("user.dir") gets your project root folder
    private final Path fileStorageLocation = Paths.get(System.getProperty("user.dir") + "/uploads");

    public ImageUploadController() {
        try {
            // Create the directory if it doesn't exist
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Generate a unique filename to avoid conflicts (e.g., profile_12345.jpg)
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            
            // Resolve the file path
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            
            // Save the file
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // Return the URL that the frontend can use to access this image
            // NOTE: We will configure the /uploads/ path in the next step (WebConfig)
            String fileUrl = "http://localhost:8080/uploads/" + fileName;
            
            return ResponseEntity.ok(fileUrl);
        } catch (IOException ex) {
            return ResponseEntity.status(500).body("Could not upload file: " + ex.getMessage());
        }
    }
}