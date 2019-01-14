package by.vsu.bramberry.updateChecker.transfer;

import by.vsu.bramberry.updateChecker.model.entity.path.Path;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Callable;

@Slf4j
public class Transmitter implements Callable<String> {
    private String ip;
    private List<Path> paths;
    private final RestTemplate restTemplate;


    public Transmitter(String ip, List<Path> paths) {
        this.ip = ip;
        this.paths = paths;
        restTemplate = new RestTemplate();
    }

    @Override
    public String call() throws Exception {

        InetAddress ipAddress = InetAddress.getByName(ip);
        int serverPort = 6666;
        log.info("Try to connect to socket with IP address {} and port {}", ip, serverPort);
        Socket socket = new Socket(ipAddress, serverPort);
        log.info("Connected to {}", ip);

       /* UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://%s:6666/")
                .queryParam("paths", paths);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        //Отправляем пути к .exe файлам
        HttpEntity<Computer> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                Computer.class);*/

        ObjectMapper objectMapper = new ObjectMapper();
        OutputStream sout = socket.getOutputStream();
        log.debug(objectMapper.writeValueAsString(paths));
        byte[] bytes = objectMapper.writeValueAsBytes(paths);
        sout.write(bytes);
        sout.flush();

        //Принимаем данные от клиента в формате json
        InputStream sin = socket.getInputStream();
        bytes = IOUtils.toByteArray(sin);
        String line = new String(bytes, StandardCharsets.UTF_8);
        log.info("received : {}", line);
        sin.close();
        sout.close();
        return line;
    }
}
