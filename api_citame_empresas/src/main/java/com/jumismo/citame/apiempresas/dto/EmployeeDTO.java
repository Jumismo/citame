package com.jumismo.citame.apiempresas.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jumismo.citame.apiempresas.utils.Constantes;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class EmployeeDTO.
 */
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9153277495284218570L;

	/** The id. */
	@ApiModelProperty(hidden = true)
	private Long id;

	/** The name. */
	private String name;

	/** The phone. */
	private String phone;

	/** The is owner. */
	private boolean isOwner;
	
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
	@ApiModelProperty(hidden = true)
	private Date fechaBaja;

	/** The entreprise. */
	private Long entrepriseId;
}
