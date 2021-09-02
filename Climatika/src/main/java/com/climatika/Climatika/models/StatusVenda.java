package com.climatika.Climatika.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity 
@Table(name = "tb_status_venda")
public class StatusVenda {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
  	@JsonIgnoreProperties("userStatus")
	private Usuario idUsuario;
 	
 	@OneToMany(mappedBy = "statusVenda", cascade =  CascadeType.REMOVE)
	@JsonIgnoreProperties("statusVenda")
	private  List<Produto> codigoProduto; 
	
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

	public List<Produto> getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(List<Produto> codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
