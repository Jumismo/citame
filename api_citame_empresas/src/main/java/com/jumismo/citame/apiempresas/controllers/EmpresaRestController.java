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
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
class EntrepriseRestController {

	private final IEntrepriseService entrepriseService;

	EntrepriseRestController(IEntrepriseService entrepriseService) {
		this.entrepriseService = entrepriseService;
	}

	@ApiOperation(value = "Get all entreprise", response = List.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok!!"), @ApiResponse(code = 404, message = "Not found") })
	@GetMapping(value = "/empresas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EntrepriseDTO> getAllEmpresas() {
		log.debug("Call getAllEmpresas method");
		return entrepriseService.getAllEntreprise();
	}

	@ApiOperation(value = "Get entreprise by id", response = EntrepriseEntity.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Entreprise found"),
			@ApiResponse(code = 404, message = "Not found") })
	@GetMapping(value = "/empresas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EntrepriseDTO getEmpresa(@Validated @PathVariable Long id) {
		log.debug("Call getEmpresa method with id %d", id);
		return entrepriseService.getEntreprise(id);
	}

	@ApiOperation(value = "Save entreprise", response = Void.class)
	@ApiResponses({ @ApiResponse(code = 201, message = "Entreprise created"),
			@ApiResponse(code = 403, message = "Permission denied"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping("/empresas")
	public void save(@RequestBody EntrepriseDTO empresa) {
		entrepriseService.save(empresa);
	}

	@ApiOperation(value = "Delete entreprise", response = Void.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Entreprise created"),
			@ApiResponse(code = 403, message = "Permission denied"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/empresas/{id}")
	public void delete(@PathVariable(value = "id") Long idEmpresa) {
		entrepriseService.delete(idEmpresa);
	}

}
