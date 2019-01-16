package by.vsu.bramberry.updateChecker.model.service.iservice;

import java.util.concurrent.ExecutionException;

public interface TransmitterService {
    void transmitAll() throws InterruptedException, ExecutionException;

    void transmit(String ipList) throws ExecutionException, InterruptedException;
}
