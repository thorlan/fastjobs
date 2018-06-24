package br.com.fastjobs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fastjobs.model.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long>{

	public Optional<UserLogin> findByEmail(String email);
}
