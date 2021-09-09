package com.climatika.Climatika.models;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name = "tb_produto")
public class Produto {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank
	@Size(min = 3, max = 50)
	private String nomeProduto;
	
	@NotBlank
	@Size(max = 30)
	private String marca;
	
	@NotNull
	private float preco;
	
	@NotBlank
	@Size(max = 50)
	private String categoriaMae;
	
	@NotBlank
	@Size(max = 50)
	private String categoriaFilha;
	
	@ManyToMany
	@JsonIgnoreProperties ({"listaStatusVenda"})
	private  List<StatusVenda> listaStatusVenda = new ArrayList<>();
	
	//gets e sets

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
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

	public List<StatusVenda> getListaStatusVenda() {
		return listaStatusVenda;
	}

	public void setListaStatusVenda(List<StatusVenda> listaStatusVenda) {
		this.listaStatusVenda = listaStatusVenda;
	}
	
	
	
}
