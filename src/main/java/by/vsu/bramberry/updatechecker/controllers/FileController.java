package by.vsu.bramberry.updatechecker.controllers;


import by.vsu.bramberry.updatechecker.model.entity.UploadFile;
import by.vsu.bramberry.updatechecker.model.exception.FileStoreException;
import by.vsu.bramberry.updatechecker.model.service.FileStorageService;
import by.vsu.bramberry.updatechecker.model.service.iservice.UploadFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
public class FileController {
    private final FileStorageService fileStorageService;
    private final UploadFileService uploadFileService;
    private final static String SHOW_FILENAME = "filename";
    private final static String FILE_TYPE = "type";


    /**
     * @return updated channel with new video or Uploading error
     */
    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        UploadFile uploadFile = uploadFileService.getByFileName(file.getOriginalFilename());
        if (uploadFile != null) {
            throw new FileStoreException("Sorry! Filename contains already exists");
        }
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(fileName)
                .toUriString();

        Map<String, String> stats = getStats(file);
        uploadFile = new UploadFile(fileName, stats.get(SHOW_FILENAME), fileDownloadUri,
                stats.get(FILE_TYPE), file.getSize());

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

    @DeleteMapping("files/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        uploadFileService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    private Map<String, String> getStats(MultipartFile file) {
        Map<String, String> stats = new HashMap<>();
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new RuntimeException("There is no filename");
        }

        int index = file.getOriginalFilename().lastIndexOf(".");

        String showFilename = originalFilename.substring(0, index);
        String type = originalFilename.substring(index + 1);

        stats.put(SHOW_FILENAME, showFilename);
        stats.put(FILE_TYPE, type);

        return stats;
    }

}
