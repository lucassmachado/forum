package br.com.alura.forum.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursosRepository;
import br.com.alura.forum.repository.TopicosRepository;

@RestController
public class TopicosController {

	@Autowired
	private TopicosRepository topicosRepository;

	@Autowired
	private CursosRepository cursosRepository;

	@GetMapping("/topicos")
	public List<TopicoDto> listar(String nomeCurso) {
		if (nomeCurso == null) {
			return TopicoDto.converter(topicosRepository.findAll());
		}
		return TopicoDto.converter(topicosRepository.findByCursoNome(nomeCurso));
	}

	@PostMapping
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(cursosRepository);
		topicosRepository.save(topico);

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}

}
