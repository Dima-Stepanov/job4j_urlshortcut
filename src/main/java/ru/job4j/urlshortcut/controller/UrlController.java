package ru.job4j.urlshortcut.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;
import ru.job4j.urlshortcut.config.CodeGenerate;
import ru.job4j.urlshortcut.domain.Site;
import ru.job4j.urlshortcut.domain.UrlSite;
import ru.job4j.urlshortcut.service.UrlService;

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
@RestController
public class UrlController {
    private static final Logger LOG = LoggerFactory.getLogger(UrlController.class.getSimpleName());
    private static final int CODE_LENGTH = 8;
    private final UrlService urls;
    private final CodeGenerate codeGenerate;

    public UrlController(UrlService urls, CodeGenerate codeGenerate) {
        this.urls = urls;
        this.codeGenerate = codeGenerate;
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<String> redirect(@PathVariable String code) {
        UrlSite url = urls.findUrlByCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(url.getUrl());
    }

    @PostMapping("/convert")
    public ResponseEntity<Map<String, String>> convert(@RequestBody Map<String, String> body) {
        String url = body.get("url");
        String code = this.codeGenerate.generate(CODE_LENGTH);
        Site site = new Site();
        site.setId(1);
        UrlSite urlSite = UrlSite.of(url, code, site);
        this.urls.save(urlSite);
        return ResponseEntity.ok()
                .body(new HashMap<>() {{
                    put("code", code);
                }});
    }
}
