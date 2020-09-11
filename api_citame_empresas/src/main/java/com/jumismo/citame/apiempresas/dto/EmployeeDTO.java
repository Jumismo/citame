package com.jumismo.citame.apiempresas.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

	@Id
	@GeneratedValue
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
	@ManyToOne
	@JoinColumn
	private EntrepriseDTO entreprise;

}
