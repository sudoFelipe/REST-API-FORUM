package br.com.sudofelipe.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.sudofelipe.forum.modelo.Curso;
import br.com.sudofelipe.forum.modelo.Topico;
import br.com.sudofelipe.forum.repository.CursoRepository;
import lombok.Getter;
import lombok.Setter;

public class TopicoForm {

	@NotNull @NotEmpty @Length(min = 5)
	@Getter @Setter private String titulo;
	
	@NotNull @NotEmpty @Length(min = 10)
	@Getter @Setter private String mensagem;
	
	@NotNull @NotEmpty
	@Getter @Setter private String nomeCurso;
	
	public Topico converter(CursoRepository repository) {
		
		Curso curso = repository.findByNome(nomeCurso);
		
		return new Topico(titulo, mensagem, curso);
	}
}
