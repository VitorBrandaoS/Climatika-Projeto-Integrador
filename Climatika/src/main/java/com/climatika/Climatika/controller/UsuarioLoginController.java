package com.climatika.Climatika.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.climatika.Climatika.Service.UsuarioService;
import com.climatika.Climatika.models.Usuario;
import com.climatika.Climatika.models.UsuarioLogin;

@RestController
@RequestMapping("/climatika/usuario")
@CrossOrigin("*")
public class UsuarioLoginController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/login")
	public ResponseEntity<UsuarioLogin> Autentication(@RequestBody Optional<UsuarioLogin> user) {
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Object> Post(@RequestBody Usuario usuario) {
		Optional<Object> user = usuarioService.CadastrarUsuario(usuario);
		if (user.isEmpty()){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}else {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(user.get());
		}
	}

}
