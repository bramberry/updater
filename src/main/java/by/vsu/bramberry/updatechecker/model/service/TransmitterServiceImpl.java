package by.vsu.bramberry.updatechecker.model.service;

import by.vsu.bramberry.updatechecker.model.entity.Computer;
import by.vsu.bramberry.updatechecker.model.service.iservice.ComputerService;
import by.vsu.bramberry.updatechecker.model.service.iservice.TransmitterService;
import by.vsu.bramberry.updatechecker.model.service.iservice.UploadFileService;
import by.vsu.bramberry.updatechecker.transfer.Installer;
import by.vsu.bramberry.updatechecker.transfer.Transmitter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.*;

@Service
@Slf4j
public class TransmitterServiceImpl implements TransmitterService {
    private final ComputerService computerService;
    private final UploadFileService uploadFileService;
    private CompletionService<Computer> service;

    @Autowired
    public TransmitterServiceImpl(ComputerService computerService, UploadFileService uploadFileService) {
        this.computerService = computerService;
        this.uploadFileService = uploadFileService;
    }

    @PostConstruct
    private void init() {
      ExecutorService exec = Executors.newFixedThreadPool(30);
        this.service = new ExecutorCompletionService<>(exec);
    }

    @Override
    public void transmitAll(String audienceNumber) throws InterruptedException, ExecutionException {
        List<Computer> computers = computerService.findAllByAudienceNumber(audienceNumber);
        update(computers);
    }

    @Override
    public void transmitAll() throws InterruptedException, ExecutionException {
        List<Computer> computers = computerService.findAll();
        update(computers);
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

    public void installInAudience(String fileName, String number) {
        String downloadUri = uploadFileService.getByFileName(fileName).getFileDownloadUri();

        List<Computer> computers = computerService.findAllByAudienceNumber(number);

        computers.forEach(computer -> {
            Installer installer = new Installer(downloadUri, computer.getIp(), fileName);
            new Thread(installer).start();
        });

    }

    public void installOnce(String fileName, String ip) {
        String downloadUri = uploadFileService.getByFileName(fileName).getFileDownloadUri();
        Installer installer = new Installer(downloadUri, ip, fileName);
        new Thread(installer).start();
    }

    private void update(List<Computer> computers) throws InterruptedException, ExecutionException {
        computers.forEach(computer -> {
            Transmitter transmitter = new Transmitter(computer.getIp());
            service.submit(transmitter);
        });
      int size = computers.size();
        computers = Lists.newArrayList();

      for (int i = 0; i < size; i++) {
            Future<Computer> future = service.take();
            computers.add(future.get());
        }

        computers.forEach(computerService::update);
    }
}