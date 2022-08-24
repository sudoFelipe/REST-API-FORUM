package br.com.sudofelipe.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sudofelipe.forum.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

	Curso findByNome(String nomeCurso);

}
