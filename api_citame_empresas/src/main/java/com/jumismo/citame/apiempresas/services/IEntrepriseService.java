package com.jumismo.citame.apiempresas.services;

import java.util.List;

import com.jumismo.citame.apiempresas.dto.EntrepriseDTO;

/**
 * The Interface IEntrepriseService.
 */
public interface IEntrepriseService {

	/**
	 * Gets the all entreprise.
	 *
	 * @return the all entreprise
	 */
	List<EntrepriseDTO> getAllEntreprise();

	/**
	 * Gets the entreprise.
	 *
	 * @param id the id
	 * @return the entreprise
	 */
	EntrepriseDTO getEntreprise(Long id);

	/**
	 * Save.
	 *
	 * @param entreprise the entreprise
	 */
	void save(EntrepriseDTO entreprise);

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	void delete(Long id);

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param empresa the empresa
	 * @return the entreprise DTO
	 */
	EntrepriseDTO update(Long id, EntrepriseDTO empresa);

}
