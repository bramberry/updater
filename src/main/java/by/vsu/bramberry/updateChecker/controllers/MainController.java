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
@RequestMapping("computers")
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final TransmitterService transmitterService;
    private final ComputerService computerService;

    @Autowired
    public MainController(TransmitterService transmitterService, ComputerService computerService) {
        this.transmitterService = transmitterService;
        this.computerService = computerService;
    }



    @GetMapping(value = "/{number}/search")
    public ResponseEntity getComputersBySoftwareAndAudienceNumber(@PathVariable String number,
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
    public ResponseEntity getComputer(@PathVariable Long id) {
        Computer computer = computerService.findOne(id);
        return ResponseEntity.ok(computer);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    @PostMapping
    public ResponseEntity save(@RequestBody Computer computer) {
        computerService.save(computer);
        logger.info(computer.toString());
        transmitterService.transmit(computer.getIp());
        return ResponseEntity.ok(computer);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') and isFullyAuthenticated()")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        computerService.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
