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

/** Anotações @ definem certos tipos de comportamentos para as classes.
 *  @Table(name = "postagem") :indica que nome da tabela será postagem.
 *  @Id :nome da anotação
 *  @GeneratedValue(strategy = GenerationType.IDENTITY) :como ela se comporta, nesse caso autoincrement.
 *  No DB, o @Id e @GeneratedValue se transformam em Primary Key.
 *  @NotNull :não nulo.
 *  @Size(min, max) :anotação especificando o tamanho mínimo e máximo, não recebe título vazio.
 *  @Temporal(TemporalType.TIMESTAMP) :anotação que indica tempo e seu tipo, especificado no parâmetro ().
 *  private Date date = new java.sql.Date(System.currentTimeMillis()) :captura o tempo exato da informação
 *  que entrou na classe em qual está inserido.
 *  @author ingrid-kis
 */

@Entity
@Table(name = "postagem")
public class Postagem {
	
	//atributos da classe
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull //não nulo
	@Size(min=5, max=100)
	private String titulo;
	
	@NotNull
	@Size(min=10, max=500)
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new java.sql.Date(System.currentTimeMillis());
	
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
