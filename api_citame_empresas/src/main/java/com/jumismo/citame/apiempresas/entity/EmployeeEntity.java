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

@Entity
@Table(name = "employer")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	@NotBlank
	private String phone;

	@NotNull
	private boolean isOwner;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	@Temporal(TemporalType.DATE)
	private Date fechaBaja;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private EntrepriseEntity entreprise;

}
