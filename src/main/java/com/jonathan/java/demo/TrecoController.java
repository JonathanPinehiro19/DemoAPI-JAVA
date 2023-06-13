package com.jonathan.java.demo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trecos")

public class TrecoController {
	private TrecoRepository trecoRepository;
	
	@GetMapping
	public List<Treco> getAll(){
		return trecoRepository.findAll();
		
	}
}
