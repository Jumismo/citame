package com.jumismo.citame.apiempresas.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumismo.citame.apiempresas.dao.IEntrepriseDAO;
import com.jumismo.citame.apiempresas.dto.EntrepriseDTO;
import com.jumismo.citame.apiempresas.entity.EntrepriseEntity;
import com.jumismo.citame.apiempresas.services.IEntrepriseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class EmpresaServiceImpl.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
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
		List<EntrepriseEntity> lista = entrepriseDAO.findAll();
		return lista
				.stream()
				.map(this::convertToDTO)
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
		Optional<EntrepriseEntity> entity = entrepriseDAO.findById(id);
		if(entity.isPresent()) {
			entity.get().setFechaBaja(new Date());
			entrepriseDAO.save(entity.get());
		}
		else {
			log.error("La empresa " + id + " no está registrada.");
		}
	}
	
	private EntrepriseDTO convertToDTO(EntrepriseEntity entity) {
		return entrepriseMapper.map(entity, EntrepriseDTO.class);
		
	}

}
