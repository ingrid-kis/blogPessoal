package org.generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity; //indica que essa classe será uma classe do JPA
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table; //indica que essa anotação, dentro da Entidade, será uma tabela
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//anotações @ definem certos tipos de comportamentos para as classes
@Entity
@Table(name = "postagem") //nome da tabela será Postagem
public class Postagem {
	
	//atributos da classe
	
	@Id //nome da anotaçao
	@GeneratedValue(strategy = GenerationType.IDENTITY) // como ela se comporta (autoincrement)
	//no DB, o @Id e @GeneratedValue se transformam em Primary Key
	private long id;
	
	@NotNull //não nulo
	@Size(min=5, max=100) //tamanho mín e máx
	// anotacao q nao pode receber titulo vazio
	private String titulo;
	
	@NotNull
	@Size(min=10, max=500)
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP) //anotação que indica q estamos trabalhando com tempo e qual o tipo ()
	private Date date = new java.sql.Date(System.currentTimeMillis());
	//captura o tempo exato da info que entrou nessa classe
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	
}
