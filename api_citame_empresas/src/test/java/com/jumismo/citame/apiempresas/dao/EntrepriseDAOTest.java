package com.jumismo.citame.apiempresas.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import com.jumismo.citame.apiempresas.entity.EmployeeEntity;
import com.jumismo.citame.apiempresas.entity.EntrepriseEntity;

@DataJpaTest
@Transactional
class EntrepriseDAOTest {

	@Autowired
	private IEntrepriseDAO entrepriseDAO;

	@Test
	void addEntrepriseTest() {

		EntrepriseEntity entreprise = new EntrepriseEntity();
		entreprise.setName("Entreprise");
		entreprise.setCif("123456789");

		EmployeeEntity employee = new EmployeeEntity();
		employee.setName("Employee");
		employee.setPhone("123456789");
		employee.setOwner(false);
		employee.setEntreprise(entreprise);

		entrepriseDAO.save(entreprise);

		Optional<EntrepriseEntity> entrepriseFound = entrepriseDAO.findById(1L);

		assertThat(entrepriseFound.get().getName().equals(entreprise.getName())).isTrue();

	}
}
