package com.jumismo.citame.apiempresas.services.impl;

import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.jumismo.citame.apiempresas.dao.ICustomerDAO;
import com.jumismo.citame.apiempresas.dto.CustomerDTO;
import com.jumismo.citame.apiempresas.entity.CustomerEntity;
import com.jumismo.citame.apiempresas.services.ICustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class CustomerServiceImpl.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements ICustomerService {
	
	/** The customer DAO. */
	private final ICustomerDAO customerDAO;
	
	private final ModelMapper mapper;

	/**
	 * Save.
	 *
	 * @param customer the customer
	 */
	@Override
	public void save(CustomerDTO customer) {
		customer.setFechaAlta(new Date());
		customer.setFechaModificacion(new Date());
		customerDAO.save(convertToEntity(customer));
	}
	
	@Override
	public CustomerDTO update(Long id, CustomerDTO customer) {
		Optional<CustomerEntity> customerEntity = customerDAO.findById(id);
		if(customerEntity.isPresent()) {
			CustomerEntity entity = updateDataCustomer(customerEntity.get(), customer);
			return convertToDTO(customerDAO.save(entity));
		}
		else {
			log.error("No se ha encontrado un cliente con ese identificador");
			return null;
		}
	}
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the customer DTO
	 */
	@Override
	public CustomerDTO delete(Long id) {
		Optional<CustomerEntity> entity = customerDAO.findById(id);
		if(entity.isPresent()) {
			entity.get().setFechaBaja(new Date());
			customerDAO.save(entity.get());
			return convertToDTO(entity.get());
		}else {
			log.error("Customer not found");
			return null;
		}
	}
	
	/**
	 * Convert to entity.
	 *
	 * @param dto the dto
	 * @return the customer entity
	 */
	private CustomerEntity convertToEntity(CustomerDTO dto) {
		return mapper.map(dto, CustomerEntity.class);
	}
	
	private CustomerDTO convertToDTO(CustomerEntity entity) {
		return mapper.map(entity, CustomerDTO.class);
	}

	private CustomerEntity updateDataCustomer(CustomerEntity entity, CustomerDTO dto) {
		entity.setName(dto.getName());
		entity.setPhone(dto.getPhone());
		entity.setAddress(dto.getAddress());
		entity.setMail(dto.getMail());
		entity.setFechaModificacion(new Date());
		return entity;
	}

}