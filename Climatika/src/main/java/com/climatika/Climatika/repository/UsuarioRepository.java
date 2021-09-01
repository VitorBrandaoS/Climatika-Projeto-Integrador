package com.climatika.Climatika.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.climatika.Climatika.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	// containingIgnoreCase - provisorio
	public List<Usuario> findAllByCpfContainingIgnoreCase(String cpf);
	
	public List<Usuario> findAllByAdminContaingIgnoreCase(String admin);
}
