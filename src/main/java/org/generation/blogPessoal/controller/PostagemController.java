package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController //informa ao Spring q a classe é um Controlador
@RequestMapping("/postagens") //informa qual URI () q essa classe será acessada
@CrossOrigin("*") //essa classe aceitará infos de qq origem
public class PostagemController { //controller = comunicacao com o client (postman, angular etc)
	
	@Autowired //como é Interface, não instancia, então o SPRING faz isso
	private PostagemRepository repository;
	
	@GetMapping //expor q se trata de um metodo get para funcionar
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
}
