package com.accolite.internal.project.exemployeeportal.dtomodel.dto.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accolite.internal.project.exemployeeportal.dtomodel.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	 Optional<User> findByEmail(String email);
	 User findById(long id);
	 Boolean existsByEmail(String email);
}
