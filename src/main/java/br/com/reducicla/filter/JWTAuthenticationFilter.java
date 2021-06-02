package br.com.reducicla.filter;

import br.com.reducicla.dto.response.CredencialResponseDTO;
import br.com.reducicla.exception.InvalidPasswordException;
import br.com.reducicla.exception.ResourceNotFoundException;
import br.com.reducicla.model.Usuario;
import br.com.reducicla.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static br.com.reducicla.utils.SecurityConstants.*;

/**
 * @author User on 19/05/2021
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Logger logger = Logger.getLogger(JWTAuthenticationFilter.class.getName());

    @Autowired
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        this.passwordEncoder = new BCryptPasswordEncoder(10);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            Usuario login = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            String email = login.getEmail();
            String senha = login.getSenha();

            Usuario usuario = this.usuarioService.findByEmail(email);
            if(passwordEncoder.matches(senha, usuario.getSenha())){
                return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, senha));
            }

            throw new InvalidPasswordException("Senha incorreta");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Falha na autenticação do usuário", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {

        String email = authResult.getName();

        String token = Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + TIME_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
        String bearerToken = TOKEN_PREFIX + token;

        Usuario usuario = usuarioService.findByEmail(email);

        CredencialResponseDTO credencialResponseDTO = CredencialResponseDTO
                .Builder
                .create()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .role(usuario.getRole())
                .token(bearerToken)
                .build();
        response.getWriter().write(new Gson().toJson(credencialResponseDTO));
        response.addHeader(HEADER, bearerToken);
        logger.log(Level.INFO, email + " autenticado com sucesso. O token gerado expira em " + TimeUnit.DAYS.convert(TIME_EXPIRATION, TimeUnit.MILLISECONDS) + " dia(s)");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {

        if (failed.getCause() instanceof ResourceNotFoundException) {
            response.sendError((HttpServletResponse.SC_NOT_FOUND), failed.getMessage());
        }
    }
}
