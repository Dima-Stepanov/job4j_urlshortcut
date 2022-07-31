package ru.job4j.urlshortcut;

import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.job4j.urlshortcut.config.CodeGenerate;

import javax.sql.DataSource;

@SpringBootApplication
public class UrlShortCutApplication {
    private static final Logger LOG = LoggerFactory.getLogger(UrlShortCutApplication.class.getSimpleName());

    /**
     * Создание PasswordEncoder для шифрования паролей.
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Создание объекта класса для генерации кодов
     * для паролей и защиты ссылок
     *
     * @return CodeGenerate
     */
    @Bean
    public CodeGenerate gerCodeGenerate() {
        return new CodeGenerate.CodeGenerateBuilder()
                .useLower(true)
                .useUpper(true)
                .useDigits(true)
                .build();
    }

    @Bean
    public SpringLiquibase liquibase(DataSource ds) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase-changeLog.xml");
        liquibase.setDataSource(ds);
        return liquibase;
    }

    public static void main(String[] args) {
        SpringApplication.run(UrlShortCutApplication.class, args);
        LOG.info("Go to http://localhost:8080/");
    }

}
