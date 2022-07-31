package ru.job4j.urlshortcut.domain;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.9.2.Контрольные вопросы
 * 2. Сервис - UrlShortCut [#13799]
 * SiteDTO dto для модели Site.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 31.07.2022
 */
public class SiteDTO {
    @NotBlank(message = "Site must be not empty")
    private String site;

    public static SiteDTO of(String url) {
        SiteDTO siteDTO = new SiteDTO();
        siteDTO.site = url;
        return siteDTO;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SiteDTO siteDTO = (SiteDTO) o;
        return Objects.equals(site, siteDTO.site);
    }

    @Override
    public int hashCode() {
        return Objects.hash(site);
    }

    @Override
    public String toString() {
        return "SiteDTO{url='" + site + '\'' + '}';
    }
}
