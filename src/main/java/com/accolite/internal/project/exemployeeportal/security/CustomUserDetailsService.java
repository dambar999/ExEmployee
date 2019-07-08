package com.accolite.internal.project.exemployeeportal.security;


import com.accolite.internal.project.exemployeeportal.exception.ResourceNotFoundException;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.ExEmployeeRepository;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.user.UserRepository;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ExEmployee;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    ExEmployeeRepository exEmployeeRepository;
    
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
    	
    	Optional<User> user = userRepository.findByEmail(email);
    	if (user.isPresent()) {   
           return UserPrincipal.create(user.get());
        } else  
        {
	    	ExEmployee exEmployee = exEmployeeRepository.findByEmail(email);
	    	if(exEmployee == null)
	    	{
	    		throw new UsernameNotFoundException("User not found with email : " + email);
	    	}     
	        return ExEmployeePrincipal.create(exEmployee);
        }
    }

//    @Transactional
//    public UserDetails loadUserById(Long id) {
//        User user = userRepository.findById(id).orElseThrow(
//            () -> new ResourceNotFoundException("User", "id", id)
//        );
//
//        return ExEmployeePrincipal.create(user);
//    }
}