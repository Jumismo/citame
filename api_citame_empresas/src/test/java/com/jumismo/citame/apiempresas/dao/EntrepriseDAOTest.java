package com.jumismo.citame.apiempresas.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
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

	@BeforeEach
	void setUp() {
		EntrepriseEntity entreprise = new EntrepriseEntity();
		entreprise.setName("Entreprise");
		entreprise.setCif("12345678");

		EmployeeEntity employee = new EmployeeEntity();
		employee.setName("Employee");
		employee.setPhone("12345678");
		employee.setOwner(false);
		employee.setEntreprise(entreprise);

		entrepriseDAO.save(entreprise);
	}

	@Test
	void getEntrepriseByIdTest() {
		Optional<EntrepriseEntity> entrepriseFound = entrepriseDAO.findById(1L);

		assertThat(entrepriseFound).isNotNull();
	}

	@Test
	void getAllEntrepriseTest() {
		Optional<List<EntrepriseEntity>> entreprises = Optional.ofNullable(entrepriseDAO.findAll());
		
		if(entreprises.isPresent()) {
			assertTrue(entreprises.get().size() == 6);
		}
	}

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

		Optional<EntrepriseEntity> entrepriseFound = entrepriseDAO.findById(2L);

		assertThat(entrepriseFound.get().getName().equals(entreprise.getName())).isTrue();

	}

}
