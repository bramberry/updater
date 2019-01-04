package by.vsu.bramberry.updateChecker;

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
