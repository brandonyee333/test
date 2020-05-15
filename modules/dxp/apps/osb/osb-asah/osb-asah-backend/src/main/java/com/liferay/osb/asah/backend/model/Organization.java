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

import java.util.Objects;

/**
 * @author Matthew Kong
 */
public class Organization extends DXPEntity {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!super.equals(obj)) {
			return false;
		}

		if (!(obj instanceof Organization)) {
			return false;
		}

		Organization organization = (Organization)obj;

		if (Objects.equals(getId(), organization.getId()) &&
			Objects.equals(getName(), organization.getName()) &&
			Objects.equals(_parentName, organization._parentName) &&
			Objects.equals(_type, organization._type)) {

			return true;
		}

		return false;
	}

	public String getParentName() {
		return _parentName;
	}

	public String getType() {
		return _type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName(), _parentName, _type);
	}

	public void setParentName(String parentName) {
		_parentName = parentName;
	}

	public void setType(String type) {
		_type = type;
	}

	private String _parentName;
	private String _type;

}