package by.vsu.bramberry.updatechecker.controllers;

import by.vsu.bramberry.updatechecker.model.entity.Computer;
import by.vsu.bramberry.updatechecker.model.entity.software.Software;
import by.vsu.bramberry.updatechecker.model.service.iservice.ComputerService;
import by.vsu.bramberry.updatechecker.model.service.iservice.TransmitterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("computers")
@Slf4j
@AllArgsConstructor
public class ComputerController {

    private final TransmitterService transmitterService;
    private final ComputerService computerService;

    @GetMapping(value = "/{number}/search")
    public ResponseEntity<List<Computer>> getComputersBySoftwareAndAudienceNumber(@PathVariable String number,
                                                                                  @RequestParam String programName) {
        List<Computer> computers = new ArrayList<>();
        for (Computer computer : computerService.findAllByAudienceNumber(number)) {
            if (computer.getAudienceNumber().equals(number)) {
                for (Software software : computer.getSoftwareSet()) {
                    if (software.getName().equals(programName))
                        computers.add(computer);
                }
            }
        }


        return ResponseEntity.ok(computers);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Computer> getComputer(@PathVariable Long id) {
        Computer computer = computerService.findOne(id);
        return ResponseEntity.ok(computer);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    @PostMapping
    public ResponseEntity save(@RequestBody Computer computer) {
        if (computerService.findByIp(computer.getIp()) == null) {
            computerService.save(computer);
        }
        log.info(computer.toString());
        try {
            transmitterService.transmit(computer.getIp());
        } catch (ExecutionException | InterruptedException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update error");
        }
        return ResponseEntity.ok(computer);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    @PutMapping
    public ResponseEntity<Computer> edit(@RequestBody Computer computer) {
        computerService.update(computer);
        log.info(computer.toString());
        return ResponseEntity.ok(computer);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        computerService.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
