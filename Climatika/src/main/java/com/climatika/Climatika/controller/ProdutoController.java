package com.climatika.Climatika.controller;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.climatika.Climatika.models.Produto;
import com.climatika.Climatika.repository.ProdutoRepository;

@Entity
@RequestMapping("/climatika/produto")
@CrossOrigin("*")
public class ProdutoController {
	
	@Autowired ProdutoRepository repositoryProduct;

	@GetMapping
	public ResponseEntity<List<Produto>> getAllProducts(){
		return ResponseEntity.ok(repositoryProduct.findAll());
	}
	
	
	
}
