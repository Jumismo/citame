package com.jumismo.citame.apiempresas.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumismo.citame.apiempresas.dao.IEmployeeDAO;
import com.jumismo.citame.apiempresas.dto.EmployeeDTO;
import com.jumismo.citame.apiempresas.dto.EntrepriseDTO;
import com.jumismo.citame.apiempresas.entity.EmployeeEntity;
import com.jumismo.citame.apiempresas.services.IEmployeeService;
import com.jumismo.citame.apiempresas.services.IEntrepriseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {
	
	public final IEmployeeDAO employeeDAO;
	
	public final IEntrepriseService entrepriseDAO;
	
	public final ObjectMapper mapper;

	@Override
	public List<EmployeeDTO> findAll() {
		return employeeDAO.findAll()
				.stream()
				.map(employee -> mapper.convertValue(employee, EmployeeDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeDTO findById(Long id) {
		Optional<EmployeeEntity> employee = employeeDAO.findById(id);
		if(employee.isPresent()) {
			return mapper.convertValue(employee.get(), EmployeeDTO.class);
		}else {
			return new EmployeeDTO();
		}
	}

	@Override
	public void save(EmployeeDTO employee) {
		EntrepriseDTO entreprise = entrepriseDAO.getEntreprise(employee.getEntreprise().getId());
		if(entreprise.getId() != null) {
			employee.setEntreprise(entreprise);
			employeeDAO.save(mapper.convertValue(employee, EmployeeEntity.class));
		}else {
			//TODO: Add exception
		}
	}

	@Override
	public void delete(Long id) {
		employeeDAO.deleteById(id);
	}

}
