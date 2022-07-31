package ru.job4j.urlshortcut.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.domain.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;

import java.util.Collections;
import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.9.2.Контрольные вопросы
 * 2. Сервис - UrlShortCut [#13799]
 * SiteDetailService сервис будет загружать
 * в SecutiryHolder детали авторизованного пользователя.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 30.07.2022
 */
@Service
public class SiteDetailService implements UserDetailsService {
    private final SiteRepository sites;

    public SiteDetailService(SiteRepository sites) {
        this.sites = sites;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Site user = sites.findSiteByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(user.getLogin(), user.getPassword(), Collections.emptyList());
    }
}
