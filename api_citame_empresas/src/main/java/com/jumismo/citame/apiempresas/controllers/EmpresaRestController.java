package com.jumismo.citame.apiempresas.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;
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
	@ApiResponses({
		@ApiResponse(code = 200, message = "Ok!!"),
		@ApiResponse(code = 404, message = "Not found")
	})
	@GetMapping(value = "/empresas", produces = MediaType.APPLICATION_JSON_VALUE)
	List<EmpresaDTO> getAllEmpresas() {
		return empresaService.getAllEmpresas();
	}

}
