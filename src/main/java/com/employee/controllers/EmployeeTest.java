package com.employee.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeTest {

	@GetMapping
	public String getMapping() {
		return "Successfully Called From GATEWAY";
	}
}
