package org.generation.blogPessoal.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
    
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start(){

		usuarioRepository.deleteAll();
		
		usuarioRepository.save(new Usuario(0L, "Ingrid Kis",  "ingrid@email.com.br", "12345"));
		
		usuarioRepository.save(new Usuario(0L, "Ferenc Kis", "ferenc@email.com.br", "12345"));
		
		usuarioRepository.save(new Usuario(0L, "Erzsébet Kis", "erzsebet@email.com.br", "12345"));

        usuarioRepository.save(new Usuario(0L, "Bruno Duarte", "bruno@email.com.br", "12345"));

	}

	@Test
	@DisplayName("Retornar 1 usuario")
	public void deveRetornarUmUsuario() {

		Optional<Usuario> usuario = usuarioRepository.findByUsuario("ingrid@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("ingrid@email.com.br"));
	}

	@Test
	@DisplayName("Retornar 3 usuarios")
	public void deveRetornarTresUsuarios() {

		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Kis");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Ingrid Kis"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Ferenc Kis"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Erzsébet Kis"));
		
	}

}