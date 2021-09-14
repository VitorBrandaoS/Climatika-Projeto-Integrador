package com.climatika.Climatika.Service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.climatika.Climatika.models.Usuario;
import com.climatika.Climatika.models.UsuarioLogin;
import com.climatika.Climatika.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Optional<Object> CadastrarUsuario(Usuario usuario) {
		Optional<?> user = repository.findByEmail(usuario.getEmail());
		if (user.isPresent()){
			return Optional.empty();
		}else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			String senhaEnconder = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEnconder);

			return Optional.ofNullable(repository.save(usuario));
		}
	}
	
	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user){
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByEmail(user.get().getEmail());
		
		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				
				String auth = user.get().getEmail() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				
				user.get().setToken(authHeader);
				user.get().setNomeCompleto(usuario.get().getNomeCompleto());
				user.get().setSenha(usuario.get().getSenha());
				
				return user;
			}
		} return null;
	}
}
