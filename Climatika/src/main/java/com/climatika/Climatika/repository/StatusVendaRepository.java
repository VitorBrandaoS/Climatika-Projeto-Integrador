package com.climatika.Climatika.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.climatika.Climatika.models.StatusVenda;
@Repository
public interface StatusVendaRepository extends JpaRepository<StatusVenda, Long>{
	
	List<StatusVenda> findAllByIdUsuario(Long idUsuario);
	List<StatusVenda> findAllByStatusContainingIgnoreCase(String status);
	List<StatusVenda> findAllByListaProduto(Long codigoProduto);

}
