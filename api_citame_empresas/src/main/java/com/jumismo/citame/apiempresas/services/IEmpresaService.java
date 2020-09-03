package com.jumismo.citame.apiempresas.services;

import java.util.List;
import java.util.Optional;

import com.jumismo.citame.apiempresas.dto.EmpresaDTO;

public interface IEmpresaService {

	List<EmpresaDTO> getAllEmpresas();

	Optional<EmpresaDTO> getEmpresa(Long id);

}
