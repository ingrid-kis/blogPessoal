package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
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
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name= "Recursos do Tema", description= "Administração dos temas")
@RequestMapping("/tema")
public class TemaController {

		@Autowired
		private TemaRepository repository;
		
	    @Operation(summary = "Busca lista de temas")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Retorna todos os temas"),
				@ApiResponse(responseCode = "400", description = "Retorno sem temas"),
	            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
		})
		@GetMapping
		public ResponseEntity<List<Tema>> getAll(){
			return ResponseEntity.ok(repository.findAll());
		}
		
	    @Operation(summary = "Busca temas por id")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Retorna temas existentes"),
				@ApiResponse(responseCode = "400", description = "Tema inexistente"),
	            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
		}) 
		@GetMapping("/{id}")
		public ResponseEntity<Tema> getById(@PathVariable long id){
			return repository.findById(id)
					.map(resp -> ResponseEntity.ok(resp))
					.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
		}
		
		@GetMapping("/descricao/{descricao}")
		public ResponseEntity<List<Tema>> getByTitulo(@PathVariable String descricao){
			return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
		}
		
	    @Operation(summary = "Cria novo tema")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "201", description = "Tema criado com sucesso"),
				@ApiResponse(responseCode = "400", description = "Erro na requisição"),
	            @ApiResponse(responseCode = "422", description = "Tema já existente"),
	            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
		})
		@PostMapping
		public ResponseEntity<Tema> post (@Valid @RequestBody Tema tema){
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(repository.save(tema));
		}
		
	    @Operation(summary = "Atualiza tema existente")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "201", description = "Retorna tema atualizado"),
				@ApiResponse(responseCode = "400", description = "Erro na requisição"),
	            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
		})
		@PutMapping
		public ResponseEntity<Tema> put(@Valid @RequestBody Tema tema){
			return repository.findById(tema.getId())
					.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema)))
					.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
		}
		
	    @Operation(summary = "Deleta tema existente")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Tema deletado"),
				@ApiResponse(responseCode = "400", description = "Id de tema inválido"),
		})
		@DeleteMapping("/{id}")
		public void delete(@PathVariable long id) {
	    	Optional<Tema> tema = repository.findById(id);

			if(tema.isEmpty())
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

	    	
			repository.deleteById(id);
		}
}
