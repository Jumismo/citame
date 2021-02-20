package com.jumismo.citame.apiempresas.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.jumismo.citame.apiempresas.entity.EmployeeEntity;
import com.jumismo.citame.apiempresas.entity.EntrepriseEntity;

/**
 * The Class EntrepriseDAOTest.
 */
@SpringBootTest
class EntrepriseDAOTest {

	/** The entreprise DAO. */
	@Mock
	private IEntrepriseDAO entrepriseDAO;
	
	/** The entreprise 2. */
	EntrepriseEntity entreprise2;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		EntrepriseEntity entreprise1 = new EntrepriseEntity();
		entreprise1.setId(1L);
		entreprise1.setName("Entreprise");
		entreprise1.setCif("12345678");

		EmployeeEntity employee1 = new EmployeeEntity();
		employee1.setName("Employee");
		employee1.setPhone("12345678");
		employee1.setOwner(false);
		employee1.setEntreprise(entreprise1);
		
		entreprise2 = new EntrepriseEntity();
		entreprise2.setId(2L);
		entreprise2.setName("Entreprise");
		entreprise2.setCif("123456789");

		EmployeeEntity employee2 = new EmployeeEntity();
		employee2.setName("Employee");
		employee2.setPhone("123456789");
		employee2.setOwner(false);
		employee2.setEntreprise(entreprise2);

		when(entrepriseDAO.findAll()).thenReturn(List.of(entreprise1));
		when(entrepriseDAO.findById(1L)).thenReturn(Optional.of(entreprise1));
		when(entrepriseDAO.save(entreprise2)).thenReturn(entreprise2);
	}

	/**
	 * Gets the entreprise by id test.
	 *
	 * @return the entreprise by id test
	 */
	@Test
	void getEntrepriseByIdTest() {
		Optional<EntrepriseEntity> entrepriseFound = entrepriseDAO.findById(1L);

		assertThat(entrepriseFound).isNotNull();
		verify(entrepriseDAO, times(1)).findById(1L);
	}

	/**
	 * Gets the all entreprise test.
	 *
	 * @return the all entreprise test
	 */
	@Test
	void getAllEntrepriseTest() {
		List<EntrepriseEntity> entreprises = entrepriseDAO.findAll();
		
		assertEquals(1, entreprises.size());
		verify(entrepriseDAO, times(1)).findAll();
	}

	/**
	 * Adds the entreprise test.
	 */
	@Test
	void addEntrepriseTest() {
		EntrepriseEntity entity = entrepriseDAO.save(entreprise2);
		
		assertEquals(entreprise2.getId(), entity.getId());
		verify(entrepriseDAO, times(1)).save(entreprise2);
	}

}
