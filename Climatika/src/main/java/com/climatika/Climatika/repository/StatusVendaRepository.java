package com.climatika.Climatika.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.climatika.Climatika.models.StatusVenda;

public interface StatusVendaRepository extends JpaRepository<StatusVenda, Long>{
	
	List<StatusVenda> findAllByIdUsuarioContainingIgnoreCase(String idUsuario);
	List<StatusVenda> findAllByStatusContainingIgnoreCase(String status);
	List<StatusVenda> findAllByCodigoProdutoContaining(Long codigoProduto);

}
