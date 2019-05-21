package by.vsu.bramberry.updatechecker.model.service;

import by.vsu.bramberry.updatechecker.model.exception.FileStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;
    @Value("${file.upload-dir}")
    private String uploadDir = "./files";

    @Autowired
    public FileStorageService() {
        this.fileStorageLocation = Paths.get(uploadDir)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            throw new FileStoreException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String randomString = UUID.randomUUID().toString().replace("-", "");
        fileName = fileName.replace(".", randomString + ".");

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStoreException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStoreException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileStoreException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileStoreException("File not found " + fileName, ex);
        }
    }

    public void deleteFile(String fileName) {
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        try {
            Files.delete(targetLocation);
        } catch (IOException e) {
            throw new FileStoreException(e.getMessage(), e);
        }
    }
}