package com.climatika.Climatika.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.climatika.Climatika.Service.UsuarioService;
import com.climatika.Climatika.models.UsuarioLogin;
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

import com.climatika.Climatika.models.Usuario;
import com.climatika.Climatika.repository.UsuarioRepository;

@RestController
@RequestMapping("/climatika/usuario")
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repositoryUser;

	@Autowired
	private UsuarioService usuarioService;

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
		} else {
			return ResponseEntity.ok().body(cpfBuscado);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<UsuarioLogin> Autentication(@RequestBody Optional<UsuarioLogin> user) {
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario) {
		Optional<Usuario> user = usuarioService.CadastrarUsuario(usuario);
		if (user.isEmpty()){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}else {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(user.get());
		}
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> Autentication(@RequestBody Usuario user) {
		Optional<Usuario> userExist = repositoryUser.findById(user.getId());
		if (userExist.isPresent()){
			return usuarioService.atualizarUsuario(user).map(resp -> ResponseEntity.ok(resp))
					.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/*
	@PutMapping
	public ResponseEntity<Usuario> updateUser(@Valid @RequestBody Usuario upUser) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repositoryUser.save(upUser));
	}*/

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable(value = "id") Long idUser) {
		repositoryUser.deleteById(idUser);
	}
}
