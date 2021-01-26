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

import com.liferay.osb.asah.backend.graphql.GraphQLProperty;
import com.liferay.osb.asah.backend.graphql.GraphQLType;
import com.liferay.osb.asah.common.dto.DataSourceDTO;

/**
 * @author Rachael Koestartyo
 */
@GraphQLType
public class DataSource extends DataSourceDTO {

	@GraphQLProperty("url")
	@Override
	public String getURL() {
		return super.getURL();
	}

}