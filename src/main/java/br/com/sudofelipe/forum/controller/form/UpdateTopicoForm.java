package br.com.sudofelipe.forum.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.sudofelipe.forum.modelo.Topico;
import br.com.sudofelipe.forum.repository.TopicoRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class UpdateTopicoForm {

	@NotNull @NotEmpty @Length(min = 5)
	@Getter @Setter private String titulo;
	
	@NotNull @NotEmpty @Length(min = 10)
	@Getter @Setter private String mensagem;

	public Topico atualizar(Long id, TopicoRepository repositorio) {
		
		Optional<Topico> topico = repositorio.findById(id);
		
		topico.get().setTitulo(this.titulo);
		topico.get().setMensagem(this.mensagem);
		
		return topico.get();
	}
}
