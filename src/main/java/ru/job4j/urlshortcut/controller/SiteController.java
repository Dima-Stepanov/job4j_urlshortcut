package ru.job4j.urlshortcut.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.config.CodeGenerate;
import ru.job4j.urlshortcut.domain.Site;
import ru.job4j.urlshortcut.domain.SiteDTO;
import ru.job4j.urlshortcut.service.SiteService;

import javax.validation.Valid;
import java.util.*;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.9.2.Контрольные вопросы
 * 2. Сервис - UrlShortCut [#13799]
 * SiteController rest контроллер модели site.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 29.07.2022
 */
@RestController
public class SiteController {
    private static final Logger LOG = LoggerFactory.getLogger(SiteController.class.getSimpleName());
    private static final int PASS_LENGTH = 5;
    private final SiteService sites;
    private final CodeGenerate codeGenerate;
    private final PasswordEncoder encoder;

    public SiteController(SiteService sites, CodeGenerate codeGenerate,
                          PasswordEncoder encoder) {
        this.encoder = encoder;
        this.sites = sites;
        this.codeGenerate = codeGenerate;
    }

    /**
     * Метод регистрации сайта
     *
     * @param siteDTO SiteDTO
     * @return ResponseEntity
     */
    @PostMapping("/registration")
    public ResponseEntity<Map<String, String>> registration(@Valid @RequestBody SiteDTO siteDTO) {
        LOG.info("Registration site={}", siteDTO);
        String password = codeGenerate.generate(PASS_LENGTH);
        Site site = Site.of(siteDTO.getSite(), encoder.encode(password), true);
        Site finalSite = this.sites.save(site);
        return ResponseEntity.ok()
                .body(new HashMap<String, String>() {{
                    put("registration", String.valueOf(finalSite.isRegistration()));
                    put("login", finalSite.getLogin());
                    put("password", password);
                }});
    }
}
