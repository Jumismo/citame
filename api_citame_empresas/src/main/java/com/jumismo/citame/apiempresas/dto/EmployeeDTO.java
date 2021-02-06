package com.jumismo.citame.apiempresas.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class EmployeeDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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

	/** The entreprise. */
	private Long entrepriseId;
}
