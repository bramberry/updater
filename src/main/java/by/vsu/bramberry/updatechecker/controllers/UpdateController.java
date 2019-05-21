package by.vsu.bramberry.updatechecker.controllers;

import by.vsu.bramberry.updatechecker.model.service.iservice.TransmitterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("updates")
public class UpdateController {
    private final TransmitterService transmitterService;

    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    @GetMapping
    public ResponseEntity updateAll(@RequestParam String audienceNumber) {
        try {
            transmitterService.transmitAll(audienceNumber);
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update error");
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update error");
        }
        return ResponseEntity.ok().body("Task started");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    @GetMapping("install/single")
    public ResponseEntity<String> initDownload(@RequestParam String fileName, @RequestParam String ip) {
        transmitterService.installOnce(fileName, ip);
        return ResponseEntity.ok("Install init");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    @GetMapping("install/audience")
    public ResponseEntity<String> initAudienceDownload(@RequestParam String fileName, @RequestParam String audienceNumber) {
        transmitterService.installInAudience(fileName, audienceNumber);
        return ResponseEntity.ok("Install init");
    }
}
