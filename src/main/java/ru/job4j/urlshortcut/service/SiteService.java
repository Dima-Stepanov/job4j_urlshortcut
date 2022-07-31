package ru.job4j.urlshortcut.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.job4j.urlshortcut.domain.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
    private static final Logger LOG = LoggerFactory.getLogger(SiteService.class.getSimpleName());
    private final SiteRepository sites;

    public SiteService(SiteRepository sites) {
        this.sites = sites;
    }

    public Site save(Site site) {
        try {
            this.sites.save(site);
            LOG.info("Учетная запись создана={}", site);
        } catch (Exception e) {
            LOG.info("Учетная запись с именем={} уже существует", site.getLogin());
            site.setRegistration(false);
            site.setPassword(null);
        }
        return site;
    }

    public Optional<Site> findSiteByLogin(String login) {
        return sites.findSiteByLogin(login);
    }
}
