package br.com.reducicla.filter;

import br.com.reducicla.exception.InvalidTokenException;
import br.com.reducicla.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static br.com.reducicla.utils.SecurityConstants.*;

/**
 * @author User on 19/05/2021
 */

/**
 * Classe responsável por verificar e atribuir as permissões do usuário autenticado via JWT
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final UsuarioService usuarioService;

    @Autowired
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UsuarioService usuarioService) {
        super(authenticationManager);
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER);
        if (header == null) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        try {
            String token = request.getHeader(HEADER);
            if (token == null) return null;
            String username = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            UserDetails userDetails = usuarioService.loadUserByUsername(username);
            return username != null ? new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities()) : null;
        } catch (Exception ex) {
            throw new InvalidTokenException("Token Inválido. Acesse a aplicação novamente!");
        }
    }
}