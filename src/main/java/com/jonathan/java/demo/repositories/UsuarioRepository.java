package com.jonathan.java.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonathan.java.demo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{}
