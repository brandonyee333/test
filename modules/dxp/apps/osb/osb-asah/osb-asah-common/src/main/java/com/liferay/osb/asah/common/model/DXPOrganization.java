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

import com.liferay.osb.asah.common.entity.DXPEntity;

import java.util.Objects;

import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Matthew Kong
 */
@Table("dxpentity")
public class DXPOrganization extends DXPEntity {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || !super.equals(obj) ||
			!(obj instanceof DXPOrganization)) {

			return false;
		}

		DXPOrganization dxpOrganization = (DXPOrganization)obj;

		if (Objects.equals(getId(), dxpOrganization.getId()) &&
			Objects.equals(getName(), dxpOrganization.getName()) &&
			Objects.equals(_parentName, dxpOrganization._parentName) &&
			Objects.equals(_type, dxpOrganization._type)) {

			return true;
		}

		return false;
	}

	public String getParentName() {
		return _parentName;
	}

	@Override
	public Type getType() {
		return DXPEntity.Type.ORGANIZATION;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName(), _parentName, _type);
	}

	public void setParentName(String parentName) {
		_parentName = parentName;
	}

	@Transient
	private String _parentName;

	@Transient
	private String _type;

}