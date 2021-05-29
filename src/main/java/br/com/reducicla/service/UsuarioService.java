package br.com.reducicla.service;

import br.com.reducicla.dto.request.UsuarioRequestDTO;
import br.com.reducicla.enumerated.Role;
import br.com.reducicla.exception.ResourceNotFoundException;
import br.com.reducicla.model.Colaborador;
import br.com.reducicla.model.Coletor;
import br.com.reducicla.model.Material;
import br.com.reducicla.model.Usuario;
import br.com.reducicla.repository.ColaboradorRepository;
import br.com.reducicla.repository.ColetorRepository;
import br.com.reducicla.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author User on 19/05/2021
 */

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final ColetorRepository coletorRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, ColaboradorRepository colaboradorRepository, ColetorRepository coletorRepository) {
        this.usuarioRepository = usuarioRepository;
        this.colaboradorRepository = colaboradorRepository;
        this.coletorRepository = coletorRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(10);
    }

    public Object save(UsuarioRequestDTO usuarioRequestDTO) {
        if (usuarioRequestDTO.getRole() == Role.COLETOR) {
            Coletor coletor = new Coletor(usuarioRequestDTO);
            coletor.setSenha(this.passwordEncoder.encode(coletor.getSenha()));
            return this.coletorRepository.save(coletor);
        } else if (usuarioRequestDTO.getRole() == Role.COLABORADOR) {
            Colaborador colaborador = new Colaborador(usuarioRequestDTO);
            colaborador.setSenha(this.passwordEncoder.encode(colaborador.getSenha()));
            return this.colaboradorRepository.save(colaborador);
        } else {
            Usuario usuario = new Usuario(usuarioRequestDTO);
            usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
            return this.usuarioRepository.save(usuario);
        }
    }

    public Coletor save(Coletor coletor) {
        coletor.setSenha(this.passwordEncoder.encode(coletor.getSenha()));
        return this.usuarioRepository.save(coletor);
    }

    public Colaborador save(Colaborador colaborador) {
        colaborador.setSenha(this.passwordEncoder.encode(colaborador.getSenha()));
        return this.usuarioRepository.save(colaborador);
    }

    public void addMaterial(Material material, Colaborador colaborador) {
        colaborador.getMateriais().add(material);
        this.save(colaborador);
    }

    public Usuario findById(Long id) {
        return this.usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    public Usuario findByEmail(String email) {
        return this.usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    public Page<Usuario> findAll(Pageable pageable) {
        return this.usuarioRepository.findAll(pageable);
    }

    public void delete(Usuario usuario) {
        this.usuarioRepository.delete(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = this.findByEmail(username);

        String user = usuario.getEmail();
        String password = usuario.getSenha();
        List<GrantedAuthority> authorities = usuario.getRole().getAuthorities();

        return new User(user, password, true, true, true, true, authorities);
    }
}
