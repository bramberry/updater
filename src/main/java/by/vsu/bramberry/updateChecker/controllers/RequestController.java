package by.vsu.bramberry.updateChecker.controllers;


import by.vsu.bramberry.updateChecker.model.entity.Request;
import by.vsu.bramberry.updateChecker.model.service.iservice.RequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("requests")
@AllArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @PreAuthorize("hasAnyAuthority('ADMIN','USER') and isFullyAuthenticated()")
    @PostMapping
    public ResponseEntity save(@RequestBody Request request) {
        request = requestService.save(request);
        String uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/" + request.getId()).toUriString();

        return ResponseEntity.created(URI.create(uri)).body(request);
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(requestService.findAll());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        requestService.delete(id);
        return ResponseEntity.ok("deleted");
    }
}
