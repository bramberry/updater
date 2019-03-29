package by.vsu.bramberry.updatechecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UpdateCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpdateCheckerApplication.class, args);
    }
}
