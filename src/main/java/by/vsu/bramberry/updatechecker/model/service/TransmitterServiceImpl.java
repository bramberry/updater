package by.vsu.bramberry.updatechecker.model.service;

import by.vsu.bramberry.updatechecker.model.entity.Computer;
import by.vsu.bramberry.updatechecker.model.service.iservice.ComputerService;
import by.vsu.bramberry.updatechecker.model.service.iservice.TransmitterService;
import by.vsu.bramberry.updatechecker.transfer.Transmitter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
@Slf4j
public class TransmitterServiceImpl implements TransmitterService {
    private final ComputerService computerService;
    private CompletionService<Computer> service;

    @Autowired
    public TransmitterServiceImpl(ComputerService computerService) {
        this.computerService = computerService;
    }

    @PostConstruct
    private void init() {
        ExecutorService exec = Executors.newFixedThreadPool(Thread.activeCount());
        this.service = new ExecutorCompletionService<>(exec);
    }

    @Override
    public void transmitAll() throws InterruptedException, ExecutionException {
        ArrayList<String> ipList = Lists.newArrayList();
        List<Computer> computers = computerService.findAll();
        computers.forEach(computer -> ipList.add(computer.getIp()));

        for (String ip : ipList) {
            Transmitter transmitter = new Transmitter(ip);
            service.submit(transmitter);
        }
        computers = Lists.newArrayList();

        for (int i = 0; i < ipList.size(); i++) {
            Future<Computer> future = service.take();
            computers.add(future.get());
        }

        computers.forEach(computerService::update);
    }

    @Override
    public void transmit(String ip) throws ExecutionException, InterruptedException {
        Transmitter transmitter = new Transmitter(ip);
        Future<Computer> stringFuture = service.submit(transmitter);
        Computer computer = stringFuture.get();
        if (computer != null) {
            computer.setIp(ip);
            computerService.update(computer);
        }
    }
}