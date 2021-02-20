package com.jumismo.citame.apiempresas.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class EntrepriseEntity.
 */
@Entity
@Table(name = "entreprise")
@Getter
@Setter
@NoArgsConstructor
public class EntrepriseEntity {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The name. */
	@NotNull
	@NotBlank
	private String name;

	/** The cif. */
	@NotNull
	@NotBlank
	private String cif;
	
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

	/** The list employer. */
	@OneToMany(mappedBy = "entreprise", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<EmployeeEntity> listEmployer = new ArrayList<>();


}
