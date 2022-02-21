package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @RestController :informa ao Spring que esta é uma classe Controller, controladora.
 *  @RequestMapping ("") :informa qual URI e parâmetro () que esta classe será acessada.
 *  @CrossOrigin("*") :indica que esta classe aceitará informações de qualquer origem.
 *  Classe controller :é a comunicação com o cliente (postman, angular etc).
 *  @Autowired :Interface não instancia, então indicamos o SPRING para que faça.
 *  @GetMapping :indica que se trata de um Método GET para funcionar. Logo abaixo indicamos qual.
 *  Para busca por id, deve se identificar o método HTTP que enviado à API, por ex.: @GetMapping +
 *	qual será o parâmetro que será enviado por quem estiver fazendo a requisição, por ex.: ("/{id}")
 *	@PathVariable :indica que a variável do caminho da URI(URL).
 *	
 *  Explicando o método, linha por linha:
 *
 *  @GetMapping("/{id}")
 *  public ResponseEntity<Postagem> getById(@PathVariable long id){
 *		return repository.findById(id)   
 *				.map(resp -> ResponseEntity.ok(resp))  
 * 				.orElse(ResponseEntity.notFound().build());
 * 
 *  Quando feita uma requisição do tipo GET em "postagens" e passar algum valor/atributo (no caso id),
 *  o método é acessado, e essa variável é capturada pela @PathVariable
 *  Um retorno é gerado à Interface com @Autowired, e o método findByID() é executado  
 *  Ele pode te devolver: tanto um objeto do tipo postagem com OK e um objeto dentro do corpo da requisição
 *  OU  devolver um NOTFOUND, caso esse objeto ñ exista ou tenha algum erro na requisição.
 *  
 *  @GetMapping("/titulo/{titulo}") :pode ser adicionado o título que o client vai passar na requisição
 *  ou passar um caminho com /nomeCaminho/ + {atributo}
 *  @ResquestBody :o método consegue puxar o que tem no corpo da requisição.
 *  @author ingrid-kis
 */

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem)); 
	}

	@PutMapping
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}	 
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	
}
