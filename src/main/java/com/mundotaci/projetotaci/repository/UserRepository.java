package com.mundotaci.projetotaci.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mundotaci.projetotaci.entities.User;

public interface UserRepository  extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.userName =:userName AND u.password =:password")
	User findUser(@Param("userName") String userName, @Param("password") String password);
}
