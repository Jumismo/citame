package com.jumismo.citame.apiempresas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jumismo.citame.apiempresas.entity.EmployeeEntity;

/**
 * The Interface IEmployeeDAO.
 */
@Repository
public interface IEmployeeDAO extends JpaRepository<EmployeeEntity, Long>{

}
