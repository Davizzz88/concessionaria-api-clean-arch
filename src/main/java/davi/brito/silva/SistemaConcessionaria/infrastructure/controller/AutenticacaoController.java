package davi.brito.silva.SistemaConcessionaria.infrastructure.controller;

import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.security.AutenticacaoRequest;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.security.TokenResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.ConcessionariaEntity;
import davi.brito.silva.SistemaConcessionaria.infrastructure.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/concessionaria/login")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    @PostMapping
    public ResponseEntity<TokenResponse> fazerLoginReceberToken(@RequestBody AutenticacaoRequest request){

        var tokenCpfSenha = new UsernamePasswordAuthenticationToken(request.nome(),request.senha());

        var authentication = authenticationManager.authenticate(tokenCpfSenha);

        var tokenAutenticado = (ConcessionariaEntity) authentication.getPrincipal();

        var gerarTokenJWT = tokenService.gerarToken(tokenAutenticado);

        return ResponseEntity.ok(new TokenResponse(gerarTokenJWT));

    }

}
