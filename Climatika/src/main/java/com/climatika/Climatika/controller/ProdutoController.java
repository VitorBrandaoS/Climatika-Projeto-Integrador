package com.climatika.Climatika.controller;

import java.util.List;

import javax.persistence.Entity;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.climatika.Climatika.models.Produto;
import com.climatika.Climatika.repository.ProdutoRepository;

@Entity
@RequestMapping("/climatika/produto")
@CrossOrigin("*")
public class ProdutoController {

	@Autowired
	ProdutoRepository repositoryProduct;

	@GetMapping
	public ResponseEntity<List<Produto>> getAllProducts() {
		return ResponseEntity.ok(repositoryProduct.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable(value = "id") Long searchId) {
		return repositoryProduct.findById(searchId).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/buscaNome/{nome}")
	public ResponseEntity<List<Produto>> getAllByNome(@PathVariable(value = "nome") String searchNome) {
		List<Produto> produtosBuscados = repositoryProduct.findAllByNomeProdutoContainingIgnoreCase(searchNome);
		if (produtosBuscados.isEmpty()) {
			return ResponseEntity.notFound().build();
		} 
		else {
			return ResponseEntity.ok().body(produtosBuscados);
		}	
	}
	@PostMapping
	public ResponseEntity<Produto> newProduct(@Valid @RequestBody Produto addProduct) {
		return ResponseEntity.status(201).body(repositoryProduct.save(addProduct));
	}
	
	@PutMapping
	public ResponseEntity<Produto> updateProduct(@Valid @RequestBody Produto upProduct)	{ 
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(repositoryProduct.save(upProduct));
	}
	@DeleteMapping("/{id}")
	public void deleteProduct (@PathVariable(value = "id") Long idProduct) {
		repositoryProduct.deleteById(idProduct);
	}
 }	



