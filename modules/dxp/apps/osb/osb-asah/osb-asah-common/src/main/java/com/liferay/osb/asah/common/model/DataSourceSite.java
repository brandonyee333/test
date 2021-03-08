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

package com.liferay.osb.asah.common.model;

import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Map;
import java.util.Objects;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Transient;

/**
 * @author Inácio Nery
 */
public class DataSourceSite {

	public DataSourceSite() {
	}

	public DataSourceSite(Boolean enableAllChildren, Long siteId) {
		_enableAllChildren = enableAllChildren;
		_siteId = siteId;
	}

	public DataSourceSite(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DataSourceSite)) {
			return false;
		}

		DataSourceSite dataSourceSite = (DataSourceSite)obj;

		if (Objects.equals(
				_enableAllChildren, dataSourceSite._enableAllChildren) &&
			Objects.equals(_siteId, dataSourceSite._siteId)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Boolean getEnableAllChildren() {
		return _enableAllChildren;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getSiteId() {
		return _siteId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_enableAllChildren, _siteId);
	}

	public void setEnableAllChildren(Boolean enableAllChildren) {
		_enableAllChildren = enableAllChildren;
	}

	public void setSiteId(Long siteId) {
		_siteId = siteId;
	}

	@Transient
	private Boolean _enableAllChildren;

	@Transient
	private Long _siteId;

}