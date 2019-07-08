package com.accolite.internal.project.exemployeeportal.controller;

import com.accolite.internal.project.exemployeeportal.exception.ResourceNotFoundException;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.EmployeeRequest;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.EmployeeResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.ExEmployeeRepository;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.GenericUtilResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.IExEmployeeService;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.LoginRequest;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.user.UserRepository;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ExEmployee;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.User;
import com.accolite.internal.project.exemployeeportal.security.CurrentUser;
import com.accolite.internal.project.exemployeeportal.security.ExEmployeePrincipal;
import com.accolite.internal.project.exemployeeportal.security.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExEmployeeController {

    @Autowired
    private ExEmployeeRepository exEmployeeRepository;
    
    @Autowired 
    private UserRepository userRepository;

    @Autowired
    private IExEmployeeService exEmpServiceImpl;
    @GetMapping("/exemployee/me")
//    @PreAuthorize("hasRole('EXEMPLOYEE')")
    public ExEmployee getCurrentExEmployeeUser(@CurrentUser ExEmployeePrincipal userPrincipal) {
        return exEmployeeRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
    
    @GetMapping("/user/me")
//  @PreAuthorize("hasRole('EXEMPLOYEE')")
  public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
      return userRepository.findByEmail(userPrincipal.getEmail())
              .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
  }
    
    @GetMapping(value="/get/name", produces="text/plain")
	public String getEmpName(@RequestParam("eid") int eid) {
		return exEmpServiceImpl.getEmpName(eid);
	}
	
	@GetMapping(value="/details")
	public EmployeeResponse getEmployeeDetailsByEmail(@RequestParam("email") String email)  {
		return exEmpServiceImpl.getEmployeeDetailsByEmail(email);
	}
	
//	@GetMapping(value="/supp/details")
//	public int getSupportId(@RequestParam("email") String email) {
//		return exEmpServiceImpl.getSupportId(email);
//	}
	
	@PostMapping(value="/update/details")
	public GenericUtilResponse updateEmployeeDetails(@RequestBody EmployeeRequest employeeReq) {
		return exEmpServiceImpl.updateExEmployeeDetails(employeeReq);
	}
	
	@PostMapping(value="/update/password")
	public GenericUtilResponse updateEmployeePassword(@RequestBody LoginRequest login) {
		return exEmpServiceImpl.updateExEmployeePassword(login);
	}
}
