package com.jonathan.java.demo.articles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
@RequestMapping("/articles")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;

	@GetMapping
	public List<Article> getAll() {

		// O método "findAll()" do JPA retorna todos os registros em uma lista.
		return articleRepository.findAll();
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public String getOne(@PathVariable Long id) throws JsonProcessingException {

		// Se o registro com o Id existe.
		if (articleRepository.existsById(id)) {

			// ObjectMapper tenta converter um objeto para JSON.
			ObjectMapper mapper = new ObjectMapper();

			// Obtém o registro pelo Id e armazena no objeto "treco".
			Article article = articleRepository.findById(id).get();

			// Retorna "treco" convertido para JSON (String → JSON).
			return mapper.writeValueAsString(article);
		}

		// Se o registro não existe, retorna o JSON.
		return "{ \"status\" : \"not found\" }";

	}

	@PostMapping
	public Article post(@RequestBody Article article) {

		// O método "save()" de JPA cria um novo registro
		// e armazena o objeto nele.
		return articleRepository.save(article);
	}

	@PatchMapping(path = "/{id}", produces = "application/json")
	public String updatePartial(@PathVariable Long id) throws JsonProcessingException {

		// Se o registro com o Id existe.
		if (articleRepository.existsById(id)) {

			// Obtém o registro do banco e armazena em "original".
			Article original = articleRepository.findById(id).get();
			Article novo = original;
			novo.setViews(original.getViews() + 1);

			// Salva o registro atualizado.
			articleRepository.save(novo);

			// Retorna o registro atualizado usando o método GET.
			// Nota: adicione "throws JsonProcessingException" ao método "updateAll()".
			return "{\"status\": \"updated\"}";

		}

		// Se o registro não existe, retorna o JSON.
		return "{ \"status\" : \"not found\" }";

	}
	
	 @DeleteMapping(path ="/articles/{id}")
	 public String deleteById(@PathVariable("id") Long id) {
	    return "Delete by id called";
	 }
	 
	 
	// Obtém os registros à partir de uma "string de busca".
		// Por exemplo, para pesquisar por "biscoito":
		// GET → http://localhost:8080/trecos/search/biscoito
		// A busca é "case-insensitive", ou seja, busca por "biscoito" ou "BISCOITO" tem
		// o mesmo resultado.
		// Retorna uma coleção ([]) com todos os registros em que os campos "name" e/ou
		// "description" contenham a "string de busca".
		// Se não encontrar nada, retorna {"status": "not-found"}.
		@GetMapping(path = "/search/{query}", produces = "application/json")
		public ResponseEntity<Object> search(@PathVariable String query) {
			List<Article> article = articleRepository.findByTitleContainingIgnoreCase(query);
			if (article.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": \"not-found\"}");
			}
			return ResponseEntity.ok(article);
		}

}