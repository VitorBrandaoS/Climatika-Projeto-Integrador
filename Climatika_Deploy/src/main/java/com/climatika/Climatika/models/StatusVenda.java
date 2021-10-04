package com.climatika.Climatika.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity 
@Table(name = "tb_status_venda")
public class StatusVenda {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "Venda")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "Usuario")
  	@JsonIgnoreProperties({"userStatusVendas", "senha", "endereco", "estado", "cidade"})
	private Usuario idUsuario;
 	
 	@ManyToMany
	@JsonIgnoreProperties({"userStatusVendas", "marca", "categoriaMae", "categoriaFilha", "listaStatusVenda"})
	private  List<Produto> listaProduto = new ArrayList<>();
 
	
	@NotBlank
	private String status;


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
