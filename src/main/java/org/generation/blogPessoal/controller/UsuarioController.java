package org.generation.blogPessoal.controller;

import java.util.List;

/*
 * L41 Em vez de injetar, com @Autowired, o repositorio, vamos injetar a classe a classe de serviço
 * @CrossOrigin(origins = "*", allowedHeaders = "*") =para não dar erro no front, qualquer api, até externa pode acessar
 */

import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuarios")
@Tag(name= "Recursos do Usuário", description= "Administração de uso do usuário no sistema")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Operation(summary = "Faz login do usuário")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário logado com sucesso"),
			@ApiResponse(responseCode = "400", description = "E-mail ou senha inválido"),
            @ApiResponse(responseCode = "422", description = "Usuário já cadastrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> Authentication(@RequestBody Optional<UserLogin> user){
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@Operation(summary = "Faz cadastro do usuário")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Erro na requisição"),
            @ApiResponse(responseCode = "422", description = "Usuário já cadastrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post (@RequestBody Usuario usuario){
		return usuarioService.CadastrarUsuario(usuario).map(resp -> ResponseEntity.status(201).body(resp))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@Operation(summary = "Busca lista de usuários no sistema")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Retorna todo os usuários"),
			@ApiResponse(responseCode = "400", description = "Retorno sem usuários"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
	@GetMapping("/all")
    public ResponseEntity <List<Usuario>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }
	
	@Operation(summary = "Atualiza usuário existente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Retorna usuário atualizado"),
			@ApiResponse(responseCode = "400", description = "Erro na requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@PutMapping("/atualizar")
    public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario){
        return usuarioService.atualizarUsuario(usuario)
            .map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
            .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
	
	@Operation(summary = "Busca usuário por id")
	@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Retorna usuário existente"),
				@ApiResponse(responseCode = "400", description = "Usuário inexistente"),
	            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
		})
	@GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable long id) {
        return repository.findById(id)
            .map(resp -> ResponseEntity.ok(resp))
            .orElse(ResponseEntity.notFound().build());
    }
}
