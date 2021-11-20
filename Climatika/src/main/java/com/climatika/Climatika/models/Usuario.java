package com.climatika.Climatika.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/*
	@CPF
	private String cpf;
	*/
	@NotBlank
	@Size(max = 50)
	private String nomeCompleto;

	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Size(min = 5)
	private String senha;

	@NotBlank
	@Size(min = 11, max = 11)
	private String celular;
	
	@NotBlank
	@Size(max = 200)
	private String endereco;
	
	@NotBlank
	@Size(max = 30)
	private String cidade;
	
	@NotBlank
	@Size(min = 2, max = 2)
	private String estado;

	private String tipoUsuario = "comum";

	@OneToOne(mappedBy = "idUsuario", cascade =  CascadeType.REMOVE)
	@JsonIgnoreProperties({"idUsuario", "listaProduto"})
	private StatusVenda userStatusVendas;

	/*
	@OneToMany(mappedBy = "idUsuario", cascade =  CascadeType.REMOVE)
	@JsonIgnoreProperties({"idUsuario", "listaProduto"})
	private List<StatusVenda> userStatusVendas = new ArrayList<>();
	*/


	//Getters and Setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/*
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	*/
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	/*
	public List<StatusVenda> getUserStatusVendas() {
		return userStatusVendas;
	}

	public void setUserStatusVendas(List<StatusVenda> userStatusVendas) {
		this.userStatusVendas = userStatusVendas;
	}
	*/

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public StatusVenda getUserStatusVendas() {
		return userStatusVendas;
	}

	public void setUserStatusVendas(StatusVenda userStatusVendas) {
		this.userStatusVendas = userStatusVendas;
	}
}
