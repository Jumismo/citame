package com.jumismo.citame.apiempresas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.jumismo.citame.apiempresas.dao.IEntrepriseDAO;
import com.jumismo.citame.apiempresas.dto.EntrepriseDTO;
import com.jumismo.citame.apiempresas.entity.EmployeeEntity;
import com.jumismo.citame.apiempresas.entity.EntrepriseEntity;
import com.jumismo.citame.apiempresas.services.IEntrepriseService;
import com.jumismo.citame.apiempresas.services.impl.EntrepriseServiceImpl;

@SpringBootTest
class EntrepriseServiceTest {
	
	@Mock
	private IEntrepriseDAO entrepriseDAO;
	
	private ModelMapper modelMapper = new ModelMapper();

	private IEntrepriseService entrepriseService;
	
	
	@BeforeEach
	void setUp() {
		entrepriseService = new EntrepriseServiceImpl(entrepriseDAO, modelMapper);
		
		List<EntrepriseEntity> entreprises = new ArrayList<>();
		
		EntrepriseEntity entreprise = new EntrepriseEntity();
		entreprise.setName("Entreprise");
		entreprise.setCif("12345678");

		EmployeeEntity employee = new EmployeeEntity();
		employee.setName("Employee");
		employee.setPhone("12345678");
		employee.setOwner(false);
		employee.setEntreprise(entreprise);
		entreprise.setListEmployer(List.of(employee));
		
		entreprises.add(entreprise);
		
		when(entrepriseDAO.findAll()).thenReturn(entreprises);
	}
	
	@Test
	void getAllEntreprise() {
		List<EntrepriseDTO> entreprisesDTOList = entrepriseService.getAllEntreprise();
		
		assertEquals(1, entreprisesDTOList.size());
		verify(entrepriseDAO, times(1)).findAll();
	}
}
