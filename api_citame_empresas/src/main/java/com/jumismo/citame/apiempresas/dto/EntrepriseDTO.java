package com.jumismo.citame.apiempresas.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class EntrepriseDTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntrepriseDTO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8639517619034425102L;

	/** The id. */
	private Long id;

	/** The name. */
	private String name;

	/** The cif. */
	private String cif;

	/** The list employer. */
	private Set<EmployeeDTO> listEmployer;
}
