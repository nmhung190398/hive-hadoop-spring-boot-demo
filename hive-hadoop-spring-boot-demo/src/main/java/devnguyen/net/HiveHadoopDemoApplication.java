package devnguyen.net;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class HiveHadoopDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(HiveHadoopDemoApplication.class, args);
    }
}
