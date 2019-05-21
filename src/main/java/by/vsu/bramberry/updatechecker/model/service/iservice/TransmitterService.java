package by.vsu.bramberry.updatechecker.model.service.iservice;

import java.util.concurrent.ExecutionException;

public interface TransmitterService {
    void transmitAll(String audienceNumber) throws InterruptedException, ExecutionException;

    void transmitAll() throws InterruptedException, ExecutionException;

    void transmit(String ipList) throws ExecutionException, InterruptedException;

    void installInAudience(String fileName, String number);

    void installOnce(String fileName, String ip);
}
