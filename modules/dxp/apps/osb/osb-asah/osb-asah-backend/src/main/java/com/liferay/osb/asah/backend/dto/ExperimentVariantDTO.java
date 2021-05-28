/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.osb.asah.common.entity.ExperimentVariant;
import com.liferay.osb.asah.common.graphql.GraphQLProperty;
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Marcos Martins
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExperimentVariantDTO {

	public ExperimentVariantDTO(ExperimentVariant experimentVariant) {
		_changes = experimentVariant.getChanges();
		_control = experimentVariant.getControl();
		_dxpVariantId = experimentVariant.getDXPVariantId();
		_dxpVariantName = experimentVariant.getDXPVariantName();
		_trafficSplit = experimentVariant.getTrafficSplit();
	}

	public ExperimentVariantDTO(Set<ExperimentVariant> experimentVariants) {
		SetUtil.map(experimentVariants, ExperimentVariantDTO::new);
	}

	@Min(0)
	@NotNull
	public Integer getChanges() {
		return _changes;
	}

	@NotNull
	public Boolean getControl() {
		return _control;
	}

	@GraphQLProperty("dxpVariantId")
	@JsonProperty("dxpVariantId")
	@NotBlank
	public String getDXPVariantId() {
		return _dxpVariantId;
	}

	@GraphQLProperty("dxpVariantName")
	@JsonProperty("dxpVariantName")
	@NotBlank
	public String getDXPVariantName() {
		return _dxpVariantName;
	}

	@JsonProperty("dxpVariants")
	@NotEmpty
	@Valid
	public Set<ExperimentVariantDTO> getExperimentVariantDTOs() {
		return _experimentVariantDTOs;
	}

	@Max(100)
	@Min(0)
	@NotNull
	public Double getTrafficSplit() {
		return _trafficSplit;
	}

	public void setChanges(Integer changes) {
		_changes = changes;
	}

	public void setControl(Boolean control) {
		_control = control;
	}

	public void setDXPVariantId(String dxpVariantId) {
		_dxpVariantId = dxpVariantId;
	}

	public void setDXPVariantName(String dxpVariantName) {
		_dxpVariantName = dxpVariantName;
	}

	public void setExperimentVariantDTOs(
		Set<ExperimentVariantDTO> experimentVariantDTOs) {

		_experimentVariantDTOs = experimentVariantDTOs;
	}

	public void setTrafficSplit(Double trafficSplit) {
		_trafficSplit = trafficSplit;
	}

	private Integer _changes;
	private Boolean _control;
	private String _dxpVariantId;
	private String _dxpVariantName;
	private Set<ExperimentVariantDTO> _experimentVariantDTOs = new HashSet<>();
	private Double _trafficSplit;

}