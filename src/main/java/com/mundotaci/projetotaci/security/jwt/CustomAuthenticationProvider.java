package com.mundotaci.projetotaci.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.mundotaci.projetotaci.entities.User;
import com.mundotaci.projetotaci.repository.UserRepository;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserRepository repository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String senha = authentication.getCredentials() == null ? "" : authentication.getCredentials().toString();
		
		User usuario = (User) repository.findUser(name, UtilCriptografia.md5(senha));
		if (usuario == null) {
			throw new AuthenticationCredentialsNotFoundException("Acesso negado. Login ou senha incorretos");
		}
		
		return new UsernamePasswordAuthenticationToken(usuario, senha, usuario.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
