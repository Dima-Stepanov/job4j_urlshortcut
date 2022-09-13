package ru.job4j.urlshortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.domain.Site;
import ru.job4j.urlshortcut.domain.UrlSite;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.9.2.Контрольные вопросы
 * 2. Сервис - UrlShortCut [#13799]
 * UrlRepository CRUD модели UrlSite
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 29.07.2022
 */
@Repository
public interface UrlRepository extends CrudRepository<UrlSite, Integer> {
    /**
     * Поиск сайта по коду
     *
     * @param code Код сайта
     * @return Optional
     */
    Optional<UrlSite> findUrlSiteByCode(String code);

    Iterable<UrlSite> findAllBySite(Site site);

    /**
     * Метод для увеличения счетчика статистики вызова каждого адреса.
     *
     * @param code String уникальный код для ссылки.
     */
    @Transactional
    @Modifying
    @Query("update UrlSite u set u.total = u.total + 1 where u.code=:code")
    void updateUrlSiteBaTotal(@Param("code") String code);
}
