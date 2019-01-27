package by.vsu.bramberry.updateChecker.scheduledtasks;

import by.vsu.bramberry.updateChecker.model.service.iservice.TransmitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class ScheduledTasks {


    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private final TransmitterService transmitterService;

    @Autowired
    public ScheduledTasks(TransmitterService transmitterService) {
        this.transmitterService = transmitterService;
    }

    @Scheduled(cron = "0 0 18 * * *")
    public void reportCurrentTime() throws ExecutionException, InterruptedException {
        log.info("Scheduled update at: {}",
                dateFormat.format(new Date()));
        transmitterService.transmitAll();
    }
}
