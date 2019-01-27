package by.vsu.bramberry.updateChecker.transfer;

import by.vsu.bramberry.updateChecker.model.entity.Computer;
import by.vsu.bramberry.updateChecker.model.entity.path.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.concurrent.Callable;

@Slf4j
public class Transmitter implements Callable<Computer> {
    private String ip;
    private List<Path> paths;
    private final RestTemplate restTemplate;


    public Transmitter(String ip, List<Path> paths) {
        this.ip = ip;
        this.paths = paths;
        restTemplate = new RestTemplate();
    }

    @Override
    public Computer call() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://" + ip + ":6666/GetMainInfo")
                .queryParam("paths", paths);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        //Отправляем пути к .exe файлам
        HttpEntity<Computer> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                Computer.class);

        return response.getBody();
    }
}
