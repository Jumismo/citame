package com.jumismo.citame.apiempresas.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class EmployeeEntity.
 */
@Entity
@Table(name = "employer")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeEntity {

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

	/** The is owner. */
	@NotNull
	private boolean isOwner;
	
	/** The fecha alta. */
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	/** The fecha modificacion. */
	@Temporal(TemporalType.DATE)
	private Date fechaModificacion;

	
	/** The fecha baja. */
	@Temporal(TemporalType.DATE)
	private Date fechaBaja;

	/** The entreprise. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private EntrepriseEntity entreprise;

}
