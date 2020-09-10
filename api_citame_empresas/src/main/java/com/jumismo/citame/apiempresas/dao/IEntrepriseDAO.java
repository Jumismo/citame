package com.jumismo.citame.apiempresas.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumismo.citame.apiempresas.dto.EntrepriseDTO;

public interface IEntrepriseDAO extends JpaRepository<EntrepriseDTO, Long> {
}
