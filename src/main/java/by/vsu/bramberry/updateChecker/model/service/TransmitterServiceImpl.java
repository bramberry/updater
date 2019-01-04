package by.vsu.bramberry.updateChecker.model.service;

import by.vsu.bramberry.updateChecker.model.entity.Computer;
import by.vsu.bramberry.updateChecker.model.entity.path.Path;
import by.vsu.bramberry.updateChecker.model.service.iservice.ComputerService;
import by.vsu.bramberry.updateChecker.model.service.iservice.PathService;
import by.vsu.bramberry.updateChecker.model.service.iservice.TransmitterService;
import by.vsu.bramberry.updateChecker.transfer.Transmitter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class TransmitterServiceImpl implements TransmitterService {
    private static final Logger logger = LoggerFactory.getLogger(TransmitterServiceImpl.class);
    private final ComputerService computerService;
    private final PathService pathService;


    @Autowired
    public TransmitterServiceImpl(ComputerService computerService, PathService pathService) {
        this.computerService = computerService;
        this.pathService = pathService;
    }

    @Override
    public void transmitAll() {
        int ap = Runtime.getRuntime().availableProcessors();
        ExecutorService es = Executors.newFixedThreadPool(ap);

        ArrayList<String> ipList = new ArrayList<>();
        List<Computer> computers = computerService.findAll(Pageable.unpaged());
        computers.forEach(computer -> ipList.add(computer.getIp()));


        List<Path> paths = pathService.findAll();
        List<Transmitter> threads = new ArrayList<>();
        try {
            for (String ip : ipList) {
                for (int i = 0; i < threads.size() - 1; i++) {
                    threads.add(new Transmitter(ip, paths));
                }
            }
            List<Future<String>> list = es.invokeAll(threads);
            es.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void transmit(String ip) {
        List<Path> paths = pathService.findAll();
        ExecutorService es = Executors.newSingleThreadExecutor();
        Transmitter transmitter = new Transmitter(ip, paths);
        Future<String> stringFuture = es.submit(transmitter);
        ObjectMapper mapper = new ObjectMapper();
        Computer computer = null;
        try {
            computer = mapper.readValue(stringFuture.get(), Computer.class);
        } catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        }
        if (computer != null) {
            computer.setIp(ip);
            computerService.update(computer);
        }
    }
}
