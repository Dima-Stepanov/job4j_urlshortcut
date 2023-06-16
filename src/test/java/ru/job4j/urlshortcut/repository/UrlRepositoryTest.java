package ru.job4j.urlshortcut.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.job4j.urlshortcut.domain.Site;
import ru.job4j.urlshortcut.domain.UrlSite;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.9.2.Контрольные вопросы
 * 2. Сервис - UrlShortCut [#13799]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 16.06.2023
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class UrlRepositoryTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UrlRepository urlRepository;

    @BeforeEach
    public void clearDataBaseTest() {
        entityManager.createQuery("delete from UrlSite").executeUpdate();
        entityManager.createQuery("delete from Site").executeUpdate();
    }

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(entityManager).isNotNull();
        assertThat(urlRepository).isNotNull();
    }

    @Test
    void whenFindUrlSiteByCodeThenReturnEmpty() {
        Site site1 = Site.of("login_site1", "passwors_site1", true);
        entityManager.persist(site1);
        UrlSite urlSite = UrlSite.of("url_site", "code", site1);
        var actual = urlRepository.findUrlSiteByCode(urlSite.getCode());
        assertThat(actual).isEmpty();
    }

    @Test
    void whenFindUrlSiteByCodeThenReturnUrlSute() {
        Site site1 = Site.of("login_site1", "passwors_site1", true);
        entityManager.persist(site1);
        UrlSite urlSite = UrlSite.of("url_site", "code", site1);
        entityManager.persist(urlSite);
        var actual = urlRepository.findUrlSiteByCode(urlSite.getCode());
        assertThat(actual).isEqualTo(Optional.of(urlSite));
    }

    @Test
    void whenSaveTuUrlSiteFindUrlSiteByCodeThenReturnUrlSite1() {
        Site site1 = Site.of("login_site1", "passwors_site1", true);
        entityManager.persist(site1);
        UrlSite urlSite = UrlSite.of("url_site", "code", site1);
        UrlSite urlSite1 = UrlSite.of("url_site1", "code1", site1);
        entityManager.persist(urlSite);
        entityManager.persist(urlSite1);
        var actual = urlRepository.findUrlSiteByCode(urlSite1.getCode());
        assertThat(actual).isEqualTo(Optional.of(urlSite1));
    }

    @Test
    void whenFindAllBySiteReturnEmpty() {
        Site site1 = Site.of("login_site1", "passwors_site1", true);
        entityManager.persist(site1);
        var actual = urlRepository.findAllBySite(site1);
        assertThat(actual.iterator().hasNext()).isFalse();
    }

    @Test
    void whenFindAllBySiteReturnIteratorTwoUrlSite() {
        Site site1 = Site.of("login_site1", "passwors_site1", true);
        entityManager.persist(site1);
        UrlSite urlSite = UrlSite.of("url_site", "code", site1);
        UrlSite urlSite1 = UrlSite.of("url_site1", "code1", site1);
        entityManager.persist(urlSite);
        entityManager.persist(urlSite1);
        var actual = urlRepository.findAllBySite(site1);
        var iterator = actual.iterator();
        assertThat(iterator.next()).isEqualTo(urlSite);
        assertThat(iterator.next()).isEqualTo(urlSite1);
    }

    @Test
    void whenUpdateUrlSiteBaTotalWhenTotalAddOne() {
        Site site1 = Site.of("login_site1", "passwors_site1", true);
        entityManager.persist(site1);

        UrlSite urlSite = UrlSite.of("url_site", "code", site1);
        entityManager.persist(urlSite);
        entityManager.flush();
        entityManager.clear();

        urlRepository.updateUrlSiteBaTotal(urlSite.getCode());
        var actual = urlRepository.findUrlSiteByCode(urlSite.getCode());

        assertThat(actual.get().getTotal()).isEqualTo(1);
    }

    @Test
    void whenUpdateUrlSiteBaTotalWhenTotalAddTwo() {
        Site site1 = Site.of("login_site1", "passwors_site1", true);
        entityManager.persist(site1);

        UrlSite urlSite = UrlSite.of("url_site", "code", site1);
        entityManager.persist(urlSite);
        entityManager.flush();
        entityManager.clear();

        urlRepository.updateUrlSiteBaTotal(urlSite.getCode());
        urlRepository.updateUrlSiteBaTotal(urlSite.getCode());
        var actual = urlRepository.findUrlSiteByCode(urlSite.getCode());

        assertThat(actual.get().getTotal()).isEqualTo(2);
    }
}