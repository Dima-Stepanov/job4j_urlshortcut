package ru.job4j.urlshortcut.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.job4j.urlshortcut.domain.Site;
import ru.job4j.urlshortcut.domain.UrlDTO;
import ru.job4j.urlshortcut.domain.UrlSite;
import ru.job4j.urlshortcut.repository.UrlRepository;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

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

    /**
     * Возвращает ссылку по защитному коду.
     *
     * @param code String
     * @return Optional
     */
    public Optional<UrlSite> findUrlByCode(String code) {
        this.urls.updateUrlSiteBaTotal(code);
        return this.urls.findUrlSiteByCode(code);
    }

    /**
     * Возвращает список UrlDto для вывода статистики авторизованного пользователя.
     *
     * @return List
     */
    public List<UrlDTO> getStatistic(Site site) {
        List<UrlDTO> urlDTO = new CopyOnWriteArrayList<>();
        for (UrlSite urlSite : this.urls.findAllBySite(site)) {
            urlDTO.add(getUrlDTO(urlSite));
        }
        return urlDTO;
    }

    /**
     * Конвертация UrlSite в UrlDTO
     *
     * @param urlSite UrlSite
     * @return UrlDTO
     */
    public UrlDTO getUrlDTO(UrlSite urlSite) {
        return UrlDTO.of(urlSite.getUrl(), urlSite.getTotal());
    }
}
