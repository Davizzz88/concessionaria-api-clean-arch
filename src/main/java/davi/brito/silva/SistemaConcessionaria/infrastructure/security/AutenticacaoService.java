package davi.brito.silva.SistemaConcessionaria.infrastructure.security;

import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.repository.ConcessionariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService {

    private final ConcessionariaRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByNome(username);
    }

}
