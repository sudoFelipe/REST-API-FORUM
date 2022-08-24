package br.com.sudofelipe.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sudofelipe.forum.controller.form.TopicoForm;
import br.com.sudofelipe.forum.controller.form.UpdateTopicoForm;
import br.com.sudofelipe.forum.dto.DetalhesTopicoDto;
import br.com.sudofelipe.forum.dto.TopicoDto;
import br.com.sudofelipe.forum.modelo.Topico;
import br.com.sudofelipe.forum.repository.CursoRepository;
import br.com.sudofelipe.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepo;
	
	@Autowired
	private CursoRepository cursoRepo;
	
	@GetMapping
	public List<TopicoDto> lista(String curso) {
		//	DTO - dados disponibilizados para o cliente
		System.out.println(curso);
		List<Topico> topicos = curso == null ? topicoRepo.findAll() : topicoRepo.findByCursoNome(curso);
		
		return TopicoDto.converter(topicos);
	}
	
	@PostMapping
	@Transactional			//	Commita a Transação (Spring efetuará o commit automático da transação, caso nenhuma exception tenha sido lançada)
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		//	FORM - Dados enviados para o servidor
		Topico topico = form.converter(cursoRepo);
		topicoRepo.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesTopicoDto> detalhar(@PathVariable Long id) {	// ou public TopicoDto detalhar(@PathVariable("id") Long codigo) {
		
		Optional<Topico> topico = topicoRepo.findById(id);
		
		if (topico.isPresent()) {
			return ResponseEntity.ok(new DetalhesTopicoDto(topico.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")	//	Método executado ao ser executado uma requisião PUT
	@Transactional			//	Commita a Transação (Spring efetuará o commit automático da transação, caso nenhuma exception tenha sido lançada)
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid UpdateTopicoForm form) {
		
		Optional<Topico> optional = topicoRepo.findById(id);
		
		if (optional.isPresent()) {
			
			Topico topico = form.atualizar(id, topicoRepo);
			return ResponseEntity.ok(new TopicoDto(topico));
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional			//	Commita a Transação (Spring efetuará o commit automático da transação, caso nenhuma exception tenha sido lançada)
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		
		Optional<Topico> optional = topicoRepo.findById(id);
		
		if (optional.isPresent()) {
			
			topicoRepo.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();

	}
	
}
