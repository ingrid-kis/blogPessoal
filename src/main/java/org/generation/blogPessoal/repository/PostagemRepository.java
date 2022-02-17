package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //anotação que indica ao Spring q aqui é uma classe repositório
public interface PostagemRepository extends JpaRepository<Postagem,Long>{ //Postagem é o nome do Model
		                                                                  //tipo é o primitivo Long, em vez de long 
	public  List<Postagem> findAllByTituloContainingIgnoreCase (String titulo); //cria um método e seu nome é sua função

}
