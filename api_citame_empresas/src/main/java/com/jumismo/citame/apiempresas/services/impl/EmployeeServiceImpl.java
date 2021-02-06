package com.jumismo.citame.apiempresas.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;
import com.jumismo.citame.apiempresas.dao.IEmployeeDAO;
import com.jumismo.citame.apiempresas.dao.IEntrepriseDAO;
import com.jumismo.citame.apiempresas.dto.EmployeeDTO;
import com.jumismo.citame.apiempresas.entity.EmployeeEntity;
import com.jumismo.citame.apiempresas.entity.EntrepriseEntity;
import com.jumismo.citame.apiempresas.services.IEmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {
	
	public final IEmployeeDAO employeeDAO;
	
	public final IEntrepriseDAO entrepriseDAO;
	
	public final ModelMapper mapper;

	@Override
	public List<EmployeeDTO> findAll() {
		return employeeDAO.findAll()
				.stream()
				.map(employee -> convertToDTO(employee))
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeDTO findById(Long id) {
		Optional<EmployeeEntity> employee = employeeDAO.findById(id);
		if(employee.isPresent()) {
			return convertToDTO(employee.get());
		}else {
			return new EmployeeDTO();
		}
	}

	@Override
	public void save(EmployeeDTO employeeDTO) {
		Optional<EntrepriseEntity> entreprise = entrepriseDAO.findById(employeeDTO.getEntrepriseId());
		if(entreprise.isPresent()) {
			EmployeeEntity employeeEntity = mapper.map(employeeDTO, EmployeeEntity.class);
			employeeEntity.setEntreprise(mapper.map(entreprise.get(), EntrepriseEntity.class));
			try {
				employeeEntity = employeeDAO.save(employeeEntity);
				if(employeeEntity.getId() != null) {
					entreprise.get().getListEmployer().add(employeeEntity);
					entrepriseDAO.save(entreprise.get());
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			//TODO: Add exception
		}
	}

	@Override
	public void delete(Long id) {
		employeeDAO.deleteById(id);
	}
	
	private EmployeeDTO convertToDTO(EmployeeEntity entity) {
		EmployeeDTO employeeDTO = mapper.map(entity, EmployeeDTO.class);
		employeeDTO.setEntrepriseId(entity.getEntreprise().getId());
		return employeeDTO;
	}

}
