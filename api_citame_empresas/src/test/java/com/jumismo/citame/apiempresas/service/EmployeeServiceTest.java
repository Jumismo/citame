package com.jumismo.citame.apiempresas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.jumismo.citame.apiempresas.dao.IEmployeeDAO;
import com.jumismo.citame.apiempresas.dao.IEntrepriseDAO;
import com.jumismo.citame.apiempresas.dto.EmployeeDTO;
import com.jumismo.citame.apiempresas.entity.EmployeeEntity;
import com.jumismo.citame.apiempresas.entity.EntrepriseEntity;
import com.jumismo.citame.apiempresas.services.IEmployeeService;
import com.jumismo.citame.apiempresas.services.impl.EmployeeServiceImpl;
import com.jumismo.citame.apiempresas.utils.TestData;

/**
 * The Class EmployeeServiceTest.
 */
@SpringBootTest
public class EmployeeServiceTest {

	/** The employee DAO. */
	@Mock
	private IEmployeeDAO employeeDAO;
	
	/** The entreprise DAO. */
	@Mock
	private IEntrepriseDAO entrepriseDAO;
	
	/** The model mapper. */
	private ModelMapper modelMapper = new ModelMapper();
	
	/** The employee service. */
	private IEmployeeService employeeService;
	
	/** The employee 1. */
	private EmployeeEntity employee1;
	
	
	/**
	 * Sets the up.
	 *
	 * @throws ParseException the parse exception
	 */
	@BeforeEach
	void setUp() throws ParseException{
		employeeService = new EmployeeServiceImpl(employeeDAO, entrepriseDAO, modelMapper);
		
		EntrepriseEntity entreprise1 = TestData.getEntrepriseEntity();

		employee1 = TestData.getEmployeeEntity();
		employee1.setEntreprise(entreprise1);
		
		when(employeeDAO.findAll()).thenReturn(List.of(employee1));
		when(employeeDAO.findById(1L)).thenReturn(Optional.of(employee1));
		when(employeeDAO.save(Mockito.any(EmployeeEntity.class))).thenReturn(employee1);
		when(entrepriseDAO.save(Mockito.any(EntrepriseEntity.class))).thenReturn(entreprise1);
		when(entrepriseDAO.findById(1L)).thenReturn(Optional.of(entreprise1));
	}
	
	/**
	 * Gets the all employee test.
	 *
	 * @return the all employee test
	 */
	@Test
	void getAllEmployeeTest() {
		List<EmployeeDTO> employees = employeeService.findAll();
		
		assertEquals(1, employees.size());
		verify(employeeDAO, times(1)).findAll();
	}
	
	/**
	 * Gets the employee test.
	 *
	 * @return the employee test
	 */
	@Test
	void getEmployeeTest() {
		EmployeeDTO employee = employeeService.findById(1L);
		
		assertEquals(1L, employee.getId());
		verify(employeeDAO, times(1)).findById(1L);
	}
	
	/**
	 * Gets the employee not found test.
	 *
	 * @return the employee not found test
	 */
	@Test
	void getEmployeeNotFoundTest() {
		when(employeeDAO.findById(1L)).thenReturn(Optional.empty());

		EmployeeDTO employee = employeeService.findById(1L);
		
		assertNotEquals(1L, employee.getId());
		verify(employeeDAO, times(1)).findById(1L);
	}
	
	/**
	 * Adds the employee not found test.
	 *
	 * @throws ParseException the parse exception
	 */
	@Test
	void addEmployeeNotFoundTest() throws ParseException {
		when(entrepriseDAO.findById(1L)).thenReturn(Optional.empty());

		EmployeeDTO employeeDTO = TestData.getEmployeeDTO();
		employeeDTO.setEntrepriseId(1L);
		
		employeeService.save(employeeDTO);
		
		verify(employeeDAO, times(0)).save(Mockito.any(EmployeeEntity.class));
	}
	
	/**
	 * Adds the employee test.
	 *
	 * @throws ParseException the parse exception
	 */
	@Test
	void addEmployeeTest() throws ParseException {
		EmployeeDTO employeeDTO = TestData.getEmployeeDTO();
		employeeDTO.setEntrepriseId(1L);
		
		employeeService.save(employeeDTO);
		
		verify(employeeDAO, times(1)).save(Mockito.any(EmployeeEntity.class));
	}
	
	@Test
	void updateEmployeeTest() throws ParseException {
		EmployeeDTO employeeDTO = TestData.getEmployeeDTO();
		employeeDTO.setEntrepriseId(1L);
		
		EmployeeDTO respuesta = employeeService.update(1L, employeeDTO);

		assertEquals(employeeDTO.getName(), respuesta.getName());
		verify(employeeDAO, times(1)).findById(1L);
		verify(employeeDAO, times(1)).save(Mockito.any(EmployeeEntity.class));
	}
	
	@Test
	void updateEmployeeNotFoundTest() throws ParseException {
		when(employeeDAO.findById(1L)).thenReturn(Optional.empty());
		EmployeeDTO employeeDTO = TestData.getEmployeeDTO();
		employeeDTO.setEntrepriseId(1L);
		
		employeeService.update(1L, employeeDTO);
		
		verify(employeeDAO, times(1)).findById(1L);
		verify(employeeDAO, times(0)).save(Mockito.any(EmployeeEntity.class));
	}
	
	/**
	 * Delete employee test.
	 */
	@Test
	void deleteEmployeeTest() {
		employeeService.delete(1L);
		
		verify(employeeDAO, times(1)).findById(Mockito.anyLong());
		verify(employeeDAO, times(1)).save(Mockito.any(EmployeeEntity.class));
	}
	
	/**
	 * Delete employee not found test.
	 */
	@Test
	void deleteEmployeeNotFoundTest() {
		when(employeeDAO.findById(1L)).thenReturn(Optional.empty());
		
		employeeService.delete(1L);
		
		verify(employeeDAO, times(1)).findById(Mockito.anyLong());
		verify(employeeDAO, times(0)).save(Mockito.any(EmployeeEntity.class));
	}
	
}
