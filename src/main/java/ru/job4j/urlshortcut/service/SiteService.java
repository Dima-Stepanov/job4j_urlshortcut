package ru.job4j.urlshortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.domain.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.9.2.Контрольные вопросы
 * 2. Сервис - UrlShortCut [#13799]
 * SiteService слой бизнес логики модели Site.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 29.07.2022
 */
@Service
public class SiteService {
    private final SiteRepository sites;

    public SiteService(SiteRepository sites) {
        this.sites = sites;
    }

    public Site save(Site site) {
        return this.sites.save(site);
    }

    public Optional<Site> findSiteByLogin(String login) {
        return sites.findSiteByLogin(login);
    }
}
