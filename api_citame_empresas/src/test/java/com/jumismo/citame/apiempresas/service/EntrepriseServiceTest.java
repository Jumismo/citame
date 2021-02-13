package com.jumismo.citame.apiempresas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.jumismo.citame.apiempresas.dao.IEntrepriseDAO;
import com.jumismo.citame.apiempresas.dto.EntrepriseDTO;
import com.jumismo.citame.apiempresas.entity.EmployeeEntity;
import com.jumismo.citame.apiempresas.entity.EntrepriseEntity;
import com.jumismo.citame.apiempresas.services.IEntrepriseService;
import com.jumismo.citame.apiempresas.services.impl.EntrepriseServiceImpl;
import com.jumismo.citame.apiempresas.utils.TestData;

@SpringBootTest
class EntrepriseServiceTest {
	
	@Mock
	private IEntrepriseDAO entrepriseDAO;
	
	private ModelMapper modelMapper = new ModelMapper();

	private IEntrepriseService entrepriseService;
	
	
	@BeforeEach
	void setUp() throws ParseException {
		entrepriseService = new EntrepriseServiceImpl(entrepriseDAO, modelMapper);
		
		List<EntrepriseEntity> entreprises = new ArrayList<>();
		
		EntrepriseEntity entreprise = TestData.getEntrepriseEntity();

		EmployeeEntity employee = TestData.getEmployeeEntity();
		employee.setEntreprise(entreprise);
		entreprise.setListEmployer(List.of(employee));
		
		entreprises.add(entreprise);
		
		when(entrepriseDAO.findAll()).thenReturn(entreprises);
		when(entrepriseDAO.findById(1L)).thenReturn(Optional.of(entreprise));
		when(entrepriseDAO.findById(2L)).thenReturn(Optional.empty());
		when(entrepriseDAO.save(Mockito.any(EntrepriseEntity.class))).thenReturn(entreprise);
	}
	
	@Test
	void getAllEntrepriseTest() {
		List<EntrepriseDTO> entreprisesDTOList = entrepriseService.getAllEntreprise();
		
		assertEquals(1, entreprisesDTOList.size());
		verify(entrepriseDAO, times(1)).findAll();
	}
	
	@Test
	void getEntrepriseTest() {
		EntrepriseDTO entrepriseDTO = entrepriseService.getEntreprise(1L);
		
		assertEquals(1L, entrepriseDTO.getId());
		verify(entrepriseDAO, times(1)).findById(1L);
	}
	
	@Test
	void getNotFoundEntrepriseTest() {
		EntrepriseDTO entrepriseDTO = entrepriseService.getEntreprise(2L);
		assertNotEquals(2L, entrepriseDTO.getId());
		verify(entrepriseDAO, times(1)).findById(2L);
	}
	
	@Test
	void saveEntrepriseTest() throws ParseException {
		entrepriseService.save(TestData.getEntrepriseDTO());
		verify(entrepriseDAO, times(1)).save(Mockito.any(EntrepriseEntity.class));
	}
	
	@Test
	void deleteEntrepriseTest() {
		entrepriseService.delete(1L);
		verify(entrepriseDAO, times(1)).findById(Mockito.anyLong());
		verify(entrepriseDAO, times(1)).save(Mockito.any(EntrepriseEntity.class));
	}
	
	@Test
	void deleteEntrepriseNotFoundTest() {
		when(entrepriseDAO.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		entrepriseService.delete(1L);
		verify(entrepriseDAO, times(1)).findById(Mockito.anyLong());
		verify(entrepriseDAO, times(0)).save(Mockito.any(EntrepriseEntity.class));
	}
}
