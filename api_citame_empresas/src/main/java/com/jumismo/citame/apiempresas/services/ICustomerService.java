package com.jumismo.citame.apiempresas.services;

import com.jumismo.citame.apiempresas.dto.CustomerDTO;

/**
 * The Interface ICustomerService.
 */
public interface ICustomerService {

	/**
	 * Save.
	 *
	 * @param customer the customer
	 */
	public void save(CustomerDTO customer);

	public CustomerDTO update(Long id, CustomerDTO customer);

	public CustomerDTO delete(Long id);

}
