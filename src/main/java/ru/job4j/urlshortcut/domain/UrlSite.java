package ru.job4j.urlshortcut.domain;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.9.2.Контрольные вопросы
 * 2. Сервис - UrlShortCut [#13799]
 * Url domain модель URL ссылки добавленные в систему для кодировки.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 28.07.2022
 */
@Entity
@Table(name = "urls")
public class UrlSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    @NotBlank(message = "Url must be not empty")
    private String url;
    @Column(unique = true, nullable = false)
    private String code;
    @ManyToOne
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;
    private int total;

    public static UrlSite of(String url, String code, Site site) {
        UrlSite urlSite = new UrlSite();
        urlSite.url = url;
        urlSite.code = code;
        urlSite.site = site;
        return urlSite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UrlSite urlSite = (UrlSite) o;
        return id == urlSite.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UrlSite{id=" + id + ", url='" + url + '\''
                + ", code='" + code + '\'' + ", site=" + site
                + ", total=" + total + '}';
    }
}
