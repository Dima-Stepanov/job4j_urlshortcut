package ru.job4j.urlshortcut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.job4j.urlshortcut.config.CodeGenerate;

@SpringBootApplication
public class UrlShortCutApplication {
    private static final Logger LOG = LoggerFactory.getLogger(UrlShortCutApplication.class.getSimpleName());

    @Bean
    public CodeGenerate gerCodeGenerate() {
        return new CodeGenerate.CodeGenerateBuilder()
                .useLower(true)
                .useUpper(true)
                .useDigits(true)
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(UrlShortCutApplication.class, args);
        LOG.info("Go to http://localhost:8080/");
    }

}
