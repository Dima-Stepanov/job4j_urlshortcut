package ru.job4j.urlshortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.domain.UrlSite;
import ru.job4j.urlshortcut.repository.UrlRepository;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.9.2.Контрольные вопросы
 * 2. Сервис - UrlShortCut [#13799]
 * UrlService слой бизнес логики модели UrlSite.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 29.07.2022
 */
@Service
public class UrlService {
    private final UrlRepository urls;

    public UrlService(UrlRepository urls) {
        this.urls = urls;
    }

    public UrlSite save(UrlSite urlSite) {
        return this.urls.save(urlSite);
    }

    public Optional<UrlSite> findUrlByCode(String code) {
        return this.urls.findUrlSiteByCode(code);
    }
}
