package com.climatika.Climatika.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.climatika.Climatika.models.Produto;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	List<Produto> findAllByNomeProdutoContainingIgnoreCase(String nomeProduto);
	List<Produto> findAllByCategoriaMaeContainingIgnoreCase(String categoriaMae);
	List<Produto> findAllByCategoriaFilhaContainingIgnoreCase(String categoriaFilha);
}
