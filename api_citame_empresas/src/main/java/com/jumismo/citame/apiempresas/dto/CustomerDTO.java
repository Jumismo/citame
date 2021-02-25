package com.jumismo.citame.apiempresas.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jumismo.citame.apiempresas.utils.Constantes;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class CustomerDTO.
 */
@NoArgsConstructor
@Data
public class CustomerDTO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2813909967752775383L;

	/** The id. */
	@ApiModelProperty(hidden = true)
	private Long id;

	/** The name. */
	private String name;

	/** The phone. */
	private String phone;
	
	/** The address. */
	private String address;
	
	/** The mail. */
	@Email
	private String mail;
	
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

}
