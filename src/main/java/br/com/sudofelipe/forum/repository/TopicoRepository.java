package br.com.sudofelipe.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sudofelipe.forum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

	/**
	 * Busca pelo relacionamento do curso e retorna o atributo nome do curso
	 * Query automática JPA
	 * @param titulo
	 * @return
	 */
	List<Topico> findByCursoNome(String curso);
	
//	List<Topico> findByCurso_Nome(String curso);	(Exemplo para resolver ambiguidade de nomes)
//	Neste Exemplo o underline, informa que o atributo nome está dentro da entidade curso
	
	/**
	 * Busca pelo relacionamento do curso e retorna o atributo nome do curso com a query
	 * Criação da query manual
	 * @param curso
	 * @return
	 */
	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :curso")
	List<Topico> buscarCurso(@Param("curso") String curso);

}
