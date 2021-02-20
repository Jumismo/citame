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

/**
 * The Class EntrepriseServiceTest.
 */
@SpringBootTest
class EntrepriseServiceTest {
	
	/** The entreprise DAO. */
	@Mock
	private IEntrepriseDAO entrepriseDAO;
	
	/** The model mapper. */
	private ModelMapper modelMapper = new ModelMapper();

	/** The entreprise service. */
	private IEntrepriseService entrepriseService;
	
	
	/**
	 * Sets the up.
	 *
	 * @throws ParseException the parse exception
	 */
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
	
	/**
	 * Gets the all entreprise test.
	 *
	 * @return the all entreprise test
	 */
	@Test
	void getAllEntrepriseTest() {
		List<EntrepriseDTO> entreprisesDTOList = entrepriseService.getAllEntreprise();
		
		assertEquals(1, entreprisesDTOList.size());
		verify(entrepriseDAO, times(1)).findAll();
	}
	
	/**
	 * Gets the entreprise test.
	 *
	 * @return the entreprise test
	 */
	@Test
	void getEntrepriseTest() {
		EntrepriseDTO entrepriseDTO = entrepriseService.getEntreprise(1L);
		
		assertEquals(1L, entrepriseDTO.getId());
		verify(entrepriseDAO, times(1)).findById(1L);
	}
	
	/**
	 * Gets the not found entreprise test.
	 *
	 * @return the not found entreprise test
	 */
	@Test
	void getNotFoundEntrepriseTest() {
		EntrepriseDTO entrepriseDTO = entrepriseService.getEntreprise(2L);
		assertNotEquals(2L, entrepriseDTO.getId());
		verify(entrepriseDAO, times(1)).findById(2L);
	}
	
	/**
	 * Save entreprise test.
	 *
	 * @throws ParseException the parse exception
	 */
	@Test
	void saveEntrepriseTest() throws ParseException {
		entrepriseService.save(TestData.getEntrepriseDTO());
		verify(entrepriseDAO, times(1)).save(Mockito.any(EntrepriseEntity.class));
	}
	
	
	/**
	 * Update entreprise not found test.
	 *
	 * @throws ParseException the parse exception
	 */
	@Test
	void updateEntrepriseNotFoundTest() throws ParseException {
		when(entrepriseDAO.findById(1L)).thenReturn(Optional.empty());
		
		entrepriseService.update(1L, TestData.getEntrepriseDTO());
		
		verify(entrepriseDAO, times(0)).save(Mockito.any(EntrepriseEntity.class));
	}
	
	/**
	 * Update entreprise test.
	 *
	 * @throws ParseException the parse exception
	 */
	@Test
	void updateEntrepriseTest() throws ParseException {
		entrepriseService.update(1L, TestData.getEntrepriseDTO());
		
		verify(entrepriseDAO, times(1)).save(Mockito.any(EntrepriseEntity.class));
	}
	
	/**
	 * Delete entreprise test.
	 */
	@Test
	void deleteEntrepriseTest() {
		entrepriseService.delete(1L);
		verify(entrepriseDAO, times(1)).findById(Mockito.anyLong());
		verify(entrepriseDAO, times(1)).save(Mockito.any(EntrepriseEntity.class));
	}
	
	/**
	 * Delete entreprise not found test.
	 */
	@Test
	void deleteEntrepriseNotFoundTest() {
		when(entrepriseDAO.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		entrepriseService.delete(1L);
		verify(entrepriseDAO, times(1)).findById(Mockito.anyLong());
		verify(entrepriseDAO, times(0)).save(Mockito.any(EntrepriseEntity.class));
	}
}
