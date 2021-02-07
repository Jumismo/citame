package com.jumismo.citame.apiempresas.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.jumismo.citame.apiempresas.dao.IEmployeeDAO;
import com.jumismo.citame.apiempresas.dao.IEntrepriseDAO;
import com.jumismo.citame.apiempresas.dto.EmployeeDTO;
import com.jumismo.citame.apiempresas.entity.EmployeeEntity;
import com.jumismo.citame.apiempresas.entity.EntrepriseEntity;
import com.jumismo.citame.apiempresas.services.IEmployeeService;
import com.jumismo.citame.apiempresas.services.impl.EmployeeServiceImpl;

@SpringBootTest
public class EmployeeServiceTest {

	@Mock
	private IEmployeeDAO employeeDAO;
	
	@Mock
	private IEntrepriseDAO entrepriseDAO;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	private IEmployeeService employeeService;
	
	private EmployeeEntity employee1;

	
	@BeforeEach
	void setUp() {
		employeeService = new EmployeeServiceImpl(employeeDAO, entrepriseDAO, modelMapper);
		
		EntrepriseEntity entreprise1 = new EntrepriseEntity();
		entreprise1.setId(1L);
		entreprise1.setName("Entreprise");
		entreprise1.setCif("12345678");

		employee1 = new EmployeeEntity();
		employee1.setId(1L);
		employee1.setName("Employee");
		employee1.setPhone("12345678");
		employee1.setOwner(false);
		employee1.setEntreprise(entreprise1);
		
		when(employeeDAO.save(employee1)).thenReturn(employee1);
		when(entrepriseDAO.save(entreprise1)).thenReturn(entreprise1);
		when(entrepriseDAO.findById(1L)).thenReturn(Optional.of(entreprise1));
	}
	
	@Test
	void addEmployeeTest() {
		
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId(1L);
		employeeDTO.setName("Employee");
		employeeDTO.setPhone("12345678");
		employeeDTO.setOwner(false);
		employeeDTO.setEntrepriseId(1L);
		
		employeeService.save(employeeDTO);
		
		verify(employeeDAO, times(1)).save(employee1);
	}
}
