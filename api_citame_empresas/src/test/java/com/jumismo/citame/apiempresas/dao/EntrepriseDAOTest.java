package com.jumismo.citame.apiempresas.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.jumismo.citame.apiempresas.dto.EmployeeDTO;
import com.jumismo.citame.apiempresas.dto.EntrepriseDTO;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EntrepriseDAOTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private IEntrepriseDAO entrepriseDAO;

	@Test
	public void addEntrepriseTest() {

		EntrepriseDTO entreprise = new EntrepriseDTO();
		entreprise.setName("Entreprise");
		entreprise.setCif("123456789");

		entityManager.persist(entreprise);
		entityManager.flush();

		EmployeeDTO employee = new EmployeeDTO();
		employee.setName("Employee");
		employee.setPhone("123456789");
		employee.setOwner(false);
		employee.setEntreprise(entreprise);

		entityManager.persist(employee);
		entityManager.flush();

		Optional<EntrepriseDTO> entrepriseFound = entrepriseDAO.findById(1L);

		assertThat(entrepriseFound.get().getName().equals(entreprise.getName()));

	}
}
