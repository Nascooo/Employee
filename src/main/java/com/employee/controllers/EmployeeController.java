package com.employee.controllers;

import com.employee.dto.EmployeeDTO;
import com.employee.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("{id}")
	public EmployeeDTO getEmployee(@PathVariable("id") String employeeId) {
		return employeeService.getEmployee(employeeId);
	}

	@PostMapping
	public EmployeeDTO addNewEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
		return employeeService.addNewEmployee(employeeDTO);
	}

}
