package com.climatika.Climatika.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.climatika.Climatika.models.Usuario;
import com.climatika.Climatika.repository.UsuarioRepository;

@RestController
@RequestMapping("/climatika/usuario")
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired UsuarioRepository repositoryUser;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAllUsers() {
		return ResponseEntity.ok(repositoryUser.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getByUserId(@PathVariable(value = "id") Long searchId) {
		return repositoryUser.findById(searchId).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<List<Usuario>> getByCpf(@PathVariable(value = "cpf") String searchCpf) {
		List<Usuario> cpfBuscado = repositoryUser.findAllByCpfContainingIgnoreCase(searchCpf);
		
		if (cpfBuscado.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.ok().body(cpfBuscado);
		}
	}
	
	@GetMapping("/admin/{admin}")
	public ResponseEntity<List<Usuario>> getAdmins(@PathVariable(value = "admin") String searchadmin) {
		List<Usuario> viewAdmin = repositoryUser.findAllByCpfContainingIgnoreCase(searchadmin);
		
		if (viewAdmin.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.ok().body(viewAdmin);
		}
	}
}
