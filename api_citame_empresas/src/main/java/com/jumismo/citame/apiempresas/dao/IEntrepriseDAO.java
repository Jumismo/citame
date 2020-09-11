package com.jumismo.citame.apiempresas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jumismo.citame.apiempresas.dto.EntrepriseDTO;

@Repository
public interface IEntrepriseDAO extends JpaRepository<EntrepriseDTO, Long> {
}
