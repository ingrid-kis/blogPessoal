package org.generation.blogPessoal.model;

import java.time.LocalDate;

import javax.persistence.Entity; //indica que essa classe será uma classe do JPA
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table; //indica que essa anotação, dentro da Entidade, será uma tabela
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Anotações @ definem certos tipos de comportamentos para as classes.
 * 
 * @Table(name = "postagem") :indica que nome da tabela será postagem.
 * @Id :nome da anotação
 * @GeneratedValue(strategy = GenerationType.IDENTITY) :como ela se comporta,
 *                          nesse caso autoincrement. No DB, o @Id
 *                          e @GeneratedValue se transformam em Primary Key.
 * @NotNull :não nulo.
 * @Size(min, max) :anotação especificando o tamanho mínimo e máximo, não recebe
 *            título vazio.
 * @Temporal(TemporalType.TIMESTAMP) :anotação que indica tempo e seu tipo,
 *                                   especificado no parâmetro (). private Date
 *                                   date = new
 *                                   java.sql.Date(System.currentTimeMillis())
 *                                   :captura o tempo exato da informação que
 *                                   entrou na classe em qual está inserido.
 * @JsonIgnoreProperties() :passar como parâmetro a propriedade que será
 *                         ignorada no método abaixo (no caso, Tema).
 * @author ingrid-kis
 */

@Entity
@Table(name = "postagem")
public class Postagem {

	// atributos da classe

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O atributo título é obrigatório!")
	@NotNull // não nulo
	@Size(min = 5, max = 100, message = "O atributo título deve conter no mínimo 5 e no máximo 100 caracteres")
	private String titulo;

	@NotBlank(message = "O atributo texto é obrigatório!")
	@NotNull
	@Size(min = 10, max = 500, message = "O atributo título deve conter no mínimo 5 e no máximo 100 caracteres")
	private String texto;

	@UpdateTimestamp
	private LocalDate data;

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
}
