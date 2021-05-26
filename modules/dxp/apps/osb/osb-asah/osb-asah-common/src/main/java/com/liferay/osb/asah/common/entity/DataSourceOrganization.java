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

package com.liferay.osb.asah.common.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Inácio Nery
 */
@Table
public class DataSourceOrganization {

	public DataSourceOrganization() {
	}

	public DataSourceOrganization(
		Boolean enableAllChildren, Long organizationId,
		Set<Long> organizationIds) {

		_enableAllChildren = enableAllChildren;
		_organizationId = organizationId;
		_organizationIds = organizationIds;
	}

	public DataSourceOrganization(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DataSourceOrganization)) {
			return false;
		}

		DataSourceOrganization dataSourceOrganization =
			(DataSourceOrganization)obj;

		if (Objects.equals(
				_enableAllChildren,
				dataSourceOrganization._enableAllChildren) &&
			Objects.equals(
				_organizationId, dataSourceOrganization._organizationId) &&
			Objects.equals(
				_organizationIds, dataSourceOrganization._organizationIds)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Boolean getEnableAllChildren() {
		return _enableAllChildren;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonProperty("id")
	public Long getOrganizationId() {
		return _organizationId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Set<Long> getOrganizationIds() {
		return _organizationIds;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_enableAllChildren, _organizationId, _organizationIds);
	}

	public void setEnableAllChildren(Boolean enableAllChildren) {
		_enableAllChildren = enableAllChildren;
	}

	public void setOrganizationId(Long organizationId) {
		_organizationId = organizationId;
	}

	public void setOrganizationIds(Set<Long> organizationIds) {
		_organizationIds = organizationIds;
	}

	@Transient
	private Boolean _enableAllChildren;

	@Transient
	private Long _organizationId;

	@Transient
	private Set<Long> _organizationIds;

}