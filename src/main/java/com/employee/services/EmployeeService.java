package com.employee.services;

import com.employee.dto.EmployeeDTO;
import com.employee.dto.LeavesDTO;
import com.employee.entities.Employee;
import com.employee.mappers.EmployeeMapper;
import com.employee.reposiotories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.apache.commons.lang3.Validate.notNull;

@Service
public class EmployeeService {

	private EmployeeRepository employeeRepository;

	private EmployeeMapper employeeMapper;

	private RestTemplate restTemplate;

	public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, RestTemplate restTemplate) {
		this.employeeRepository = employeeRepository;
		this.employeeMapper = employeeMapper;
		this.restTemplate = restTemplate;
	}

	public EmployeeDTO getEmployee(String employeeId) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
		Employee employee = optionalEmployee.orElseThrow(() -> new NoSuchElementException("Employee Not Exists"));
		return employeeMapper.entityToDTO(employee);
	}

	public EmployeeDTO addNewEmployee(EmployeeDTO employeeDTO) {
		notNull(employeeDTO, "Should not Be NUll");
		Employee employee = employeeRepository.save(employeeMapper.dtoToEntity(employeeDTO));
		LeavesDTO requestToSend = new LeavesDTO();
		requestToSend.setEmployeeId(employee.getId());
		restTemplate.postForEntity("http://leaves/api/v1/leaves/employee", requestToSend, LeavesDTO.class);
		return employeeMapper.entityToDTO(employee);
	}
}
