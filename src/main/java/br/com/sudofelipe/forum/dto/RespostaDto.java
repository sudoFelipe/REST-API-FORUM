package br.com.sudofelipe.forum.dto;

import java.time.LocalDateTime;

import br.com.sudofelipe.forum.modelo.Resposta;
import lombok.Getter;

public class RespostaDto {

	@Getter private Long id;
	@Getter private String mensagem;
	@Getter private LocalDateTime dataCriacao;
	@Getter private String nomeAutor;
	
	public RespostaDto(Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.dataCriacao = resposta.getDataCriacao();
		this.nomeAutor = resposta.getAutor().getNome();
	}
}
