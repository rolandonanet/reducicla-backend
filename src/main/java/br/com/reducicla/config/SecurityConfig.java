package br.com.reducicla.config;

import br.com.reducicla.filter.JWTAuthenticationFilter;
import br.com.reducicla.filter.JWTAuthorizationFilter;
import br.com.reducicla.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author Lucas Copque on 19/05/2021
 */

/**
 * Classe de configuração do Spring Boot Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final UsuarioService usuarioService;

    @Autowired
    public SecurityConfig(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Adiciona configurações de cors e mapeamento de endpoints com autenticação e autorização.
     * @param http Objeto que contém informações da requisição http
     * @throws Exception Lança exeção caso algum erro for localizado em tempo de execução
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**/protected/**").hasAnyRole("USUARIO", "ADMIN")
                .antMatchers("/**/admin/**").hasRole("ADMIN")
                .antMatchers("/**/login", "/**/usuarios/save", "/**/posts").permitAll()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), usuarioService))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), usuarioService));
    }

    /**
     * Seta configurações iniciais de cors
     * @return Retorna um objeto com as configurações de cors setadas
     */
    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "DELETE", "PATCH"));
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Seta configurações como o spring boot deve validar a senha do usuário para autenticação
     * @param auth Objeto que contém informações de autenticação
     * @throws Exception Lança exeção caso algum erro for localizado em tempo de execução
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder(10));
    }
}