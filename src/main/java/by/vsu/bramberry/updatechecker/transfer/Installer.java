package by.vsu.bramberry.updatechecker.transfer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
public class Installer implements Runnable {
    private final RestTemplate restTemplate;
    private final String ip;
    private final String uri;
    private final String fileName;

    public Installer(String uri, String ip, String fileName) {
        this.fileName = fileName;
        this.ip = ip;
        this.uri = uri;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void run() {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://" + ip + ":6666/InitDownload")
                    .queryParam("filename", uri)
                    .queryParam("name", fileName);

            restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    null,
                    Object.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
