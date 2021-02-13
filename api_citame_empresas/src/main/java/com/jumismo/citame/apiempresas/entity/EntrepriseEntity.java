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

@Entity
@Table(name = "entreprise")
@Getter
@Setter
@NoArgsConstructor
public class EntrepriseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	@NotBlank
	private String cif;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	@Temporal(TemporalType.DATE)
	private Date fechaBaja;

	@OneToMany(mappedBy = "entreprise", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<EmployeeEntity> listEmployer = new ArrayList<>();


}
