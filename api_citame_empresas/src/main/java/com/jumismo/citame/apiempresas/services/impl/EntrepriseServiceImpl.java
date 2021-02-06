package com.jumismo.citame.apiempresas.services.impl;

import java.util.List;
import java.util.Optional;
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
	
	/** The entreprise mapper. */
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
				.map(entreprise -> convertToDTO(entreprise))
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
		Optional<EntrepriseEntity> entrepriseEntity = entrepriseDAO.findById(id);
		if(entrepriseEntity.isPresent()) {
			return convertToDTO(entrepriseEntity.get());
		}
		else {
			return new EntrepriseDTO();
		}
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
	
	private EntrepriseDTO convertToDTO(EntrepriseEntity entity) {
		EntrepriseDTO entrepriseDTO = entrepriseMapper.map(entity, EntrepriseDTO.class);
		return entrepriseDTO;
	}

}
