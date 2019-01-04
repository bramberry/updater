package by.vsu.bramberry.updateChecker.transfer;

import by.vsu.bramberry.updateChecker.model.entity.path.Path;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Callable;

public class Transmitter implements Callable<String> {
    private static final Logger logger = LoggerFactory.getLogger(Transmitter.class);
    private String ip;
    private List<Path> paths;

    public Transmitter(String ip, List<Path> paths) {
        this.ip = ip;
        this.paths = paths;
    }

    @Override
    public String call() throws Exception {

        InetAddress ipAddress = InetAddress.getByName(ip);
        int serverPort = 6666;
        logger.info("Try to connect to socket with IP address {} and port {}", ip, serverPort);
        Socket socket = new Socket(ipAddress, serverPort);
        logger.info("Connected to {}", ip);

        //Отправляем пути к .exe файлам
        ObjectMapper objectMapper = new ObjectMapper();
        OutputStream sout = socket.getOutputStream();
        logger.debug(objectMapper.writeValueAsString(paths));
        byte[] bytes = objectMapper.writeValueAsBytes(paths);
        sout.write(bytes);
        sout.flush();

        //Принимаем данные от клиента в формате json
        InputStream sin = socket.getInputStream();
        bytes = IOUtils.toByteArray(sin);
        String line = new String(bytes, StandardCharsets.UTF_8);
        logger.info("The client sent this : {}", line);
        sin.close();
        sout.close();
        return line;
    }
}
