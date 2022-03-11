package org.generation.blogPessoal.seguranca;
/* throws Exception = tratativa de erros.
 * http.authorizeRequests().antMatchers("/usuarios/logar").permitAll() =permite o endind point determinado ("//")
 * acessar esse caminho sem precisar de um TOKEN
 * .anyRequest().authenticated() = no Header o token deverá ser informado
 * sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS =bach-end não controla o tempo de sessão do user
 * o usuario nao é deslogado apos tempo determinado.
 * .csrf().disable() = csrf tipo de ataque recorrente, aqui desabilitado para deixar o código mais simples por motivos didáticos
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.inMemoryAuthentication().withUser("ingrid").password(passwordEncoder().encode("ingrid")).authorities("ROLE_ADMIN");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/usuarios/logar").permitAll()
		.antMatchers("/usuarios/cadastrar").permitAll()
		.antMatchers("/usuarios/all").permitAll()
		.antMatchers("/usuarios/atualizar").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors()
		.and().csrf().disable();
	}
	
}
