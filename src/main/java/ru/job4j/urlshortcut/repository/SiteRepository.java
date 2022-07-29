package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.urlshortcut.domain.Site;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.9.2.Контрольные вопросы
 * 2. Сервис - UrlShortCut [#13799]
 * SiteRepository CRUD модели данных Site
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 29.07.2022
 */
@Repository
public interface SiteRepository extends CrudRepository<Site, Integer> {

    Optional<Site> findSiteByLogin(String login);
}
