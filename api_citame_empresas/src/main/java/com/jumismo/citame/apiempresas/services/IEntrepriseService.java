package com.jumismo.citame.apiempresas.services;

import java.util.List;
import java.util.Optional;

import com.jumismo.citame.apiempresas.dto.EntrepriseDTO;

public interface IEntrepriseService {

	List<EntrepriseDTO> getAllEntreprise();

	Optional<EntrepriseDTO> getEntreprise(Long id);

	void save(EntrepriseDTO entreprise);

	void delete(Long id);

}
