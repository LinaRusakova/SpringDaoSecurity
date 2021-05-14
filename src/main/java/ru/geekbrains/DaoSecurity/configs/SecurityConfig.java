package ru.geekbrains.DaoSecurity.configs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                //    .antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN")
                //    .antMatchers("/profilepage/**").authenticated()
                //   .and()
                //    .httpBasic()
                .and()
                .logout().logoutSuccessUrl("/");
    }
}
