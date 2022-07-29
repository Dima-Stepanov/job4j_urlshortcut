package ru.job4j.urlshortcut.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.config.CodeGenerate;
import ru.job4j.urlshortcut.service.UrlService;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.9.2.Контрольные вопросы
 * 2. Сервис - UrlShortCut [#13799]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 29.07.2022
 */
@RestController
public class UrlController {
    private static final Logger LOG = LoggerFactory.getLogger(UrlController.class.getSimpleName());
    private static final int CODE_LENGTH = 5;
    private final UrlService urls;
    private final CodeGenerate codeGenerate;

    public UrlController(UrlService urls, CodeGenerate codeGenerate) {
        this.urls = urls;
        this.codeGenerate = codeGenerate;
    }
}
