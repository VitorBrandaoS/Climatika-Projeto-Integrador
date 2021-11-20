package com.climatika.Climatika.Service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import com.climatika.Climatika.models.StatusVenda;
import com.climatika.Climatika.repository.StatusVendaRepository;
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
	@Autowired
	private StatusVendaRepository statusRepository;
	
	public Optional<Usuario> CadastrarUsuario(Usuario usuario) {
		Optional<Usuario> user = repository.findByEmail(usuario.getEmail());
		if (user.isPresent()){
			return Optional.empty();
		}else {
			//Encriptando Senha e cadastrando Usuario
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			String senhaEnconder = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEnconder);

			Optional.ofNullable(repository.save(usuario));
			//Iniciando Status Venda no Usuario cadastrado
			Optional<Usuario> user2 = repository.findByEmail(usuario.getEmail());
			StatusVenda statusVenda = new StatusVenda();
			statusVenda.setIdUsuario(user2.get());
			Optional.ofNullable(statusRepository.save(statusVenda));

			return Optional.ofNullable(usuario);
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
				user.get().setEmail(usuario.get().getEmail());
				user.get().setSenha(usuario.get().getSenha());
				user.get().setId(usuario.get().getId());
				user.get().setNomeCompleto(usuario.get().getNomeCompleto());
				user.get().setTipoUsuario(usuario.get().getTipoUsuario());
				user.get().setEndereco(usuario.get().getEndereco());
				user.get().setCelular(usuario.get().getCelular());
				user.get().setCidade(usuario.get().getCidade());
				//user.get().setCpf(usuario.get().getCpf());
				
				return user;
			}
		} return null;
	}

	public Optional<Usuario> atualizarUsuario(Usuario user) {
		return repository.findById(user.getId()).map(usuarioExistente -> {

			usuarioExistente.setEmail(usuarioExistente.getEmail());
			usuarioExistente.setSenha(usuarioExistente.getSenha());
			usuarioExistente.setNomeCompleto(user.getNomeCompleto());
			usuarioExistente.setTipoUsuario(user.getTipoUsuario());
			usuarioExistente.setCelular(user.getCelular());
			usuarioExistente.setEndereco(user.getEndereco());
			usuarioExistente.setEstado(user.getEstado());
			usuarioExistente.setCidade(user.getCidade());
			//usuarioExistente.setCpf(user.getCpf());

			return Optional.ofNullable(repository.save(usuarioExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
	public Optional<Usuario> atualizarSenha(Usuario user) {
		return repository.findById(user.getId()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(user.getSenha());

			usuarioExistente.setEmail(usuarioExistente.getEmail());
			usuarioExistente.setSenha(senhaCriptografada);
			usuarioExistente.setNomeCompleto(usuarioExistente.getNomeCompleto());
			usuarioExistente.setTipoUsuario(usuarioExistente.getTipoUsuario());
			usuarioExistente.setCelular(usuarioExistente.getCelular());
			usuarioExistente.setEndereco(usuarioExistente.getEndereco());
			usuarioExistente.setEstado(usuarioExistente.getEstado());
			usuarioExistente.setCidade(usuarioExistente.getCidade());
			//usuarioExistente.setCpf(user.getCpf());

			return Optional.ofNullable(repository.save(usuarioExistente));
		}).orElseGet(() -> {

			return Optional.empty();
		});
	}

	public Optional<Usuario> atualizarEmail(Usuario user) {
		return repository.findById(user.getId()).map(usuarioExistente -> {

			usuarioExistente.setEmail(user.getEmail());
			usuarioExistente.setSenha(usuarioExistente.getSenha());
			usuarioExistente.setNomeCompleto(usuarioExistente.getNomeCompleto());
			usuarioExistente.setTipoUsuario(usuarioExistente.getTipoUsuario());
			usuarioExistente.setCelular(usuarioExistente.getCelular());
			usuarioExistente.setEndereco(usuarioExistente.getEndereco());
			usuarioExistente.setEstado(usuarioExistente.getEstado());
			usuarioExistente.setCidade(usuarioExistente.getCidade());

			return Optional.ofNullable(repository.save(usuarioExistente));
		}).orElseGet(() -> {

			return Optional.empty();
		});
	}

}
