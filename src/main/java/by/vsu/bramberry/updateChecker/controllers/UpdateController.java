package by.vsu.bramberry.updateChecker.controllers;

import by.vsu.bramberry.updateChecker.model.service.iservice.TransmitterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("updates")
@Slf4j
@AllArgsConstructor
public class UpdateController {
    private final TransmitterService transmitterService;

    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    @GetMapping
    public ResponseEntity updateAll() {
        transmitterService.transmitAll();
        return ResponseEntity.ok("Task started");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    @GetMapping(value = "/{ip}")
    public ResponseEntity updateOne(@PathVariable String ip) {
        transmitterService.transmit(ip);
        return ResponseEntity.ok().body("Task started");
    }
}
