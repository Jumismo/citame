package com.jumismo.citame.apiempresas.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class CustomerEntity.
 */
@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class CustomerEntity {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The name. */
	@NotBlank
	private String name;

	/** The phone. */
	@NotBlank
	private String phone;
	
	/** The address. */
	@NotBlank
	private String address;
	
	/** The mail. */
	@NotBlank
	@Email
	private String mail;
	
	/** The fecha alta. */
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	/** The fecha modificacion. */
	@Temporal(TemporalType.DATE)
	private Date fechaModificacion;
	
	/** The fecha baja. */
	@Temporal(TemporalType.DATE)
	private Date fechaBaja;
}
