package com.jumismo.citame.apiempresas.dao;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.jumismo.citame.apiempresas.entity.EmployeeEntity;
import com.jumismo.citame.apiempresas.entity.EntrepriseEntity;

@SpringBootTest
public class EmployeeDAOTest {
	
	@Mock
	private IEmployeeDAO employeeDAO;
	
	private EmployeeEntity employee1;
	
	@BeforeEach
	void setUp() {
		EntrepriseEntity entreprise1 = new EntrepriseEntity();
		entreprise1.setId(1L);
		entreprise1.setName("Entreprise");
		entreprise1.setCif("12345678");

		employee1 = new EmployeeEntity();
		employee1.setName("Employee");
		employee1.setPhone("12345678");
		employee1.setOwner(false);
		employee1.setEntreprise(entreprise1);
		
		when(employeeDAO.save(employee1)).thenReturn(employee1);
	}
	
	@Test
	void addEmployeeTest() {
		EmployeeEntity entity = employeeDAO.save(employee1);
		
		assertTrue(entity.getName().equals(employee1.getName()));
		verify(employeeDAO, timeout(1)).save(employee1);
	}

}
