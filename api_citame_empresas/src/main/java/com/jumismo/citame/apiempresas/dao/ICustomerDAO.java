package com.jumismo.citame.apiempresas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jumismo.citame.apiempresas.entity.CustomerEntity;

@Repository
public interface ICustomerDAO extends JpaRepository<CustomerEntity, Long>{

}
