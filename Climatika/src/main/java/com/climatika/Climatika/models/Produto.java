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
	private String categoria_mae;
	
	@NotBlank
	@Size(max = 50)
	private String categoria_filha;

	
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

	public String getCategoria_mae() {
		return categoria_mae;
	}

	public void setCategoria_mae(String categoria_mae) {
		this.categoria_mae = categoria_mae;
	}

	public String getCategoria_filha() {
		return categoria_filha;
	}

	public void setCategoria_filha(String categoria_filha) {
		this.categoria_filha = categoria_filha;
	}
}
