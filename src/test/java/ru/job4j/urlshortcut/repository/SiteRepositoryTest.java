package ru.job4j.urlshortcut.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.job4j.urlshortcut.domain.Site;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.9.2.Контрольные вопросы
 * 2. Сервис - UrlShortCut [#13799]
 * SiteRepository TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 16.06.2023
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class SiteRepositoryTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private SiteRepository siteRepository;

    @BeforeEach
    public void clearDataBaseTest() {
        entityManager.createQuery("delete from UrlSite").executeUpdate();
        entityManager.createQuery("delete from Site").executeUpdate();
    }

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(entityManager).isNotNull();
        assertThat(siteRepository).isNotNull();
    }

    @Test
    public void whenFindSiteByLoginThenReturnEmpty() {
        Site site = Site.of("login", "password", true);
        var actual = siteRepository.findSiteByLogin(site.getLogin());
        assertThat(actual).isEmpty();
    }

    @Test
    public void whenFindSiteByLoginThenReturnLogin() {
        Site site = Site.of("login", "password", true);
        entityManager.persist(site);
        var actual = siteRepository.findSiteByLogin(site.getLogin());
        assertThat(actual).isEqualTo(Optional.of(site));
    }

    @Test
    public void whenFindSiteMoreLoginThenReturnEmpty() {
        Site site = Site.of("login", "password", true);
        entityManager.persist(site);
        var actual = siteRepository.findSiteByLogin("LOGOGO");
        assertThat(actual).isEmpty();
    }

    @Test
    public void whenFindSiteLoginThenReturnSite() {
        Site site = Site.of("login", "password", true);
        Site site1 = Site.of("login1", "password1", true);
        entityManager.persist(site);
        entityManager.persist(site1);
        var actual = siteRepository.findSiteByLogin(site1.getLogin());
        assertThat(actual).isEqualTo(Optional.of(site1));
    }
}