package br.com.sudofelipe.forum.config.validacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ErroFormularioDto {

	@Getter @Setter private String campo;
	@Getter @Setter private String erro;
}
