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

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author André Miranda
 */
public class DXPVariant {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DXPVariant)) {
			return false;
		}

		DXPVariant dxpVariant = (DXPVariant)obj;

		if (Objects.equals(_changes, dxpVariant._changes) &&
			Objects.equals(_control, dxpVariant._control) &&
			Objects.equals(_dxpVariantId, dxpVariant._dxpVariantId) &&
			Objects.equals(_dxpVariantName, dxpVariant._dxpVariantName) &&
			Objects.equals(_trafficSplit, dxpVariant._trafficSplit)) {

			return true;
		}

		return false;
	}

	@Min(0)
	@NotNull
	public Integer getChanges() {
		return _changes;
	}

	@JsonProperty("dxpVariantId")
	@NotBlank
	public String getDXPVariantId() {
		return _dxpVariantId;
	}

	@JsonProperty("dxpVariantName")
	@NotBlank
	public String getDXPVariantName() {
		return _dxpVariantName;
	}

	@Max(100)
	@Min(0)
	@NotNull
	public Double getTrafficSplit() {
		return _trafficSplit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_changes, _control, _dxpVariantId, _dxpVariantName, _trafficSplit);
	}

	@NotNull
	public Boolean isControl() {
		return _control;
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

	public void setTrafficSplit(Double trafficSplit) {
		_trafficSplit = trafficSplit;
	}

	private Integer _changes;
	private Boolean _control;
	private String _dxpVariantId;
	private String _dxpVariantName;
	private Double _trafficSplit;

}