package com.edu.pe;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource data;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(data)
                .usersByUsernameQuery("select username, pass , estado from usuario where username=?")
                .authoritiesByUsernameQuery("select u.username, t.usuario "
                        + " from tipo_usuario t inner join usuario u  on u.id_tipo = t.id_tipo "
                        + " where u.username = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests().antMatchers("/img/**", "/css/**", "fonts/**", "/lib/**", "/login").permitAll()
                // Asignar permisos 
                .antMatchers("/comentario/**").hasAnyAuthority("Estudiante", "Docente")
                .antMatchers("/contenido/**").hasAnyAuthority("Estudiante", "Docente")
                .antMatchers("/curso/**").hasAnyAuthority("Estudiante", "Docente")
                .antMatchers("/foro/**").hasAnyAuthority("Estudiante", "Docente")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/acceso").permitAll().
                failureUrl("/login?error=true").and().
                logout().permitAll().logoutSuccessUrl("/login?logout");

    }
}
