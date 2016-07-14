package org.example.repository;

import java.util.List;

import org.example.domain.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	
	User findByUsername(String username);
	
	List<User> findAll(Specification<User> spec);

}
