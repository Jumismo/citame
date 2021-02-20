package com.jumismo.citame.apiempresas.utils;

import java.text.ParseException;

import org.apache.commons.lang3.time.FastDateFormat;

import com.jumismo.citame.apiempresas.dto.EmployeeDTO;
import com.jumismo.citame.apiempresas.dto.EntrepriseDTO;
import com.jumismo.citame.apiempresas.entity.EmployeeEntity;
import com.jumismo.citame.apiempresas.entity.EntrepriseEntity;

/**
 * The Class TestData.
 */
public class TestData {

	/**
	 * Gets the entreprise DTO.
	 *
	 * @return the entreprise DTO
	 * @throws ParseException the parse exception
	 */
	public static EntrepriseDTO getEntrepriseDTO() throws ParseException {
		EntrepriseDTO entreprise = new EntrepriseDTO();
		entreprise.setId(1L);
		entreprise.setName("Entreprise");
		entreprise.setCif("12345678");
		entreprise.setFechaAlta(FastDateFormat.getInstance(Constantes.FORMATO_FECHA_DD_MM_YYYY).parse("12-12-2012"));
		return entreprise;
	}
	
	/**
	 * Gets the entreprise entity.
	 *
	 * @return the entreprise entity
	 * @throws ParseException the parse exception
	 */
	public static EntrepriseEntity getEntrepriseEntity() throws ParseException {
		EntrepriseEntity entreprise = new EntrepriseEntity();
		entreprise.setId(1L);
		entreprise.setName("Entreprise");
		entreprise.setCif("12345678");
		entreprise.setFechaAlta(FastDateFormat.getInstance(Constantes.FORMATO_FECHA_DD_MM_YYYY).parse("12-12-2012"));

		return entreprise;
	}
	
	/**
	 * Gets the employee DTO.
	 *
	 * @return the employee DTO
	 * @throws ParseException the parse exception
	 */
	public static EmployeeDTO getEmployeeDTO() throws ParseException {
		EmployeeDTO employee = new EmployeeDTO();
		employee.setId(1L);
		employee.setName("Employee");
		employee.setPhone("12345678");
		employee.setOwner(false);
		employee.setFechaAlta(FastDateFormat.getInstance(Constantes.FORMATO_FECHA_DD_MM_YYYY).parse("12-12-2012"));
		return employee;
	}
	
	/**
	 * Gets the employee entity.
	 *
	 * @return the employee entity
	 * @throws ParseException the parse exception
	 */
	public static EmployeeEntity getEmployeeEntity() throws ParseException {
		EmployeeEntity employee = new EmployeeEntity();
		employee.setId(1L);
		employee.setName("Employee");
		employee.setPhone("12345678");
		employee.setOwner(false);
		employee.setFechaAlta(FastDateFormat.getInstance(Constantes.FORMATO_FECHA_DD_MM_YYYY).parse("12-12-2012"));
		return employee;
	}
	
}
