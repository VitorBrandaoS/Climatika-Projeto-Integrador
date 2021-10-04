package com.climatika.Climatika.Securanca;

import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.climatika.Climatika.models.Usuario;
import com.climatika.Climatika.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository userRepository;
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<Usuario> user = userRepository.findByEmail(email);

		user.orElseThrow(() -> new UsernameNotFoundException(email + " not found."));
		
		return user.map(UserDetailsImpl::new).get();
		/*if(user.isPresent()) {
			return new UserDetailsImpl(user.get());
		}else{
		    throw new UsernameNotFoundException(email+" não existe!");
		}*/


	}
}
