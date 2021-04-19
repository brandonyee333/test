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

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Rachael Koestartyo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistributionDTO {

	public DistributionDTO(Distribution distribution) {
		_count = distribution.getCount();
		_values = distribution.getValues();
	}

	public DistributionDTO(
		List<Distribution> distributions, String transformationKey) {

		_distributionDTOMap = Collections.singletonMap(
			transformationKey,
			SetUtil.map(distributions, DistributionDTO::new));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DistributionDTO)) {
			return false;
		}

		DistributionDTO distributionDTO = (DistributionDTO)obj;

		if (Objects.equals(_count, distributionDTO._count) &&
			Objects.equals(_values, distributionDTO._values)) {

			return true;
		}

		return false;
	}

	@JsonProperty("count")
	public Integer getCount() {
		return _count;
	}

	@JsonAnyGetter
	public Map<String, Set<DistributionDTO>> getDistributionDTOMap() {
		return _distributionDTOMap;
	}

	@JsonProperty("values")
	public List<Object> getValues() {
		return _values;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_count, _values);
	}

	public void setCount(Integer count) {
		_count = count;
	}

	public void setValues(List<Object> values) {
		_values = values;
	}

	private Integer _count;
	private Map<String, Set<DistributionDTO>> _distributionDTOMap =
		new HashMap<>();
	private List<Object> _values;

}