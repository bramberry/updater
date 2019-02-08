package by.vsu.bramberry.updateChecker.controllers;


import by.vsu.bramberry.updateChecker.model.entity.UploadFile;
import by.vsu.bramberry.updateChecker.model.service.FileStorageService;
import by.vsu.bramberry.updateChecker.model.service.iservice.UploadFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class FileController {
    private final FileStorageService fileStorageService;
    private final UploadFileService uploadFileService;


    /**
     * @return updated channel with new video or Uploading error
     */
    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    @PostMapping("/upload")
    public ResponseEntity uploadVideo(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(fileName)
                .toUriString();

        UploadFile uploadFile = new UploadFile(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());

        return ResponseEntity.ok(uploadFileService.save(uploadFile));
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/files")
    @PreAuthorize("isFullyAuthenticated()")
    public ResponseEntity<List<UploadFile>> getAllFiles() {
        return ResponseEntity.ok(uploadFileService.getAll());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        uploadFileService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

}
