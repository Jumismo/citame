package com.jumismo.citame.apiempresas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.jumismo.citame.apiempresas.dao.IEntrepriseDAO;
import com.jumismo.citame.apiempresas.dto.EntrepriseDTO;
import com.jumismo.citame.apiempresas.entity.EmployeeEntity;
import com.jumismo.citame.apiempresas.entity.EntrepriseEntity;
import com.jumismo.citame.apiempresas.services.impl.EntrepriseServiceImpl;

class EntrepriseServiceTest {

	@InjectMocks
	private EntrepriseServiceImpl entrepriseService;
	
	@Mock
	private IEntrepriseDAO entrepriseDAO;
	
	@Mock
	private ModelMapper modelMapper;
	
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void getAllEntreprise() {
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
		
		List<EntrepriseDTO> entreprisesDTOList = entrepriseService.getAllEntreprise();
		
		assertEquals(1, entreprisesDTOList.size());
		verify(entrepriseDAO, times(1)).findAll();
	}
}
