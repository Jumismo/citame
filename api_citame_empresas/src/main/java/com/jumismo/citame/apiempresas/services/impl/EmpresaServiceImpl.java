package com.jumismo.citame.apiempresas.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jumismo.citame.apiempresas.dao.IEmpresaDAO;
import com.jumismo.citame.apiempresas.dto.EmpresaDTO;
import com.jumismo.citame.apiempresas.services.IEmpresaService;

@Service
public class EmpresaServiceImpl implements IEmpresaService {

	private final IEmpresaDAO empresaDAO;

	public EmpresaServiceImpl(IEmpresaDAO empresaDAO) {
		this.empresaDAO = empresaDAO;
	}

	@Override
	public List<EmpresaDTO> getAllEmpresas() {
		return empresaDAO.findAll();
	}

	@Override
	public Optional<EmpresaDTO> getEmpresa(Long id) {
		return empresaDAO.findById(id);
	}

}
