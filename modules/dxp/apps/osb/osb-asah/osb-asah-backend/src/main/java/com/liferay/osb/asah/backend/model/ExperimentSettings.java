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

package com.liferay.osb.asah.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.osb.asah.common.model.DXPVariantSettings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Marcellus Tavares
 */
public final class ExperimentSettings {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ExperimentSettings)) {
			return false;
		}

		ExperimentSettings experimentSettings = (ExperimentSettings)obj;

		if (Objects.equals(
				_confidenceLevel, experimentSettings._confidenceLevel) &&
			Objects.equals(
				_dxpVariantsSettings,
				experimentSettings._dxpVariantsSettings)) {

			return true;
		}

		return false;
	}

	@Max(99)
	@Min(80)
	@NotNull
	public Double getConfidenceLevel() {
		return _confidenceLevel;
	}

	@JsonProperty("dxpVariantsSettings")
	@NotEmpty
	@Valid
	public List<DXPVariantSettings> getDXPVariantsSettings() {
		return _dxpVariantsSettings;
	}

	@JsonIgnore
	public Map<String, DXPVariantSettings> getDXPVariantsSettingsMap() {
		Map<String, DXPVariantSettings> dxpVariantSettingsMap = new HashMap<>();

		for (DXPVariantSettings dxpVariantSettings : _dxpVariantsSettings) {
			dxpVariantSettingsMap.put(
				dxpVariantSettings.getDXPVariantId(), dxpVariantSettings);
		}

		return dxpVariantSettingsMap;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_confidenceLevel, _dxpVariantsSettings);
	}

	public void setConfidenceLevel(Double confidenceLevel) {
		_confidenceLevel = confidenceLevel;
	}

	public void setDXPVariantsSettings(
		List<DXPVariantSettings> dxpVariantsSettings) {

		_dxpVariantsSettings = dxpVariantsSettings;
	}

	private Double _confidenceLevel;
	private List<DXPVariantSettings> _dxpVariantsSettings;

}