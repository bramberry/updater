package by.vsu.bramberry.updatechecker.transfer;

import by.vsu.bramberry.updatechecker.model.entity.Computer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.Callable;

@Slf4j
public class Transmitter implements Callable<Computer> {
    private final RestTemplate restTemplate;
    private String ip;

    public Transmitter(String ip) {
        this.ip = ip;
        restTemplate = new RestTemplate();
    }

    @Override
    public Computer call() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://" + ip + ":6666/GetMainInfo");
                
        log.info("request: {}", builder.toUriString());

        //Отправляем пути к .exe файлам
        HttpEntity<Computer> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                Computer.class);
        log.info("response: {}", response.getBody());
        return response.getBody();
    }
}
