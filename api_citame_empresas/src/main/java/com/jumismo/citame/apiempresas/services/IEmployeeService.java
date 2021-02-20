package com.jumismo.citame.apiempresas.services;

import java.util.List;

import com.jumismo.citame.apiempresas.dto.EmployeeDTO;

/**
 * The Interface IEmployeeService.
 */
public interface IEmployeeService {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<EmployeeDTO> findAll();
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the employee DTO
	 */
	EmployeeDTO findById(Long id);
	
	/**
	 * Save.
	 *
	 * @param employee the employee
	 */
	void save(EmployeeDTO employee);
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	void delete(Long id);
}
