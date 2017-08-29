package pl.gielerak.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@ComponentScan("pl.gielerak")
@Configuration
@EnableAutoConfiguration
@PropertySources({
        @PropertySource("classpath:/config.properties"),
        @PropertySource(value = "${ws.properties}", ignoreResourceNotFound = true)
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
