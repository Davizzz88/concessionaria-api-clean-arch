package davi.brito.silva.SistemaConcessionaria.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.ConcessionariaEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(ConcessionariaEntity concessionaria) {
        Instant expiresAt = LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
        try {
            Algorithm agth = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Concessionária")
                    .withSubject(concessionaria.getNome())
                    .withExpiresAt(expiresAt)
                    .sign(agth);
        }catch (Exception e) {
            throw new RuntimeException("Erro ao gerar token JWT:" + e.getCause());
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm agth = Algorithm.HMAC256(secret);
            return JWT.require(agth).withIssuer("API Concessionária")
                    .build().verify(token).getSubject();
        }catch (Exception e) {
            return "";
        }
    }
}
