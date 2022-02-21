package org.generation.blogPessoal.repository;

/** @Repository :informa ao Spring que esta é uma classe repositório.
 *  Em JpaRepository<Postagem,Long> :Postagem é o nome do Model, tipo é o primitivo Long, em vez de long
 *  Dica: Crie um método e nomeie com o sua função, como na linha 17
 */ 

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface PostagemRepository extends JpaRepository<Postagem,Long>{ 
		                                                                  
	public  List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);
}
