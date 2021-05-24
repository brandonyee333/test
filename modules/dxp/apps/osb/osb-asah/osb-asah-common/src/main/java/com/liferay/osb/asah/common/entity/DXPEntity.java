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

import java.util.Objects;

/**
 * @author Matthew Kong
 */
public class DXPEntity {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof DXPEntity)) {
			return false;
		}

		DXPEntity dxpEntity = (DXPEntity)obj;

		if (Objects.equals(_dataSourceName, dxpEntity._dataSourceName) &&
			Objects.equals(_id, dxpEntity._id) &&
			Objects.equals(_name, dxpEntity._name)) {

			return true;
		}

		return false;
	}

	public String getDataSourceName() {
		return _dataSourceName;
	}

	public String getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id, _name);
	}

	public void setDataSourceName(String dataSourceName) {
		_dataSourceName = dataSourceName;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setName(String name) {
		_name = name;
	}

	private String _dataSourceName;
	private String _id;
	private String _name;

}