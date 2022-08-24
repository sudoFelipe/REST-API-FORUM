package br.com.sudofelipe.forum.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.sudofelipe.forum.modelo.Resposta;
import br.com.sudofelipe.forum.modelo.StatusTopico;
import br.com.sudofelipe.forum.modelo.Topico;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class DetalhesTopicoDto {

	@Getter private Long id;
	@Getter private String titulo;
	@Getter private String mensagem;
	@Getter private LocalDateTime dataCriacao;
	@Getter private String nomeAutor;
	@Getter private StatusTopico status;
	@Getter private List<RespostaDto> respostas;
	
	public DetalhesTopicoDto(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.nomeAutor = topico.getAutor().getNome();
		this.status = topico.getStatus();
		this.respostas = new ArrayList<RespostaDto>();
		this.respostas.addAll(topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList())); 
	}
}
