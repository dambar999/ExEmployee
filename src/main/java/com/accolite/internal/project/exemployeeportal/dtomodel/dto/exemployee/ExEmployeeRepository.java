package com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ExEmployee;

@Repository
public interface ExEmployeeRepository  extends JpaRepository<ExEmployee, Long> {

	 ExEmployee findByEmail(String email);
	 ExEmployee findById(long id);
	 Boolean existsByEmail(String email);
}
