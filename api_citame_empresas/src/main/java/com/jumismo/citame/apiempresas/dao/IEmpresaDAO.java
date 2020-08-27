package com.jumismo.citame.apiempresas.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumismo.citame.apiempresas.dto.EmpresaDTO;

public interface IEmpresaDAO extends JpaRepository<EmpresaDTO, Long> {
}
