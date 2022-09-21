package br.com.sudofelipe.forum.form;

import javax.validation.Valid;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;

public class LoginForm {

	@Getter private String email;
	@Getter private String senha;
	public UsernamePasswordAuthenticationToken converter() {

		return new UsernamePasswordAuthenticationToken(email, senha);
	}
}
