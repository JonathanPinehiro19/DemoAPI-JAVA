package com.jonathan.java.demo.contacts;


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
@RequestMapping("/contacts")
public class ContactController {

	@Autowired
	private ContactRepository contactRepository;

	@GetMapping
	public List<Contact> getAll() {
		return contactRepository.findAll();
	}

	@GetMapping(path = "/{id}")
	public Contact getOne(@PathVariable Long id) {
		if (contactRepository.existsById(id)) {
			return contactRepository.findById(id).get();
		}
		return null;
	}

	@PostMapping
	public Contact post(@RequestBody Contact contato) {
		return contactRepository.save(contato);
		
	}
	
	@PatchMapping(path = "/{id}")
	public Contact patch(@PathVariable Long id, @RequestBody Contact contato) {
		return null;
	}

}
