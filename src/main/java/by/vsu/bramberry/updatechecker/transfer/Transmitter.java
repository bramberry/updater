package by.vsu.bramberry.updatechecker.transfer;

import by.vsu.bramberry.updatechecker.model.entity.Computer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Callable;

@Slf4j
public class Transmitter implements Callable<Computer> {
    private final RestTemplate restTemplate;
    private String ip;
    private List<String> paths;


    public Transmitter(String ip, List<String> paths) {
        this.ip = ip;
        this.paths = paths;
        restTemplate = new RestTemplate();
    }

    @Override
    public Computer call() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://" + ip + ":6666/GetMainInfo")
                .queryParam("paths", paths).encode(StandardCharsets.UTF_8);
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
