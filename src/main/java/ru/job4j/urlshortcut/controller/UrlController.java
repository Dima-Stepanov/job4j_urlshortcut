package ru.job4j.urlshortcut.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.urlshortcut.config.CodeGenerate;
import ru.job4j.urlshortcut.domain.Site;
import ru.job4j.urlshortcut.domain.UrlDTO;
import ru.job4j.urlshortcut.domain.UrlSite;
import ru.job4j.urlshortcut.service.SiteService;
import ru.job4j.urlshortcut.service.UrlService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.9.2.Контрольные вопросы
 * 2. Сервис - UrlShortCut [#13799]
 * UrlController rest контроллер модели UrlSite
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 29.07.2022
 */
@Validated
@RestController
public class UrlController {
    private static final Logger LOG = LoggerFactory.getLogger(UrlController.class.getSimpleName());
    private static final int CODE_LENGTH = 8;
    private final UrlService urls;
    private final SiteService sites;
    private final CodeGenerate codeGenerate;

    public UrlController(UrlService urls, CodeGenerate codeGenerate, SiteService sites) {
        this.sites = sites;
        this.urls = urls;
        this.codeGenerate = codeGenerate;
    }

    /**
     * Возвращает адрес по закодированной ссылке.
     *
     * @param code String code Url
     * @return ResponseEntity
     */
    @GetMapping("/redirect/{code}")
    public ResponseEntity<String> redirect(@PathVariable @NotBlank String code) {
        UrlSite url = urls.findUrlByCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var body = new HashMap<>() {{
            put("URL", url.getUrl());
        }}.toString();
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("HTTP CODE", "302 REDIRECT URL")
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(body.length())
                .body(body);
    }

    /**
     * Сохраняет адрес, и возвращает код адреса.
     *
     * @param urlDTO UrlDTO
     * @return ResponseEntity
     */
    @PostMapping("/convert")
    public ResponseEntity<Map<String, String>> convert(@Valid @RequestBody UrlDTO urlDTO,
                                                       Authentication authentication) {
        String login = authentication.getName();
        Site site = sites.findSiteByLogin(login)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        String code = this.codeGenerate.generate(CODE_LENGTH);
        UrlSite urlSite = UrlSite.of(urlDTO.getUrl(), code, site);
        this.urls.save(urlSite);
        return ResponseEntity.ok()
                .body(new HashMap<>() {{
                    put("code", code);
                }});
    }

    /**
     * Возвращает статистику по вызываемым ссылкам.
     *
     * @return Iterable
     */
    @GetMapping(value = "/statistic")
    public Iterable<UrlDTO> statistic(Authentication authentication) {
        LOG.info("Select statistic URL");
        String login = authentication.getName();
        Site site = sites.findSiteByLogin(login)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return this.urls.getStatistic(site);
    }
}
