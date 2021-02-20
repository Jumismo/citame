package com.jumismo.citame.apiempresas.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jumismo.citame.apiempresas.utils.Constantes;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class EntrepriseDTO.
 */
@NoArgsConstructor
@Getter
@Setter
public class EntrepriseDTO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8639517619034425102L;

	/** The id. */
	@ApiModelProperty(hidden = true)
	private Long id;

	/** The name. */
	private String name;

	/** The cif. */
	private String cif;
	
	/** The fecha alta. */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constantes.FORMATO_FECHA_DD_MM_YYYY)
	@ApiModelProperty(hidden = true)
	private Date fechaAlta;
	
	/** The fecha modificacion. */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constantes.FORMATO_FECHA_DD_MM_YYYY)
	@ApiModelProperty(hidden = true)
	private Date fechaModificacion;
	
	/** The fecha baja. */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constantes.FORMATO_FECHA_DD_MM_YYYY)
	private Date fechaBaja;

	/** The list employer. */
	private Set<EmployeeDTO> listEmployer;
}
