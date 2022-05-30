package com.employee.mappers;

import com.employee.dto.EmployeeDTO;
import com.employee.entities.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	EmployeeDTO entityToDTO(Employee employee);

	Employee dtoToEntity(EmployeeDTO employeeDTO);

}
