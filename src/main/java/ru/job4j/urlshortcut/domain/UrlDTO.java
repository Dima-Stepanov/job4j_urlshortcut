package ru.job4j.urlshortcut.domain;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.9.2.Контрольные вопросы
 * 2. Сервис - UrlShortCut [#13799]
 * UrlDTO dto для модели UrlSite.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 30.07.2022
 */
public class UrlDTO {
    @NotBlank(message = "Url must be not empty")
    private String url;
    private Integer total;

    public static UrlDTO of(String url, Integer total) {
        UrlDTO urlDTO = new UrlDTO();
        urlDTO.url = url;
        urlDTO.total = total;
        return urlDTO;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
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
        UrlDTO urlDTO = (UrlDTO) o;
        return Objects.equals(url, urlDTO.url) && Objects.equals(total, urlDTO.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, total);
    }

    @Override
    public String toString() {
        return "UrlDTO{url='" + url + '\'' + ", total=" + total + '}';
    }
}
