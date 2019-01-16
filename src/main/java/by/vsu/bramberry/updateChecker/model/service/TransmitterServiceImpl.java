package by.vsu.bramberry.updateChecker.model.service;

import by.vsu.bramberry.updateChecker.model.entity.Computer;
import by.vsu.bramberry.updateChecker.model.entity.path.Path;
import by.vsu.bramberry.updateChecker.model.service.iservice.ComputerService;
import by.vsu.bramberry.updateChecker.model.service.iservice.PathService;
import by.vsu.bramberry.updateChecker.model.service.iservice.TransmitterService;
import by.vsu.bramberry.updateChecker.transfer.Transmitter;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
@AllArgsConstructor
@Slf4j
public class TransmitterServiceImpl implements TransmitterService {
    private final ComputerService computerService;
    private final PathService pathService;
    private List<Path> paths;
    private CompletionService<Computer> service;

    @PostConstruct
    private void init() {
        this.paths = pathService.findAll();
    }

    @Override
    public void transmitAll() throws InterruptedException, ExecutionException {
        ArrayList<String> ipList = Lists.newArrayList();
        List<Computer> computers = computerService.findAll();
        computers.forEach(computer -> ipList.add(computer.getIp()));

        for (String ip : ipList) {
            Transmitter transmitter = new Transmitter(ip, paths);
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
        Transmitter transmitter = new Transmitter(ip, paths);
        Future<Computer> stringFuture = service.submit(transmitter);
        Computer computer = stringFuture.get();
        if (computer != null) {
            computer.setIp(ip);
            computerService.update(computer);
        }
    }
}