package com.jumismo.citame.apiempresas.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jumismo.citame.apiempresas.dto.EntrepriseDTO;
import com.jumismo.citame.apiempresas.entity.EntrepriseEntity;
import com.jumismo.citame.apiempresas.services.IEntrepriseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class EntrepriseRestController.
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class EntrepriseRestController {

	/** The entreprise service. */
	private final IEntrepriseService entrepriseService;


	/**
	 * Gets the all empresas.
	 *
	 * @return the all empresas
	 */
	@ApiOperation(value = "Get all entreprise", response = List.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok!!"), @ApiResponse(code = 404, message = "Not found") })
	@GetMapping(value = "/entreprises", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EntrepriseDTO> getAllEmpresas() {
		log.debug("Call getAllEmpresas method");
		return entrepriseService.getAllEntreprise();
	}

	/**
	 * Gets the empresa.
	 *
	 * @param id the id
	 * @return the empresa
	 */
	@ApiOperation(value = "Get entreprise by id", response = EntrepriseEntity.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Entreprise found"),
			@ApiResponse(code = 404, message = "Not found") })
	@GetMapping(value = "/entreprises/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EntrepriseDTO getEmpresa(@Validated @PathVariable Long id) {
		log.debug("Call getEmpresa method with id %d", id);
		return entrepriseService.getEntreprise(id);
	}

	/**
	 * Save.
	 *
	 * @param empresa the empresa
	 */
	@ApiOperation(value = "Save a new entreprise", response = Void.class)
	@ApiResponses({ @ApiResponse(code = 201, message = "Entreprise created"),
			@ApiResponse(code = 403, message = "Permission denied"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping("/entreprises")
	public void save(@RequestBody EntrepriseDTO empresa) {
		entrepriseService.save(empresa);
	}

	/**
	 * Delete.
	 *
	 * @param idEmpresa the id empresa
	 */
	@ApiOperation(value = "Delete entreprise", response = Void.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Entreprise created"),
			@ApiResponse(code = 403, message = "Permission denied"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/entreprises/{id}")
	public void delete(@PathVariable(value = "id") Long idEmpresa) {
		entrepriseService.delete(idEmpresa);
	}

}
