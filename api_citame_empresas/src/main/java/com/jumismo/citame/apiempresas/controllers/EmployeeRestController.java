package com.jumismo.citame.apiempresas.controllers;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.jumismo.citame.apiempresas.dto.EmployeeDTO;
import com.jumismo.citame.apiempresas.services.IEmployeeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * The Class EmployeeRestController.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class EmployeeRestController {

	/** The employee service. */
	public final IEmployeeService employeeService;
	
	/**
	 * Gets the all employees.
	 *
	 * @return the all employees
	 */
	@ApiOperation(value = "Get all employees", response = List.class)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successfully", response = List.class),
			@ApiResponse(code = 404, message = "Employees not found"),
			@ApiResponse(code = 500, message = "Internal server error")
			})
	@GetMapping("/employees")
	public List<EmployeeDTO> getAllEmployees(){
		return employeeService.findAll();
	}
	
	/**
	 * Gets the employee by id.
	 *
	 * @param id the id
	 * @return the employee by id
	 */
	@ApiOperation(value= "Get employee by id", response = EmployeeDTO.class)
	@ApiResponses({
		@ApiResponse(code = 200, message = "Successfully", response = EmployeeDTO.class),
		@ApiResponse(code = 404, message = "Employee not found"),
		@ApiResponse(code = 500, message = "Internal server error")
	})
	@GetMapping("/employees/{id}")
	public EmployeeDTO getEmployeeById(@PathVariable Long id) {
		return employeeService.findById(id);
	}
	
	/**
	 * Save employee.
	 *
	 * @param employee the employee
	 */
	@ApiOperation(value = "Save a new employee", response = Void.class)
	@ApiResponses({
		@ApiResponse(code = 201, message = "Successfully"),
		@ApiResponse(code = 404, message = "Not found"),
		@ApiResponse(code = 500, message = "Internal server error")
	})
	@PostMapping("/employees")
	public void saveEmployee(@RequestBody EmployeeDTO employee) {
		employeeService.save(employee);
	}
	
	/**
	 * Update employee.
	 *
	 * @param id the id
	 * @param employee the employee
	 * @return the employee DTO
	 */
	@ApiOperation(value = "Update an employee", response = EmployeeDTO.class)
	@ApiResponses({
		@ApiResponse(code = 201, message = "Successfully", response = EmployeeDTO.class),
		@ApiResponse(code = 404, message = "Employee not found", response = NotFoundException.class),
		@ApiResponse(code = 500, message = "Internal server error", response = InternalServerError.class)
	})
	@PutMapping("/employees/{id}")
	public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employee) {
		return employeeService.update(id, employee);
	}
	
	/**
	 * Delete employee.
	 *
	 * @param id the id
	 */
	@ApiOperation(value = "Delete an employee", response = Void.class)
	@ApiResponses({
		@ApiResponse(code = 200, message = "Successfully"),
		@ApiResponse(code = 404, message = "Not found"),
		@ApiResponse(code = 500, message = "Internal server error")
	})
	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		employeeService.delete(id);
	}
}
