package br.com.reducicla.enumerated;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

@Getter
public enum Role {

    ADMIN(AuthorityUtils.createAuthorityList("ROLE_USUARIO", "ROLE_ADMIN")),
    COLABORADOR(AuthorityUtils.createAuthorityList("ROLE_USUARIO")),
    COLETOR(AuthorityUtils.createAuthorityList("ROLE_USUARIO"));

    private List<GrantedAuthority> authorities;

    Role(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
