package com.climatika.Climatika.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity 
@Table(name = "tb_status_venda")
public class StatusVenda {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "Venda")
	private Long id;

	@OneToOne
	@JoinColumn(name = "Usuario")
	@JsonIgnoreProperties({"userStatusVendas", "senha", "endereco", "estado", "cidade"})
	private Usuario idUsuario;
	/*
	@ManyToOne
	@JoinColumn(name = "Usuario")
  	@JsonIgnoreProperties({"userStatusVendas", "senha", "endereco", "estado", "cidade"})
	private Usuario idUsuario;
 	*/
 	@ManyToMany
	@JsonIgnoreProperties({"userStatusVendas", "marca", "categoriaMae", "categoriaFilha", "listaStatusVenda"})
	private  List<Produto> listaProduto = new ArrayList<>();

	private String status = "aberto";


	//getters and setters
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Usuario getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public List<Produto> getListaProduto() {
		return listaProduto;
	}


	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}




}
