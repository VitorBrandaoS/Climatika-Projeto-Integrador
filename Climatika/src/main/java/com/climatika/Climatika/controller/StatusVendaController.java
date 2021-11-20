package com.climatika.Climatika.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.climatika.Climatika.models.Produto;
import com.climatika.Climatika.models.StatusVenda;
import com.climatika.Climatika.repository.ProdutoRepository;
import com.climatika.Climatika.repository.StatusVendaRepository;

@RestController
@RequestMapping("/climatika/status")
@CrossOrigin("*")
public class StatusVendaController {

	@Autowired
	private StatusVendaRepository repositoryStatus;
	@Autowired
	private ProdutoRepository repositoryProduct;
	
	@GetMapping
	public ResponseEntity<List<StatusVenda>> getAllProductStatus() {
		return ResponseEntity.ok(repositoryStatus.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<StatusVenda> getById(@PathVariable(value = "id") Long searchId2) {
		return repositoryStatus.findById(searchId2).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/busca/codigo/{codigoProduto}")
	public ResponseEntity<List<StatusVenda>> getAllByCodigo(@PathVariable(value = "codigoProduto") Long searchCod) {
		List<StatusVenda> codigoBuscado = repositoryStatus.findAllByListaProduto(searchCod);

		if (codigoBuscado.isEmpty())
			return ResponseEntity.status(204).build();
		else
			return ResponseEntity.status(200).body(codigoBuscado);
	}

	@GetMapping("/busca/usuario/{idUsuario}")
	public ResponseEntity<List<StatusVenda>> getAllByIdUsuario(@PathVariable(value = "idUsuario") Long searchUser) {
		List<StatusVenda> usuarioBuscado = repositoryStatus.findAllByIdUsuario(searchUser);

		if (usuarioBuscado.isEmpty())
			return ResponseEntity.status(204).build();
		else
			return ResponseEntity.status(200).body(usuarioBuscado);
	}

	@GetMapping("/busca/status/{status}")
	public ResponseEntity<List<StatusVenda>> getAllByStatus(@PathVariable(value = "status") String searchStatus) {
		List<StatusVenda> statusBuscado = repositoryStatus.findAllByStatusContainingIgnoreCase(searchStatus);

		if (statusBuscado.isEmpty())
			return ResponseEntity.status(204).build();
		else
			return ResponseEntity.status(200).build();
	}
	
	@PostMapping("/add")
	public ResponseEntity<StatusVenda> newStatus(@Valid @RequestBody StatusVenda addStatus) {
			return ResponseEntity.status(201).body(repositoryStatus.save(addStatus));
	}
	
	//Adiciona um produto na lista de compras do usuario referente aquele pedido que está em andamento.

	@PostMapping("/add/produto/{id_produto}/{id_status_venda}")
	public ResponseEntity<?> newProduct(@PathVariable(value = "id_produto") Long idProduto,
			@PathVariable(value = "id_status_venda") Long idStatusVenda) {
		Optional<Produto> optionalProduto = repositoryProduct.findById(idProduto);
		Optional<StatusVenda> optionalStatusVenda = repositoryStatus.findById(idStatusVenda);
		
		if(optionalProduto.isPresent() && optionalStatusVenda.isPresent()) {
			if (optionalStatusVenda.get().getListaProduto().contains(optionalProduto.get())) {
				optionalProduto.get().setQuant(optionalProduto.get().getQuant() + 1L);
			} else {
				optionalProduto.get().setQuant(optionalProduto.get().getQuant() + 1L);
				optionalStatusVenda.get().getListaProduto().add(optionalProduto.get());
			}

			return ResponseEntity.status(201).body(repositoryStatus.save(optionalStatusVenda.get()));
			
		} else {
			return ResponseEntity.status(400).build();
		}
	}

	@PostMapping("/remove/produto/{id_produto}/{id_status_venda}")
	public ResponseEntity<?> removeProduct(@PathVariable(value = "id_produto") Long idProduto,
										@PathVariable(value = "id_status_venda") Long idStatusVenda) {
		Optional<Produto> optionalProduto = repositoryProduct.findById(idProduto);
		Optional<StatusVenda> optionalStatusVenda = repositoryStatus.findById(idStatusVenda);

		if(optionalProduto.isPresent() && optionalStatusVenda.isPresent()) {
			optionalProduto.get().setQuant(0L);
			optionalStatusVenda.get().getListaProduto().remove(optionalProduto.get());
			return ResponseEntity.status(201).body(repositoryStatus.save(optionalStatusVenda.get()));
		} else {
			return ResponseEntity.status(400).build();
		}
	}

	@PostMapping("/limpa-carrinho/{idVenda}")
	public ResponseEntity<StatusVenda> limpaCarrinho(@PathVariable(value = "idVenda") Long idVenda){
		Optional<StatusVenda> statusVenda = repositoryStatus.findById(idVenda);
		if(statusVenda.isPresent()) {

			statusVenda.get().getListaProduto().clear();
			return ResponseEntity.ok().body(repositoryStatus.save(statusVenda.get()));

		}else {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/calculo-total/{idVenda}")
	public ResponseEntity<?> calculoTotal(@PathVariable(value = "idVenda") Long id){
		Optional<StatusVenda> statusVenda = repositoryStatus.findById(id);
		if(statusVenda.isPresent()) {
			List<Produto> lista = statusVenda.get().getListaProduto();
			float total = 0;
			for(Produto a: lista) {
				total += a.getPreco();
			}
			return ResponseEntity.ok().body(total);

		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping
	public ResponseEntity<StatusVenda> updateStatus(@Valid @RequestBody StatusVenda upStatus) {
		return ResponseEntity.status(200).body(repositoryStatus.save(upStatus));
	}

	@DeleteMapping("/{id}")
	public void deleteStatus(@PathVariable(value = "id") Long idStatus) {
		repositoryStatus.deleteById(idStatus);
	}

}
