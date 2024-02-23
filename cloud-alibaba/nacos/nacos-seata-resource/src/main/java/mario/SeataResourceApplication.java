package mario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zxz
 * @date 2024年02月22日 21:02
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SeataResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeataResourceApplication.class, args);
    }
}
