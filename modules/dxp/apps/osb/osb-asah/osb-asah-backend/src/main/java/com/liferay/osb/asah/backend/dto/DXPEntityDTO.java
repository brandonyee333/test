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

import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.graphql.GraphQLType;

/**
 * @author Matthew Kong
 */
@GraphQLType
public class DXPEntityDTO {

	public DXPEntityDTO(DXPEntity dxpEntity) {
		_dataSourceName = dxpEntity.getDataSourceName();
		_id = dxpEntity.getId();
		_name = dxpEntity.getName();
	}

	public String getDataSourceName() {
		return _dataSourceName;
	}

	public Long getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	private final String _dataSourceName;
	private final Long _id;
	private final String _name;

}