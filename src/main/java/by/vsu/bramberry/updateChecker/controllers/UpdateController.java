package by.vsu.bramberry.updateChecker.controllers;

import by.vsu.bramberry.updateChecker.model.entity.Computer;
import by.vsu.bramberry.updateChecker.model.service.iservice.TransmitterService;
import by.vsu.bramberry.updateChecker.model.service.iservice.UploadFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("updates")
public class UpdateController {
    private final TransmitterService transmitterService;
    private final RestTemplate restTemplate = new RestTemplate();
    private final UploadFileService uploadFileService;

    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    @GetMapping
    public ResponseEntity<String> updateAll() {
        try {
            transmitterService.transmitAll();
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage(), e);
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update error");
        }
        return ResponseEntity.ok("Task started");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    @GetMapping(value = "/{ip}")
    public ResponseEntity<String> updateOne(@PathVariable String ip) {
        try {
            transmitterService.transmit(ip);
        } catch (ExecutionException | InterruptedException e) {
            log.error(e.getMessage(), e);
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update error");
        }
        return ResponseEntity.ok().body("Task started");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    @GetMapping("download/init")
    public ResponseEntity<String> initDownload(@RequestParam String fileName, @RequestParam String ip) {

        String downloadUri = uploadFileService.getByFileName(fileName).getFileDownloadUri();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://" + ip + ":6666/InitDownload")
                .queryParam("filename", downloadUri);

        restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                Computer.class);
        return ResponseEntity.ok("initiated");
    }
}
