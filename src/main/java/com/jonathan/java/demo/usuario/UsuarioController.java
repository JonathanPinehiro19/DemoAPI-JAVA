package com.jonathan.java.demo.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public List<Usuario> getAll() {
		return usuarioRepository.findAll();
	}

	@GetMapping(path = "/{id}")
	public Usuario getOne(@PathVariable Long id) {
		if (usuarioRepository.existsById(id)) {
			return usuarioRepository.findById(id).get();
		}
		return null;
	}

	@PostMapping
	public Usuario post(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
		
	}
	
	@PatchMapping(path = "/{id}")
	public Usuario patch(@PathVariable Long id, @RequestBody Usuario usuario) {
		return null;
	}

}
