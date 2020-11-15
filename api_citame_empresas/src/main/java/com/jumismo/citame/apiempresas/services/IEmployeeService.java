package com.jumismo.citame.apiempresas.services;

import java.util.List;

import com.jumismo.citame.apiempresas.dto.EmployeeDTO;

public interface IEmployeeService {

	List<EmployeeDTO> findAll();
	
	EmployeeDTO findById(Long id);
	
	void save(EmployeeDTO employee);
	
	void delete(Long id);
}
