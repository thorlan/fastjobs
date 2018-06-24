package br.com.fastjobs.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fastjobs.model.UserLogin;
import br.com.fastjobs.repository.UserLoginRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserLoginRepository userLoginRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("-----------------------");
		System.out.println(email);
		System.out.println("-----------------------");
		Optional<UserLogin> userLoginOpt =  userLoginRepository.findByEmail(email);
		UserLogin userLogin = userLoginOpt.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		return new UsuarioSistema(userLogin, getPermissoes(userLogin));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(UserLogin userLogin) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		userLogin.getPermissoes().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));
		return authorities;
	}

}