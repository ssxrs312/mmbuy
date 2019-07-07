package study.mmbuyeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MmbuyEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MmbuyEurekaServerApplication.class, args);
	}

}
