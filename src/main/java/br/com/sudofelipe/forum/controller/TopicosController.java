package br.com.sudofelipe.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sudofelipe.forum.dto.TopicoDto;
import br.com.sudofelipe.forum.modelo.Curso;
import br.com.sudofelipe.forum.modelo.Topico;

@RestController
public class TopicosController {

	@RequestMapping("/topicos")
	public List<TopicoDto> lista() {
		
		Topico topico = new Topico("Dúvida", "Dúvida em Spring Boot", new Curso("Spring Boot", "Programação"));
		
		return TopicoDto.converter(Arrays.asList(topico, topico, topico));
	}
}
