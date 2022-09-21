package br.com.sudofelipe.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sudofelipe.forum.config.security.TokenService;
import br.com.sudofelipe.forum.dto.TokenDto;
import br.com.sudofelipe.forum.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
		
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			
			Authentication autentication = authManager.authenticate(dadosLogin);
			
			String token = tokenService.gerarToken(autentication);
			
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
			
		} catch (Exception e) {
			
			return ResponseEntity.badRequest().build();
		}
		
	}
}
