// package com.tutorial.apidemo.apidemo.services;

// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.nio.file.StandardCopyOption;
// import java.util.Arrays;
// import java.util.List;
// import java.util.UUID;
// import java.util.stream.Stream;

// import org.apache.commons.io.FilenameUtils;
// import org.springframework.stereotype.Service;
// import org.springframework.util.StringUtils;
// import org.springframework.web.multipart.MultipartFile;

// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//  */


// /**
//  *
//  * @author tient
//  */
// @Service
// public class ImageStorageServices implements IStorageService {
//     private final Path storageFolder = Paths.get("uploads");

//     // 
//     public ImageStorageServices() {
//         // Ensure the storage folder exists
//         try {
//             Files.createDirectories(storageFolder);
//         } catch (IOException e) {
//             throw new RuntimeException("Could not initialize folder", e);
//         }
//     }


//     // Validate image file extension
//     private boolean isValidImageFile(MultipartFile file) {
//         String filename = file.getOriginalFilename();
//         if (filename == null) return false;
        
//         String fileExtension = FilenameUtils.getExtension(filename).toLowerCase();
//         List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png", "gif", "bmp", "webp");
//         return allowedExtensions.contains(fileExtension);
//     }

//     @Override
//     public String storeFile(MultipartFile file) {
//         try {
//             // Check if file is empty
//             if (file.isEmpty()) {
//                 throw new RuntimeException("Failed to store empty file " + file.getOriginalFilename());
//             }
            
//             // Validate file type
//             if (!isValidImageFile(file)) {
//                 throw new RuntimeException("Only image files are allowed");
//             }
            
//             // Generate unique filename
//             String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
//             String fileExtension = FilenameUtils.getExtension(originalFilename);
//             String uniqueFilename = UUID.randomUUID().toString() + "." + fileExtension;
            
//             // Create target location
//             Path targetLocation = storageFolder.resolve(uniqueFilename);
            
//             // Copy file to target location
//             Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            
//             return uniqueFilename;
            
//         } catch (IOException e) {
//             throw new RuntimeException("Could not store file " + file.getOriginalFilename(), e);
//         }
//     }

//     @Override
//     public Stream<Path> loadAll() {
//         try {
//             return Files.walk(storageFolder, 1)
//                     .filter(path -> !path.equals(storageFolder))
//                     .map(storageFolder::relativize);
//         } catch (IOException e) {
//             throw new RuntimeException("Failed to read stored files", e);
//         }
//     }

//     @Override
//     public byte[] readFileContent(String filename) {
//         try {
//             Path filePath = storageFolder.resolve(filename);
//             if (!Files.exists(filePath)) {
//                 throw new RuntimeException("File not found: " + filename);
//             }
//             return Files.readAllBytes(filePath);
//         } catch (IOException e) {
//             throw new RuntimeException("Could not read file: " + filename, e);
//         }
//     }

//     @Override
//     public void deleteAllFiles() {
//         try {
//             Files.walk(storageFolder)
//                     .filter(path -> !path.equals(storageFolder))
//                     .forEach(path -> {
//                         try {
//                             Files.delete(path);
//                         } catch (IOException e) {
//                             throw new RuntimeException("Could not delete file: " + path, e);
//                         }
//                     });
//         } catch (IOException e) {
//             throw new RuntimeException("Could not delete files", e);
//         }
//     }
    
//     // Additional utility methods
//     public Path getStorageFolder() {
//         return storageFolder;
//     }
    
//     public boolean deleteFile(String filename) {
//         try {
//             Path filePath = storageFolder.resolve(filename);
//             return Files.deleteIfExists(filePath);
//         } catch (IOException e) {
//             throw new RuntimeException("Could not delete file: " + filename, e);
//         }
//     }
    
//     public boolean fileExists(String filename) {
//         Path filePath = storageFolder.resolve(filename);
//         return Files.exists(filePath);
//     }
// }
