package br.com.fastjobs.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.fastjobs.model.UserLogin;

public class UsuarioSistema extends User {
	
	private static final long serialVersionUID = 1L;
	
	private UserLogin userLogin;
	
	public UsuarioSistema(UserLogin userLogin, Collection<? extends GrantedAuthority> authorities) {
		super(userLogin.getEmail(), userLogin.getSenha(), authorities);
		this.userLogin = userLogin;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}
}
