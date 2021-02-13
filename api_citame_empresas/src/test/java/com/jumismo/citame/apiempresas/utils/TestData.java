package com.jumismo.citame.apiempresas.utils;

import java.text.ParseException;

import org.apache.commons.lang3.time.FastDateFormat;

import com.jumismo.citame.apiempresas.dto.EmployeeDTO;
import com.jumismo.citame.apiempresas.dto.EntrepriseDTO;
import com.jumismo.citame.apiempresas.entity.EmployeeEntity;
import com.jumismo.citame.apiempresas.entity.EntrepriseEntity;

public class TestData {

	public static EntrepriseDTO getEntrepriseDTO() throws ParseException {
		EntrepriseDTO entreprise = new EntrepriseDTO();
		entreprise.setId(1L);
		entreprise.setName("Entreprise");
		entreprise.setCif("12345678");
		entreprise.setFechaAlta(FastDateFormat.getInstance(Constantes.FORMATO_FECHA_DD_MM_YYYY).parse("12-12-2012"));
		return entreprise;
	}
	
	public static EntrepriseEntity getEntrepriseEntity() throws ParseException {
		EntrepriseEntity entreprise = new EntrepriseEntity();
		entreprise.setId(1L);
		entreprise.setName("Entreprise");
		entreprise.setCif("12345678");
		entreprise.setFechaAlta(FastDateFormat.getInstance(Constantes.FORMATO_FECHA_DD_MM_YYYY).parse("12-12-2012"));

		return entreprise;
	}
	
	public static EmployeeDTO getEmployeeDTO() throws ParseException {
		EmployeeDTO employee = new EmployeeDTO();
		employee.setId(1L);
		employee.setName("Employee");
		employee.setPhone("12345678");
		employee.setOwner(false);
		employee.setFechaAlta(FastDateFormat.getInstance(Constantes.FORMATO_FECHA_DD_MM_YYYY).parse("12-12-2012"));
		return employee;
	}
	
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
