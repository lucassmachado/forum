package br.com.alura.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.modelo.Curso;

public interface CursosRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nomeCurso);

}
