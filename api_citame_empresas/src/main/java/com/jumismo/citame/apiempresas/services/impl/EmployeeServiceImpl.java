package com.jumismo.citame.apiempresas.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumismo.citame.apiempresas.dao.IEmployeeDAO;
import com.jumismo.citame.apiempresas.dao.IEntrepriseDAO;
import com.jumismo.citame.apiempresas.dto.EmployeeDTO;
import com.jumismo.citame.apiempresas.entity.EmployeeEntity;
import com.jumismo.citame.apiempresas.entity.EntrepriseEntity;
import com.jumismo.citame.apiempresas.services.IEmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class EmployeeServiceImpl.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class EmployeeServiceImpl implements IEmployeeService {
	
	/** The employee DAO. */
	private final IEmployeeDAO employeeDAO;
	
	/** The entreprise DAO. */
	private final IEntrepriseDAO entrepriseDAO;
	
	/** The mapper. */
	private final ModelMapper mapper;
	
	private static final String EMPLOYEE_NOT_FOUND = "El empleado no ha sido encontrado";

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@Override
	public List<EmployeeDTO> findAll() {
		return employeeDAO.findAll()
				.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the employee DTO
	 */
	@Override
	public EmployeeDTO findById(Long id) {
		Optional<EmployeeEntity> employee = employeeDAO.findById(id);
		if(employee.isPresent()) {
			return convertToDTO(employee.get());
		}else {
			log.info(EMPLOYEE_NOT_FOUND);
			return new EmployeeDTO();
		}
	}

	/**
	 * Save.
	 *
	 * @param employeeDTO the employee DTO
	 */
	@Override
	public void save(EmployeeDTO employeeDTO) {
		Optional<EntrepriseEntity> entrepriseEntity = entrepriseDAO.findById(employeeDTO.getEntrepriseId());
		if(entrepriseEntity.isPresent()) {
			EmployeeEntity employeeEntity = mapper.map(employeeDTO, EmployeeEntity.class);
			employeeEntity.setFechaAlta(new Date());
			employeeEntity.setFechaModificacion(new Date());
			employeeEntity.setFechaBaja(null);
			employeeEntity.setEntreprise(mapper.map(entrepriseEntity.get(), EntrepriseEntity.class));
			try {
				employeeDAO.save(employeeEntity);
			}catch (Exception e) {
				log.error("Error al registrar al empleado: " + employeeDTO.getName());
			}
		}else {
			log.error("Error al obtener la empresa con ID: " + employeeDTO.getEntrepriseId());
		}
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	@Override
	public void delete(Long id) {
		Optional<EmployeeEntity> entity = employeeDAO.findById(id);
		if(entity.isPresent()) {
			entity.get().setFechaBaja(new Date());
			employeeDAO.save(entity.get());
		}else {
			log.error(EMPLOYEE_NOT_FOUND);
		}
	}
	

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param employee the employee
	 * @return the employee DTO
	 */
	@Override
	public EmployeeDTO update(Long id, EmployeeDTO employee) {
		Optional<EmployeeEntity> entity = employeeDAO.findById(id);
		if(entity.isPresent()) {
			return convertToDTO(employeeDAO.save(updateDataEntity(entity.get(), employee)));
		}else {
			log.error(EMPLOYEE_NOT_FOUND);
			return null;
		}
	}
	
	/**
	 * Convert to DTO.
	 *
	 * @param entity the entity
	 * @return the employee DTO
	 */
	private EmployeeDTO convertToDTO(EmployeeEntity entity) {
		EmployeeDTO employeeDTO = mapper.map(entity, EmployeeDTO.class);
		employeeDTO.setEntrepriseId(entity.getEntreprise().getId());
		return employeeDTO;
	}
	
	/**
	 * Convert to entity.
	 *
	 * @param entity the entity
	 * @param employeeDTO the employee DTO
	 * @return the employee entity
	 */
	private EmployeeEntity updateDataEntity(EmployeeEntity entity, EmployeeDTO employeeDTO) {
		entity.setName(employeeDTO.getName());
		entity.setOwner(employeeDTO.isOwner());
		entity.setPhone(employeeDTO.getPhone());
		entity.setFechaModificacion(new Date());
		return entity;
	}


}
