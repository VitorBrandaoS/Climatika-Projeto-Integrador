package com.climatika.Climatika.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_produto")
public class Produto {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank
	@Size(min = 3, max = 50)
	private String nome;
	
	@NotBlank
	@Size(max = 30)
	private String marca;
	
	@NotBlank
	private float preco;
	
	@NotBlank
	@Size(max = 50)
	private String categoriaMae;
	
	@NotBlank
	@Size(max = 50)
	private String categoriaFilha;

	private Long user;
	
	//gets e sets
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public String getCategoriaMae() {
		return categoriaMae;
	}

	public void setCategoriaMae(String categoriaMae) {
		this.categoriaMae = categoriaMae;
	}

	public String getCategoriaFilha() {
		return categoriaFilha;
	}

	public void setCategoriaFilha(String categoriaFilha) {
		this.categoriaFilha = categoriaFilha;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}
	
	
}
