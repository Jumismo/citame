package com.jumismo.citame.apiempresas.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jumismo.citame.apiempresas.dto.CustomerDTO;
import com.jumismo.citame.apiempresas.services.ICustomerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * The Class CustomerRestController.
 */
@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class CustomerRestController {
	
	/** The customer service. */
	private final ICustomerService customerService;
	
	/**
	 * Save.
	 *
	 * @param customer the customer
	 */
	@ApiOperation(value = "Alta de cliente")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Created"),
		@ApiResponse(code = 400, message = "Error in validation"),
		@ApiResponse(code = 500, message = "Error in server")
	})
	@PostMapping(value = "/customers")
	public void save(@RequestBody CustomerDTO customer) {
		customerService.save(customer);
	}
	
	@ApiOperation(value = "Actualiza un cliente")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Updated"),
		@ApiResponse(code = 400, message = "Error in validation"),
		@ApiResponse(code = 500, message = "Error in server")
	})
	@PutMapping(value = "/customers/{id}")
	public CustomerDTO update(@PathVariable(required = true) Long id, @RequestBody CustomerDTO customer) {
		return customerService.update(id, customer);
	}
	
	@ApiOperation(value = "Borra un cliente")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Deleted"),
		@ApiResponse(code = 400, message = "Error in validation"),
		@ApiResponse(code = 500, message = "Error in server")
	})
	@DeleteMapping(value = "/customers/{id}")
	public CustomerDTO delete(@PathVariable(required = true) Long id) {
		return customerService.delete(id);
	}

}
