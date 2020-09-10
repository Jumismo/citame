package com.jumismo.citame.apiempresas.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jumismo.citame.apiempresas.dao.IEntrepriseDAO;
import com.jumismo.citame.apiempresas.dto.EntrepriseDTO;
import com.jumismo.citame.apiempresas.services.IEntrepriseService;

/**
 * The Class EmpresaServiceImpl.
 */
@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	/** The empresa DAO. */
	private final IEntrepriseDAO entrepriseDAO;

	/**
	 * Instantiates a new empresa service impl.
	 *
	 * @param empresaDAO the empresa DAO
	 */
	public EntrepriseServiceImpl(IEntrepriseDAO entrepriseDAO) {
		this.entrepriseDAO = entrepriseDAO;
	}

	/**
	 * Gets the all empresas.
	 *
	 * @return the all empresas
	 */
	@Override
	public List<EntrepriseDTO> getAllEntreprise() {
		return entrepriseDAO.findAll();
	}

	/**
	 * Gets the empresa.
	 *
	 * @param id the id
	 * @return the empresa
	 */
	@Override
	public Optional<EntrepriseDTO> getEntreprise(Long id) {
		return entrepriseDAO.findById(id);
	}

	/**
	 * Save.
	 *
	 * @param empresa the empresa
	 */
	@Override
	public void save(EntrepriseDTO empresa) {
		entrepriseDAO.save(empresa);
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the empresa DTO
	 */
	@Override
	public void delete(Long id) {
		entrepriseDAO.deleteById(id);
	}

}
