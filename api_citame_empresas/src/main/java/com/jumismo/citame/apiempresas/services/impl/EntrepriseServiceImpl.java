package com.jumismo.citame.apiempresas.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.jumismo.citame.apiempresas.dao.IEntrepriseDAO;
import com.jumismo.citame.apiempresas.dto.EntrepriseDTO;
import com.jumismo.citame.apiempresas.entity.EntrepriseEntity;
import com.jumismo.citame.apiempresas.services.IEntrepriseService;

import lombok.RequiredArgsConstructor;

/**
 * The Class EmpresaServiceImpl.
 */
@Service
@RequiredArgsConstructor
public class EntrepriseServiceImpl implements IEntrepriseService {

	/** The empresa DAO. */
	private final IEntrepriseDAO entrepriseDAO;
	
	private final ModelMapper entrepriseMapper;

	/**
	 * Gets the all empresas.
	 *
	 * @return the all empresas
	 */
	@Override
	public List<EntrepriseDTO> getAllEntreprise() {
		return entrepriseDAO.findAll()
				.stream()
				.map(entreprise -> entrepriseMapper.map(entreprise, EntrepriseDTO.class))
				.collect(Collectors.toList());
	}

	/**
	 * Gets the empresa.
	 *
	 * @param id the id
	 * @return the empresa
	 */
	@Override
	public EntrepriseDTO getEntreprise(Long id) {
		return entrepriseMapper.map(entrepriseDAO.findById(id), EntrepriseDTO.class);
	}

	/**
	 * Save.
	 *
	 * @param empresa the empresa
	 */
	@Override
	public void save(EntrepriseDTO empresa) {
		entrepriseDAO.save(entrepriseMapper.map(empresa, EntrepriseEntity.class));
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
