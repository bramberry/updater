package by.vsu.bramberry.updateChecker.controllers;

import by.vsu.bramberry.updateChecker.model.entity.Computer;
import by.vsu.bramberry.updateChecker.model.entity.software.Software;
import by.vsu.bramberry.updateChecker.model.service.iservice.ComputerService;
import by.vsu.bramberry.updateChecker.model.service.iservice.TransmitterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final TransmitterService transmitterService;
    private final ComputerService computerService;

    @Autowired
    public MainController(TransmitterService transmitterService, ComputerService computerService) {
        this.transmitterService = transmitterService;
        this.computerService = computerService;
    }


    @GetMapping(value = "/computers")
    public ResponseEntity getAudience(@RequestParam Integer page, @RequestParam Integer size) {
        List<Computer> computers = computerService.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(computers);
    }

    @GetMapping(value = "/computers/audience/{number}")
    public ResponseEntity getAudienceByNumber(@PathVariable("number") String number,
                                              @RequestParam Integer page,
                                              @RequestParam Integer size) {
        List<Computer> computers = computerService.findAllByAudienceNumber(number, PageRequest.of(page, size));
        return ResponseEntity.ok(computers);
    }

    @GetMapping(value = "/computers/{number}/search")
    public ResponseEntity getComputersBySoftwareAndAudienceNumber(@PathVariable String number, @RequestParam String programName) {
        List<Computer> computers = new ArrayList<>();
        for (Computer computer : computerService.findAll(PageRequest.of(1, 1))) {
            if (computer.getAudienceNumber().equals(number)) {
                for (Software software : computer.getSoftwareSet()) {
                    if (software.getName().equals(programName))
                        computers.add(computer);
                }
            }
        }


        return ResponseEntity.ok(computers);
    }

    @GetMapping(value = "/computers/{id}")
    public ResponseEntity getComputer(@PathVariable String id) {
        Computer computer = computerService.findOne(Long.parseLong(id));
        return ResponseEntity.ok(computer);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    @PostMapping(value = "/computers")
    public ResponseEntity save(@RequestBody Computer computer) {
        computerService.save(computer);
        logger.info(computer.toString());
        transmitterService.transmit(computer.getIp());
        return ResponseEntity.ok(computer);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    @GetMapping(value = "/computers/update")
    public ResponseEntity updateAll() {
        transmitterService.transmitAll();
        return ResponseEntity.ok("Task started");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    @GetMapping(value = "/computers/{ip}/update/")
    public ResponseEntity updateOne(@PathVariable String ip) {
        transmitterService.transmit(ip);
        return ResponseEntity.ok().body("Task started");
    }

    @DeleteMapping(value = "/computers/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        computerService.delete(Long.parseLong(id));
        return ResponseEntity.ok("Deleted successfully");
    }
}
