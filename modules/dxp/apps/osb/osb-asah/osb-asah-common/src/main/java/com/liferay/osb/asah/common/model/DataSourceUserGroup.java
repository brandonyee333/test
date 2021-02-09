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

import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Transient;

/**
 * @author Inácio Nery
 */
public class DataSourceUserGroup {

	public DataSourceUserGroup() {
	}

	public DataSourceUserGroup(
		Boolean enableAllChildren, Long userGroupId, Set<Long> userGroupIds) {

		_enableAllChildren = enableAllChildren;
		_userGroupId = userGroupId;
		_userGroupIds = userGroupIds;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DataSourceUserGroup)) {
			return false;
		}

		DataSourceUserGroup dataSourceUserGroup = (DataSourceUserGroup)obj;

		if (Objects.equals(
				_enableAllChildren, dataSourceUserGroup._enableAllChildren) &&
			Objects.equals(_userGroupId, dataSourceUserGroup._userGroupId) &&
			Objects.equals(_userGroupIds, dataSourceUserGroup._userGroupIds)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Boolean getEnableAllChildren() {
		return _enableAllChildren;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getUserGroupId() {
		return _userGroupId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Set<Long> getUserGroupIds() {
		return _userGroupIds;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_enableAllChildren, _userGroupId, _userGroupIds);
	}

	public void setEnableAllChildren(Boolean enableAllChildren) {
		_enableAllChildren = enableAllChildren;
	}

	public void setUserGroupId(Long userGroupId) {
		_userGroupId = userGroupId;
	}

	public void setUserGroupIds(Set<Long> userGroupIds) {
		_userGroupIds = userGroupIds;
	}

	@Transient
	private Boolean _enableAllChildren;

	@Transient
	private Long _userGroupId;

	@Transient
	private Set<Long> _userGroupIds;

}