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
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

/**
 * @author Marcos Martins
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExperimentVariantsDTO {

	public ExperimentVariantsDTO() {
	}

	public ExperimentVariantsDTO(Set<ExperimentVariant> experimentVariants) {
		SetUtil.map(experimentVariants, ExperimentVariantDTO::new);
	}

	@JsonProperty("dxpVariants")
	@Valid
	public Set<ExperimentVariantDTO> getExperimentVariantDTOs() {
		return _experimentVariantDTOs;
	}

	public void setExperimentVariantDTOs(
		Set<ExperimentVariantDTO> experimentVariantDTOs) {

		_experimentVariantDTOs = experimentVariantDTOs;
	}

	private Set<ExperimentVariantDTO> _experimentVariantDTOs = new HashSet<>();

}