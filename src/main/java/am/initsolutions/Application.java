package am.initsolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "am.initsolutions.models")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
