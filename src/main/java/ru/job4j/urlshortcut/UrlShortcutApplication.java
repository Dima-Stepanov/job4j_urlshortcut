package ru.job4j.urlshortcut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.job4j.urlshortcut.config.CodeGenerate;

@SpringBootApplication
public class UrlShortcutApplication {

    @Bean
    public CodeGenerate gerCodeGenerate() {
        return new CodeGenerate.CodeGenerateBuilder()
                .useLower(true)
                .useUpper(true)
                .useDigits(true)
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(UrlShortcutApplication.class, args);
    }

}
