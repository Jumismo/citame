package com.jumismo.citame.apiempresas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jumismo.citame.apiempresas.dto.EmpresaDTO;
import com.jumismo.citame.apiempresas.services.IEmpresaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
class EmpresaRestController {

	private final IEmpresaService empresaService;

	EmpresaRestController(IEmpresaService empresaService) {
		this.empresaService = empresaService;
	}

	@ApiOperation(value = "Get all entreprise", response = List.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok!!"), @ApiResponse(code = 404, message = "Not found") })
	@GetMapping(value = "/empresas", produces = MediaType.APPLICATION_JSON_VALUE)
	List<EmpresaDTO> getAllEmpresas() {
		return empresaService.getAllEmpresas();
	}

	@ApiOperation(value = "Get entreprise by id", response = EmpresaDTO.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Entreprise found"),
			@ApiResponse(code = 404, message = "Not found") })
	@GetMapping(value = "/empresas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	Optional<EmpresaDTO> getEmpresa(@Validated @RequestParam Long id) {
		return empresaService.getEmpresa(id);
	}

}
