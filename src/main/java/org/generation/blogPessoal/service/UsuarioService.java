package org.generation.blogPessoal.service;

/* L26 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();//estancionar objeto enconder
 * L29 usuario.setSenha(senhaEnconder); //senha incriptada
 * L30 return repository.save(usuario); //salvar senha incriptada
 * L34 public Optional<UserLogin> Logar(Optional<UserLogin> user){ //UserLogin, porque:
 * quer retornar ao usuario os atributos: nome, usuario, senha, token
 * L47 user.get().setToken(authHeader); //token preenchido
 */
import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import java.util.Optional;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	//método público
	public Usuario CadastrarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
		String senhaEnconder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEnconder);
		
		return repository.save(usuario); 
	}

	public Optional<UserLogin> Logar(Optional<UserLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				
				user.get().setToken(authHeader); //token preenchido
				user.get().setNome(usuario.get().getNome());
				
				return user;
			}
		}
		
		return null;
	}
}
