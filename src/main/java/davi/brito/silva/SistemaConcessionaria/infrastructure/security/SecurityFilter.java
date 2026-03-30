package davi.brito.silva.SistemaConcessionaria.infrastructure.security;

import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.repository.ConcessionariaRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final ConcessionariaRepository concessionariaRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            var token = authHeader.replace("Bearer ", "");

            var validarToken = tokenService.validarToken(token);

            if (validarToken != null) {

                var concessionariaValidada = concessionariaRepository.findByNome(validarToken);

                if (concessionariaValidada != null) {

                    var confirmada = new UsernamePasswordAuthenticationToken(concessionariaValidada, null,
                            concessionariaValidada.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(confirmada);

                }
            }
        }

        filterChain.doFilter(request, response);

    }

}
