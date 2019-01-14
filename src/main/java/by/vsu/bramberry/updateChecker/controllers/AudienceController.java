package by.vsu.bramberry.updateChecker.controllers;

import by.vsu.bramberry.updateChecker.model.entity.Computer;
import by.vsu.bramberry.updateChecker.model.service.iservice.ComputerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("audiences")
@Slf4j
@AllArgsConstructor
public class AudienceController {
    private final ComputerService computerService;

    @GetMapping
    public ResponseEntity<Set<String>> getAudiencesNumbers() {
        List<Computer> computers = computerService.findAll();
        Set<String> audiencesNumbers = new HashSet<>();
        computers.forEach(computer -> {
            audiencesNumbers.add(computer.getAudienceNumber());
        });
        return ResponseEntity.ok(audiencesNumbers);
    }

    @GetMapping(value = "/{number}")
    public ResponseEntity getAudienceByNumber(@PathVariable String number) {
        List<Computer> computers = computerService.findAllByAudienceNumber(number);
        return ResponseEntity.ok(computers);
    }
}
