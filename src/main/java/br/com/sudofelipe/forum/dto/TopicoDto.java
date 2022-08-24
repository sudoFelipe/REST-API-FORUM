package br.com.sudofelipe.forum.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.sudofelipe.forum.modelo.Topico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TopicoDto {

	@Getter private Long id;
	@Getter private String titulo;
	@Getter private String mensagem;
	@Getter private LocalDateTime dataCriacao;
	
	
	
	public static List<TopicoDto> converter(List<Topico> topicos) {
		
		return topicos.stream().map(item -> new TopicoDto(item)).collect(Collectors.toList());
	}



	public TopicoDto(Topico topico) {
		super();
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}
}
